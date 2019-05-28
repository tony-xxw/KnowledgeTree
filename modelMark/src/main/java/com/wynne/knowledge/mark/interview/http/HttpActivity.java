package com.wynne.knowledge.mark.interview.http;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.R2;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * @author Wynne
 */
public class HttpActivity extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.http_activity;
    }

    @OnClick({R2.id.btn_get})
    public void onViewClicked(View view) {
        if (view.getId() == R2.id.btn_get) {
            handleGetRequest(new IRequest() {
                @Override
                public String getBaseUrl() {
                    return null;
                }

                @Override
                public String getMethod() {
                    return null;
                }

                @Override
                public IEncrypt getEncrypt() {
                    return null;
                }

                @Override
                public HashMap<String, Object> getParams() {
                    return null;
                }

                @Override
                public Map<String, FilePair> getFilePair() {
                    return null;
                }

                @Override
                public Map<String, String> getHeaders() {
                    return null;
                }
            });
        }
    }


    private String handleGetRequest(IRequest request) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(buildGetUrl(request.getBaseUrl(), request.getParams(), request.getEncrypt()));
            openUrlConnection(url, httpURLConnection);
            normalSetting(httpURLConnection, Method.GET, request.getHeaders());
            if (httpURLConnection == null) {
                return null;
            }

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                String contentEncoding = httpURLConnection.getContentEncoding();
                InputStream stream = null;

                stream = wrapsStream(contentEncoding, inputStream);
                String data = convertStreamToString(stream);
                return data;
            }
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String convertStreamToString(InputStream stream) {
        return null;
    }

    private InputStream wrapsStream(String contentEncoding, InputStream inputStream) {
        return null;
    }

    private void normalSetting(HttpURLConnection httpURLConnection, Method get, Map<String, String> headers) {

    }

    private void openUrlConnection(URL url, HttpURLConnection httpURLConnection) {

    }

    private String buildGetUrl(String baseUrl, HashMap<String, Object> params, IEncrypt encrypt) {
        if (TextUtils.isEmpty(baseUrl) || params == null || params.size() == 0) {
            return baseUrl;
        }

        if (!baseUrl.endsWith("?")) {
            baseUrl += "?";
        }

        String paramsStr = buildGetParams(params);
        if (encrypt != null) {
            paramsStr = encrypt.encrypt(baseUrl);
        }

        StringBuilder sbUrl = new StringBuilder(baseUrl);
        sbUrl.append(paramsStr);
        return sbUrl.toString();
    }

    private String buildGetParams(HashMap<String, Object> params) {
        return null;
    }


}
