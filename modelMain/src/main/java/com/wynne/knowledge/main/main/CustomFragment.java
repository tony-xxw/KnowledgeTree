package com.wynne.knowledge.main.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.main.R;
import com.wynne.knowledge.main.audio.AudioActivity;
import com.wynne.knowledge.base.base.view.BaseFragment;
import com.wynne.knowledge.main.custom.ipc.IpcActivity;
import com.wynne.knowledge.main.custom.window.WindowActivity;
import com.wynne.knowledge.main.interfere.InterfereActivity;
import com.wynne.knowledge.main.ipc.binder.BinderPoolActivity;
import com.wynne.knowledge.main.ipc.provider.ProviderActivity;
import com.wynne.knowledge.main.ipc.socket.SocketActivity;
import com.wynne.knowledge.main.loader.ClassLoaderActivity;
import com.wynne.knowledge.main.rx.RxJavaActivity;
import com.wynne.knowledge.main.thread.ThreadPoolActivity;
import com.wynne.knowledge.main.view.ViewActivity;

/**
 * @author XXW
 * @date 2018/2/28
 */

@Route(path = ARouterPath.FRAGMENT_CUSTOM)
public class CustomFragment extends BaseFragment implements View.OnClickListener {


    @Override
    public void initView() {
        mContentView.findViewById(R.id.btn_classloader).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_interfere).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_ipc).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_ipc_provider).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_socket).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_binder_poll).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_audio).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_view).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_thread_poll).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_rx).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_window).setOnClickListener(this);


        EditText editText = mContentView.findViewById(R.id.btn_keyevent);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.e("XXW", "标签: " + textView.getText().toString());
                return false;
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_fragment;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_classloader) {
            startActivity(new Intent(getActivity(), ClassLoaderActivity.class));

        } else if (i == R.id.btn_ipc) {
            startActivity(new Intent(getActivity(), IpcActivity.class));

        } else if (i == R.id.btn_ipc_provider) {
            startActivity(new Intent(getActivity(), ProviderActivity.class));

        } else if (i == R.id.btn_socket) {
            startActivity(new Intent(getActivity(), SocketActivity.class));

        } else if (i == R.id.btn_binder_poll) {
            startActivity(new Intent(getActivity(), BinderPoolActivity.class));

        } else if (i == R.id.btn_view) {//                startActivity(new Intent(getActivity(), ThumbActivity.class));
            startActivity(new Intent(getActivity(), ViewActivity.class));

        } else if (i == R.id.btn_audio) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int checkPermission = getActivity().checkSelfPermission(Manifest.permission.RECORD_AUDIO);

                if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    return;
                } else {
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                }
            } else {
                startActivity(new Intent(getActivity(), AudioActivity.class));
            }

        } else if (i == R.id.btn_thread_poll) {
            startActivity(new Intent(getActivity(), ThreadPoolActivity.class));

        } else if (i == R.id.btn_rx) {
            startActivity(new Intent(getActivity(), RxJavaActivity.class));

        } else if (i == R.id.btn_window) {
            startActivity(new Intent(getActivity(), WindowActivity.class));

        } else if (i == R.id.btn_interfere) {
            startActivity(new Intent(getActivity(), InterfereActivity.class));

        } else {
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {//禁止

                }

                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
