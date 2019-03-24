package com.wynne.knowledge.main.net;

import android.os.Handler;
import android.os.Message;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Wynne
 */
public class RetrofitService {

    private static NetApi netApi;

    private static final String BASE_URL = "http://www.wanandroid.com/";

    private static NetApi createService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //是否重连
                .retryOnConnectionFailure(true)
                .addInterceptor(mLoggingInterceptor)
                .addInterceptor(sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();


        return retrofit.create(NetApi.class);
    }


    public static NetApi getInstance() {
        if (netApi == null) {
            synchronized (RetrofitService.class) {
                if (netApi == null) {
                    netApi = createService();
                }
            }
        }
        return netApi;
    }


    private static final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            }
            final Response response = chain.proceed(request);
            return response;
        }
    };

    private static String cacheControl = "Cache-Control: public, max-age=3600";
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Response originalResponse = chain.proceed(request);


            return originalResponse.newBuilder()
                    .addHeader("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();

        }
    };
}
