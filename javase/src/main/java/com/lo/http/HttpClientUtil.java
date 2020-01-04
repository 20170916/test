package com.lo.http;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 1、since 4.3 不再使用 DefaultHttpClient
 * 2、UrlEncodedFormEntity()的形式比较单一，只能是普通的键值对，局限性相对较大;而StringEntity()的形式比较自由，
 * 只要是字符串放进去，不论格式都可以。 3、当请求体为Json字符串时发送请求时，需指定 Content
 * type：httpost.setHeader("Content-type", "application/json"); 否则默认使用 Content
 * type 'text/plain;charset=UTF-8'。
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {

    private static final Logger logger = Logger.getLogger(HttpClientUtil.class);

    public static final String DEFAULT_CHARSET = "UTF-8";



    // ConnectionManager
    private final static PoolingHttpClientConnectionManager POOLCONNMANAGER = new PoolingHttpClientConnectionManager();

    // RetryHandler
    private final static HttpRequestRetryHandler HTTPREQUESTRETRYHANDLER = (exception, executionCount, context) -> {
        if (executionCount >= 3) {
            return false;
        }
        if (exception instanceof NoHttpResponseException) {
            return true;
        }
        if (exception instanceof InterruptedIOException) {
            return false;
        }
        if (exception instanceof UnknownHostException) {
            return false;
        }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();

        return !(request instanceof HttpEntityEnclosingRequest);
    };

    static {   //类加载的时候 设置最大连接数 和 每个路由的最大连接数
        POOLCONNMANAGER.setMaxTotal(200);
        POOLCONNMANAGER.setDefaultMaxPerRoute(200);
        //POOLCONNMANAGER.setMaxPerRoute(, );
    }

    private volatile static CloseableHttpClient httpClient;
    /**
     * 获取CloseableHttpClient
     * @return
     */
    private static CloseableHttpClient getCloseableHttpClient() {
        if(httpClient==null){
            ConnectionKeepAliveStrategy connectionKeepAliveStrategy = (httpResponse, httpContext) -> {
                // tomcat默认keepAliveTimeout为20s
                return 20 * 1000;
            };
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(getRequestConfig())
                    .setConnectionManager(POOLCONNMANAGER)
                    .setRetryHandler(HTTPREQUESTRETRYHANDLER)
                    .setKeepAliveStrategy(connectionKeepAliveStrategy)
                    .build();
        }
        return httpClient;

        /*RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();*/

        /*ConnectionKeepAliveStrategy connectionKeepAliveStrategy = (httpResponse, httpContext) -> {
            // tomcat默认keepAliveTimeout为20s
            return 20 * 1000;
        };
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(getRequestConfig())
                .setConnectionManager(POOLCONNMANAGER)
                .setRetryHandler(HTTPREQUESTRETRYHANDLER)
                .setKeepAliveStrategy(connectionKeepAliveStrategy)
                .build();
        return httpClient;*/

        // return  HttpClients.createDefault();
    }

    private static RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                .setSocketTimeout(10 * 1000)
                .setConnectTimeout(10 * 1000)
                .setConnectionRequestTimeout(10 * 1000)
                .build();
    }



    /**
     * 使用closeableHttpClient执行httpRequestBase请求,返回以charset编码的响应体
     *
     * @param closeableHttpClient
     * @param httpRequestBase
     * @param charset
     * @return
     */
    private static String execute(CloseableHttpClient closeableHttpClient, HttpRequestBase httpRequestBase,
                                  String charset) {
        CloseableHttpResponse closeableHttpResponse = null;
        String entityStr = "";
        try {
            logger.info("execute");
            closeableHttpResponse = closeableHttpClient.execute(httpRequestBase);
            logger.info("execute over");
            if (closeableHttpResponse != null) {
                // 响应码以2(200,201,202,203,204,205,206)开头，即认为成功
                Integer statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
                if (statusCode.toString().startsWith("2")) {

                    HttpEntity httpEntity = new BufferedHttpEntity(closeableHttpResponse.getEntity());
                    entityStr = EntityUtils.toString(httpEntity, charset);
                    httpEntity.getContent().close();
                } else {
                    logger.info(closeableHttpResponse.getStatusLine().toString());
                    logger.info(Arrays.toString(closeableHttpResponse.getAllHeaders()));
                    logger.info(closeableHttpResponse.getEntity().toString());
                }
            }
        } catch (IOException e) {
            logger.error("IOException was catched:", e);
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    //closeableHttpResponse.close();
                    //closeableHttpClient.close();
                    //closeableHttpClient.close();
                } catch (Exception e) {
                    logger.error("CloseableHttpResponse close failed:", e);
                }
            }

        }
        return entityStr;
    }





    /**
     * http post 方法(不建议使用,HttpClient将不会被关闭,会造成资源泄露)
     *
     * @param url
     * @param charset
     * @param headerMap
     * @param entityStr
     * @return
     */
    @SuppressWarnings("resource")
    public static HttpResponse doPost(String url, String charset, Map<String, String> headerMap, String entityStr) {
        logger.info("http post return HttpResponse,the url is: " + url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = null;
        // 获取一个HttpPost对象
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头
        httpPost = (HttpPost) setHeaders(httpPost, headerMap);
        // 设置请求体
        httpPost = (HttpPost) setEntities(httpPost, entityStr, charset);
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return httpResponse;
    }

    /**
     * http post 方法
     *
     * @param url
     * @param headerMap
     * @param charset
     * @return
     */
    public static String doPost(String url, Map<String, String> headerMap, String entityStr, String charset) {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        //CloseableHttpClient closeableHttpClient =getCloseableHttpClient();
        logger.info(String.format("http post return String,the url is: %s,entiry:%s" , url,""));
        // 获取一个HttpPost对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json");
        // 设置请求头
        httpPost = (HttpPost) setHeaders(httpPost, headerMap);
        // 设置请求体
        httpPost = (HttpPost) setEntities(httpPost, entityStr, charset);
        // 获得响应体
        return execute(closeableHttpClient, httpPost, charset);
    }


    public static String doGet(String url, Map<String, String> headerMap, String charset) {
        //CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        CloseableHttpClient closeableHttpClient =getCloseableHttpClient();
        logger.info(String.format("http post return String,the url is: %s,entiry:%s" , url,""));
        // 获取一个HttpPost对象
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头
        httpGet = (HttpGet) setHeaders(httpGet, headerMap);
        // 获得响应体
        return execute(closeableHttpClient, httpGet, charset);
    }


    /**
     * 为HttpRequestBase设置请求头headerMap
     *
     * @param httpRequestBase
     * @param headerMap
     * @return
     */
    private static HttpRequestBase setHeaders(HttpRequestBase httpRequestBase, Map<String, String> headerMap) {
        if (httpRequestBase != null && headerMap != null && !headerMap.isEmpty()) {

            for (Entry<String, String> entry : headerMap.entrySet()) {
                httpRequestBase.setHeader(entry.getKey(), entry.getValue());

            }

//            String env = DrcsProperty.getRunningEnv();
//            if (env.equals("dev")) {
//                httpRequestBase.setHeader("Cookie", DEV_SESSION);
//            } else if (env.equals("sit")){
//                httpRequestBase.setHeader("Cookie", SIT_SESSION);
//            }
        }
        return httpRequestBase;
    }

    /**
     * 为HttpEntityEnclosingRequestBase设置请求体entityStr,默认编码集为utf-8,可指定字符编码集charset
     *
     * @param heerb
     * @param entityStr
     * @param charset
     * @return
     */
    private static HttpEntityEnclosingRequestBase setEntities(HttpEntityEnclosingRequestBase heerb, String entityStr,
                                                              String charset) {
        String reviseCharset = (charset == null) ? DEFAULT_CHARSET : charset;
        StringEntity se = new StringEntity(entityStr, reviseCharset);
        heerb.setEntity(se);
        return heerb;
    }

    public static String doPostWithJson(String url,String json, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(url);
        HttpResponse httpResponse = null;
        try {
            addHeaderOrParam(headerMap,httpPost);
            StringEntity stringEntity = new StringEntity(json, DEFAULT_CHARSET);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            httpResponse = getCloseableHttpClient().execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public static void addHeaderOrParam(Map<String,String> header, HttpRequestBase method){
        if(header!=null){
            Iterator<Map.Entry<String, String>> iterator = header.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = iterator.next();
                method.addHeader(elem.getKey(), elem.getValue());
            }
        }
    }


}