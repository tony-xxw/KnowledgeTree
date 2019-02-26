package com.wynne.knowledge.mark.interview.okhttp;

import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import java.io.IOException;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Wynne
 */
public class OkHttpActivity extends BaseActivity {
    private String getUrl = "http://gank.io/api/data/福利/10/1";

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.okhttp_activity;
    }

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_get) {
            okHttpGet();
        } else if (view.getId() == R.id.btn_post) {
            okHttpPost();
        }
    }

    private void okHttpPost() {



    }

    private void okHttpGet() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(getUrl).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        emitter.onNext(response.body().string());
                    } else {
                        throw new IOException("error : " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("XXW", "success :" + s);

                    }
                });

    }
}
