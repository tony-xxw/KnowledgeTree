package com.wynne.http

import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import kotlinx.android.synthetic.main.activity_socket_layout.*
import org.apache.mina.core.service.IoHandlerAdapter
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import org.apache.mina.transport.socket.nio.NioSocketAcceptor
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class SocketActivity : BaseActivity() {
    lateinit var socket: Socket
    lateinit var bufferedReader: BufferedReader
    lateinit var outputStream: OutputStream
    var executorService: ExecutorService = Executors.newSingleThreadExecutor()
    lateinit var response: String
    var handler: Handler = object : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            receive_message.text = response
        }
    }

    override fun initView() {


    }


    override fun getLayoutId(): Int = R.layout.activity_socket_layout

    fun onClick(v: View) {
        if (v.id == R.id.btn_socket_receive) {
            executorService.execute {
                val inputStream = socket.getInputStream()
                val inputStreamReader = InputStreamReader(inputStream)
                bufferedReader = BufferedReader(inputStreamReader)
                response = bufferedReader.readLine()
                handler.sendEmptyMessage(0)
            }

        } else if (v.id == R.id.btn_socket_disable) {
            bufferedReader.close()
            outputStream.close()
            socket.close()
            Log.d("XXW", "socket isConnect" + socket.isConnected)
        } else if (v.id == R.id.btn_socket_send) {
            executorService.execute({
                outputStream = socket.getOutputStream()
                outputStream.write((edit.toString() + "\n").toByteArray(Charsets.UTF_8))
                outputStream.flush()
            })
        } else if (v.id == R.id.btn_socket_connect) {
            executorService.execute({

                socket = Socket("111.111.11.11", 12306)
                Log.d("XXW", "socket isConnect " + socket.isConnected)
            })
        } else if (v.id == R.id.test_server) {
            statServer()
        }
    }

    private fun statServer() {
        executorService.execute {
            try {
                // 为了看流程，我就把所有的代码都放在main函数里了,也没有捕捉异常，直接抛出去了。实际开发中不可取。
                // 1.新建ServerSocket对象，创建指定端口的连接
                val serverSocket = ServerSocket(12306)
                println("服务端监听开始了~~~~")
                // 2.进行监听
                val socket = serverSocket.accept()// 开始监听9999端口，并接收到此套接字的连接。
                // 3.拿到输入流（客户端发送的信息就在这里）
                val `is` = socket.getInputStream()
                // 4.解析数据
                val reader = InputStreamReader(`is`)
                val bufReader = BufferedReader(reader)
                var s: String? = null
                val sb = StringBuffer()
                val value = bufReader.readLine()
                while ((value) != null) {
                    sb.append(s)
                }
                println("服务器：" + sb.toString())
                // 关闭输入流
                socket.shutdownInput()

                val os = socket.getOutputStream()
                os.write(("我是服务端,客户端发给我的数据就是：" + sb.toString()).toByteArray())
                os.flush()
                // 关闭输出流
                socket.shutdownOutput()
                os.close()

                // 关闭IO资源
                bufReader.close()
                reader.close()
                `is`.close()

                socket.close()// 关闭socket
                serverSocket.close()// 关闭ServerSocket

            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    class TestHandler : IoHandlerAdapter() {

        @Throws(Exception::class)
        override fun exceptionCaught(session: IoSession?, cause: Throwable) {
            Log.d("XXW", "exceptionCaught: $cause")
        }

        @Throws(Exception::class)
        override fun messageReceived(session: IoSession?, message: Any?) {
            Log.d("XXW", "recieve :" + (message as String?)!!)
            session!!.write("hello I am server")
        }

        @Throws(Exception::class)
        override fun messageSent(session: IoSession?, message: Any?) {

        }

        @Throws(Exception::class)
        override fun sessionClosed(session: IoSession?) {
            Log.d("XXW", "sessionClosed")
        }

        @Throws(Exception::class)
        override fun sessionOpened(session: IoSession?) {
            Log.d("XXW", "sessionOpen")
        }

        @Throws(Exception::class)
        override fun sessionIdle(session: IoSession?, status: IdleStatus?) {
        }
    }
}