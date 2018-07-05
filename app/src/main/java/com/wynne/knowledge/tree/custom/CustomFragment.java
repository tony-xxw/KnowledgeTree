package com.wynne.knowledge.tree.custom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.custom.audio.AudioActivity;
import com.wynne.knowledge.tree.custom.ipc.IpcActivity;
import com.wynne.knowledge.tree.custom.ipc.binder.BinderPoolActivity;
import com.wynne.knowledge.tree.custom.ipc.provider.ProviderActivity;
import com.wynne.knowledge.tree.custom.ipc.socket.SocketActivity;
import com.wynne.knowledge.tree.custom.loader.ClassLoaderActivity;
import com.wynne.knowledge.tree.custom.rx.RxJavaActivity;
import com.wynne.knowledge.tree.custom.thread.ThreadPoolActivity;
import com.wynne.knowledge.tree.custom.view.ThumbActivity;

/**
 * @author XXW
 * @date 2018/2/28
 */

public class CustomFragment extends Fragment implements View.OnClickListener {


    public static CustomFragment getInstance() {
        CustomFragment fragment = new CustomFragment();
        return fragment;
    }

    View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.custom_fragment, null);
        mContentView.findViewById(R.id.btn_classloader).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_ipc).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_ipc_provider).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_socket).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_binder_poll).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_audio).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_view).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_thread_poll).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_rx).setOnClickListener(this);
        return mContentView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_classloader:
                startActivity(new Intent(getActivity(), ClassLoaderActivity.class));
                break;
            case R.id.btn_ipc:
                startActivity(new Intent(getActivity(), IpcActivity.class));
                break;
            case R.id.btn_ipc_provider:
                startActivity(new Intent(getActivity(), ProviderActivity.class));
                break;
            case R.id.btn_socket:
                startActivity(new Intent(getActivity(), SocketActivity.class));
                break;
            case R.id.btn_binder_poll:
                startActivity(new Intent(getActivity(), BinderPoolActivity.class));
                break;
            case R.id.btn_view:
                startActivity(new Intent(getActivity(), ThumbActivity.class));
                break;
            case R.id.btn_audio:
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
                break;
            case R.id.btn_thread_poll:
                startActivity(new Intent(getActivity(), ThreadPoolActivity.class));
                break;
            case R.id.btn_rx:
                startActivity(new Intent(getActivity(), RxJavaActivity.class));
                break;
            default:
                break;
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
