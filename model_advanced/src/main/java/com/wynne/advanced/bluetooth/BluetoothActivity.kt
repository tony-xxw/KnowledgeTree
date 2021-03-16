package com.wynne.advanced.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import com.clj.fastble.BleManager
import com.clj.fastble.callback.BleScanCallback
import com.clj.fastble.data.BleDevice
import com.clj.fastble.scan.BleScanRuleConfig
import com.wynne.advanced.R
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.utils.BlueToothUtil
import kotlinx.android.synthetic.main.activity_base_advanced_bluetooth.*


class BluetoothActivity : BaseActivity() {
    lateinit var adapter: BluetoothAdapter
    lateinit var bluetoothLeScanner: BluetoothLeScanner
    lateinit var scanCallback: ScanCallback
    lateinit var broadcastReceiver: BroadcastReceiver

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {

        broadcastReceiver = BlueToothRecvicer
        val intentFilter = IntentFilter()
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND)
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        registerReceiver(broadcastReceiver, intentFilter)


        val manager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        adapter = manager.adapter
        if (!adapter.isEnabled) {
            adapter.enable()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bluetoothLeScanner = adapter.bluetoothLeScanner
        }

        initSdk()

        btnCheck.setOnClickListener {
            initBlueToothParms()
        }

        btnOpen.setOnClickListener {
            obtainBlueToothList()
        }

        btnBlue.setOnClickListener {

            BleManager.getInstance().scan(object : BleScanCallback() {
                override fun onScanFinished(scanResultList: MutableList<BleDevice>) {

                }

                override fun onScanStarted(success: Boolean) {
                }

                override fun onScanning(bleDevice: BleDevice) {
                    Log.d("XXW", "name " + BlueToothUtil.parseAdertisedData(bleDevice.scanRecord).name)
                    Log.d("XXW", "uuids " + BlueToothUtil.parseAdertisedData(bleDevice.scanRecord).uuids)
                    Log.d("XXW", "address " + bleDevice.device.address)
                }

            })
        }

        Log.d("XXW", "adress : " + getBluetoothAddress())
    }

    override val layoutId: Int =R.layout.activity_base_advanced_bluetooth

    lateinit var bleResultConfig: BleScanRuleConfig

    private fun initSdk() {

        bleResultConfig = BleScanRuleConfig.Builder()
                .setServiceUuids(null)
                .setDeviceName(true, "玩个锤子")
                .setDeviceMac("B4:0B:44:91:93:A1")
                .setAutoConnect(false)
                .setScanTimeOut(10000)
                .build()


    }

    private fun initBlueToothParms() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scanCallback = object : ScanCallback() {

                override fun onScanResult(callbackType: Int, result: ScanResult) {
                    super.onScanResult(callbackType, result)
                    var device: BluetoothDevice? = null
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        device = result.device

                    }

                    if (TextUtils.isEmpty(device?.name)) {
                        Log.d("XXW", "name " + BlueToothUtil.parseAdertisedData(result.scanRecord?.bytes).name)
                    } else {
                        Log.d("XXW", "name " + device?.name)
                    }
                    Log.d("XXW", "adress " + device?.address)


                }

                override fun onBatchScanResults(results: MutableList<ScanResult>?) {
                    super.onBatchScanResults(results)
                }

                override fun onScanFailed(errorCode: Int) {
                    super.onScanFailed(errorCode)
                    Log.d("XXW", "faild")

                }
            }


        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bluetoothLeScanner.startScan(scanCallback)


        }
    }

    private fun obtainBlueToothList() {
        adapter.startDiscovery()
    }



    object BlueToothRecvicer : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothDevice.ACTION_FOUND -> {

                    Log.d("XXW", "找到设备")
                    val device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice
                    Log.d("XXW", "name " + device.name)
                    Log.d("XXW", "adress " + device.address)

                }
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {

                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {

                }
            }
        }
    }

    private fun getBluetoothAddress(): String? {
        try {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val field = bluetoothAdapter.javaClass.getDeclaredField("mService")
            // 参数值为true，禁用访问控制检查
            field.isAccessible = true
            val bluetoothManagerService = field.get(bluetoothAdapter) ?: return null
            val method = bluetoothManagerService.javaClass.getMethod("getAddress")
            val address = method.invoke(bluetoothManagerService)
            return if (address != null && address is String) {
                address
            } else {
                null
            }
            //抛一个总异常省的一堆代码...
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

}