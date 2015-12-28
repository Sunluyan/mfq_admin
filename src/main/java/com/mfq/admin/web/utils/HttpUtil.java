package com.mfq.admin.web.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class HttpUtil {


    private static final Logger logger = LoggerFactory
            .getLogger(HttpUtil.class);

    private static CloseableHttpClient client;

    // 连接超时时间，默认10秒
    private static int socketTimeout = 10000;
    // 传输超时时间，默认30秒
    private static int connectTimeout = 30000;
    // 请求器的配置
    private static RequestConfig requestConfig;

    static {
        HttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
        client = HttpClients.custom().setConnectionManager(poolingConnManager)
                .build();
        // 根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
    }

    public static String get(String url, boolean fastMode) {
        return get(url, fastMode, false);
    }

    public static String get(String url, boolean fastMode,
            boolean forceTimeout) {
        HttpGet method = myHttpGet(url);
        String result = null;
        try {
            //setMethodTimeout(method, fastMode, forceTimeout);
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("push response result:{}", result);
        } catch (Exception e) {
            logger.error("push fail: url=" + url, e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    public static String post(String url, String body, boolean fastMode) {
        HttpPost method = myHttpPost(url);
        // method.setFollowRedirects(true);
        String result = null;
        try {
            //setMethodTimeout(method, fastMode);
            method.addHeader("Content-Type", "text/xml");
            method.setEntity(new StringEntity(body, "UTF-8"));
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("push response result:{}", result);
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body, e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    public static String getFromInputStream(String url, boolean fastMode) {
        HttpGet method = myHttpGet(url);
        //method.setFollowRedirects(true);
        String result = null;
        try {
            //setMethodTimeout(method, fastMode);
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error("push fail: url=" + url, e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    public static String postForm(String url, String body, boolean fastMode) {
        HttpPost method = myHttpPost(url);
        // method.setFollowRedirects(true);
        String result = null;
        try {
            method.addHeader("Content-Type", "application/x-www-form-urlencoded");
            method.setEntity(new StringEntity(body, "UTF-8"));
            //setMethodTimeout(method, fastMode);
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            try{
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                BufferedReader buffRead = new BufferedReader(isr);
                StringBuffer inputLine = new StringBuffer();
                String temp = null;
                while ((temp = buffRead.readLine()) != null) {
                    inputLine.append(temp);
                }
                logger.info("push response result:{}", result);
                result = inputLine.toString();
            }catch(Exception e){
                throw e;
            }finally{
                if(in != null){
                    in.close();
                }
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body, e);
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    public static String postJson(String url, String body, boolean fastMode) {
        HttpPost method = myHttpPost(url);
        // method.setFollowRedirects(true);
        String result = null;
        try {
            method.addHeader("Content-Type", "application/json");
            method.setEntity(new StringEntity(body, "UTF-8"));
            //////////////// start for test//////////////
            // method.setRequestHeader("m", "jkdfjksjfdjajdfkjafs");
            // method.setRequestHeader("dtp", "andorid");
            // method.setRequestHeader("dtn", "token123456");
            //////////////// end for test////////////////

            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            try{
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                BufferedReader buffRead = new BufferedReader(isr);
                StringBuffer inputLine = new StringBuffer();
                String temp = null;
                while ((temp = buffRead.readLine()) != null) {
                    inputLine.append(temp);
                }
                logger.info("push response result:{}", result);
                result = inputLine.toString();
            }catch(Exception e){
                throw e;
            }finally{
                if(in != null){
                    in.close();
                }
            }
        } catch (Exception e) {
            logger.error("push fail: url=" + url + ", body=" + body, e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    public static String postForm(String url, Map<String, String> params,
            boolean fastMode) {
        HttpPost method = myHttpPost(url);
        String result = null;
        try {
            List<NameValuePair> paramList = Lists.newArrayList();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    paramList.add(new BasicNameValuePair(entry.getKey(),
                            entry.getValue()));
                }
                method.setEntity(
                        new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
            }
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("push response result:{}", result);
        } catch (Exception e) {
            logger.error(
                    "push fail: url=" + url + ", params =" + params.toString(),
                    e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    /**
     * maixun-post专用
     * @param url
     * @param params
     * @param fastMode
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String postSmsMaixun(String url, Map<String, String> params,
            boolean fastMode) {
        HttpPost method = myHttpPost(url);
        String result = null;
        try {
            method.addHeader("Connection", "close");
            List<NameValuePair> paramList = Lists.newArrayList();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    paramList.add(new BasicNameValuePair(entry.getKey(),
                            entry.getValue()));
                }
                method.setEntity(
                        new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
            }
            HttpResponse response = client.execute(method);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            result = StringUtils.replace(result, "GBK", "utf-8");
            logger.info("push response result:{}", result);
        } catch (Exception e) {
            logger.error(
                    "HTTPException_POST_MAIXUN_SMS: url=" + url + ", params =" + params.toString(),
                    e);
        } finally {
            method.releaseConnection();
        }
        return result;
    }
    
    /**
     * 设置连接超时时间
     *
     * @param socketTimeout
     *            连接时长，默认10秒
     */
    public static void setMethodTimeout(int socketTimeout) {
        HttpUtil.socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout
     *            传输时长，默认30秒
     */
    public static void setConnectTimeout(int connectTimeout) {
        HttpUtil.connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private static void resetRequestConfig() {
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
    }
    
    private static HttpGet myHttpGet(String url){
        HttpGet get = new HttpGet(url);
        //设置请求器的配置
        get.setConfig(requestConfig);
        return get;
    }
    
    private static HttpPost myHttpPost(String url){
        HttpPost post = new HttpPost(url);
        //设置请求器的配置
        post.setConfig(requestConfig);
        return post;
    }
}