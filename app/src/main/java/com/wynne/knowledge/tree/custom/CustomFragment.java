package com.wynne.knowledge.tree.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.custom.ipc.IpcActivity;
import com.wynne.knowledge.tree.custom.ipc.provider.ProviderActivity;
import com.wynne.knowledge.tree.custom.ipc.socket.SocketActivity;
import com.wynne.knowledge.tree.custom.loader.ClassLoaderActivity;

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
            default:
                break;
        }
    }
}
