package com.wynne.knowledge.mark.interview.http;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求体
 *
 * @author Wynne
 */
public interface IRequest {

    String getBaseUrl();

    String getMethod();

    IEncrypt getEncrypt();

    HashMap<String, Object> getParams();

    Map<String, FilePair> getFilePair();

    Map<String, String> getHeaders();
}
