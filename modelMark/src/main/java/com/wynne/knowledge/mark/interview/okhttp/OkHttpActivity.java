package com.wynne.knowledge.mark.interview.okhttp;

import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.R2;
import com.wynne.knowledge.mark.widget.Sample;

import java.io.File;
import java.io.IOException;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 源码分析
 * @author Wynne
 */
public class OkHttpActivity extends BaseActivity {
    private String getUrl = "http://gank.io/api/data/福利/10/1";
    private String postUrl = "http://gank.io/api/data/福利/10/1";

    private Cache cache = new Cache(new File("/Users/zeal/Desktop/temp"), 10 * 10 * 1024);

    @Override
    public void initView() {
        Sample sample = new Sample.Build().build();
    }

    @Override
    public int getLayoutId() {
        return R.layout.okhttp_activity;
    }

    @OnClick({R2.id.btn_get, R2.id.btn_post})
    public void onViewClicked(View view) {
        if (view.getId() == R2.id.btn_get) {
            okHttpGet();
        } else if (view.getId() == R2.id.btn_post) {
            okHttpPost();
        }
    }

    private void okHttpPost() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                FormBody formBody = new FormBody.Builder().add("key", "value").build();
                OkHttpClient client = new OkHttpClient.Builder().build();
                Request request = new Request.Builder().post(formBody).url(postUrl).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        emitter.onNext(response.body().string());
                    }
                });
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

    private void okHttpGet() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();
                Request request = new Request.Builder().url(getUrl).cacheControl(new CacheControl.Builder().build()).build();

                Call call = client.newCall(request);
                Response response = call.execute();
                response.body().string();

                Log.d("XXW", "network response:" + response.networkResponse());
                Log.d("XXW", "cache response:" + response.cacheResponse());

                //在创建 cache 开始计算
                Log.d("XXW", "cache hitCount:" + cache.hitCount());//使用缓存的次数
                Log.d("XXW", "cache networkCount:" + cache.networkCount());//使用网络请求的次数
                Log.d("XXW", "cache requestCount:" + cache.requestCount());//请求的次数

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
