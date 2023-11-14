package cn.nesc.toolkit.common.httpclient;

import cn.nesc.toolkit.common.exception.HttpClientException;
import cn.nesc.toolkit.common.json.JsonUtil;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author summer
 * @Description 高性能版HttpClientUtil
 * 采用链接池及长链接技术
 * @Date 10:12 2023/10/26
 * @Param
 * @return
 **/
public class HttpClientUtil
{
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * @Author summer
     * @Description 获取HttpClientUtil实例
     * @Date 10:25 2023/10/26
     * @Param
     * @return
     **/
    private static HttpClientUtil instance = null;

    public static HttpClientUtil getInstance()
    {
        if (null == instance)
        {
            synchronized (HttpClientUtil.class)
            {
                if (null == instance)
                {
                    instance = new HttpClientUtil();
                }
            }
        }
        return instance;
    }

    // utf-8字符编码
    public static final String CHARSET_UTF_8 = "UTF-8";
    // HTTP内容类型。
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";
    // HTTP内容类型。相当于form表单的形式，提交数据
    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";
    // HTTP内容类型。相当于JSON形式，提交数据
    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

    // 连接管理器
    private PoolingHttpClientConnectionManager pool;
    // 长链接策略
    private ConnectionKeepAliveStrategy keepAliveStrategy;
    // 请求配置
    private RequestConfig requestConfig;
    private CloseableHttpClient client;

    public HttpClientUtil()
    {
        try
        {
            log.debug("Start init HttpClient configuration!");
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
            // 初始化连接池管理器
            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 将最大连接数增加到500，实际项目最好从配置文件中读取这个值
            pool.setMaxTotal(500);
            // 设置最大路由
            pool.setDefaultMaxPerRoute(100);
            // 检查永久链接的可用性
            pool.setValidateAfterInactivity(60000);

            // 长链接策略
            keepAliveStrategy = new ConnectionKeepAliveStrategy()
            {
                @Override
                public long getKeepAliveDuration(org.apache.http.HttpResponse httpResponse, HttpContext httpContext)
                {
                    HeaderElementIterator it = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext())
                    {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout"))
                        {
                            return Long.parseLong(value) * 1000;
                        }
                    }
                    return 60 * 1000;//如果没有约定，则默认定义时长为60s
                }
            };

            // 根据默认超时限制初始化requestConfig
            int socketTimeout = 10000;
            int connectTimeout = 5000;
            int connectionRequestTimeout = 5000;
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
            log.debug("Init HttpClient configuration successfully!");

            this.client = HttpClients.custom()
                    // 设置连接池管理
                    .setConnectionManager(pool)
                    // 设置长链接策略
                    .setKeepAliveStrategy(keepAliveStrategy)
                    // 设置请求配置
                    .setDefaultRequestConfig(requestConfig)
                    // 连接清理
                    .evictExpiredConnections().evictIdleConnections(10 * 1000, TimeUnit.MILLISECONDS)
                    // 设置重试次数
                    .setRetryHandler(new IapHttpRequestRetryHandler(3, true)).build();
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(e.getMessage(), e);
        }
        catch (KeyStoreException e)
        {
            log.error(e.getMessage(), e);
        }
        catch (KeyManagementException e)
        {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * @return cn.nesc.toolkit.common.httpclient.HttpResponse
     * @Author summer
     * @Description 通用的post请求
     * @Date 15:51 2023/10/26
     * @Param httpPost
     * @Param payload     请求body: json,text
     * @Param header      请求头信息
     * @Param charset     字符集
     **/
    private <T> HttpResponse sendHttpPost(HttpPost httpPost, Payload<T> payload, Map<String, String> header, String charset) throws HttpClientException
    {
        HttpResponse httpResponse = new HttpResponse();
        CloseableHttpResponse response = null;
        try
        {
            // 执行请求
            response = doPost(httpPost, payload, header, charset);
            // 得到响应实例
            if (null != response)
            {
                HttpEntity entity = response.getEntity();
                if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode())
                {
                    httpPost.abort();
                    log.debug("http request error, return code {}, abort http connection!", response.getStatusLine().getStatusCode());
                }

                httpResponse.setReturnCode(response.getStatusLine().getStatusCode());
                // 响应内容
                String responseContent = null;
                if (null == charset || "".equals(charset))
                {
                    responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
                }
                else
                {
                    responseContent = EntityUtils.toString(entity, charset);
                }
                EntityUtils.consume(entity);
                httpResponse.setReturnContent(responseContent);
            }
        }
        catch (Exception e)
        {
            httpPost.abort();
            log.error(e.getMessage(), e);
            throw new HttpClientException(e.getMessage(), e);
        }
        finally
        {
            try
            {

                // 释放资源
                if (response != null)
                {
                    response.close();
                }
                httpPost.releaseConnection();
            }
            catch (IOException e)
            {
                log.error(e.getMessage(), e);
            }
        }
        return httpResponse;
    }

    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private HttpResponse sendHttpGet(HttpGet httpGet, Map<String, String> header, String charset) throws HttpClientException
    {
        HttpResponse httpResponse = new HttpResponse();
        CloseableHttpResponse response = null;
        try
        {
            // 执行请求
            response = doGet(httpGet, header, charset);
            // 得到响应实例
            HttpEntity entity = response.getEntity();
            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode())
            {
                httpGet.abort();
                log.debug("http request error, return code {}, abort http connection!", response.getStatusLine().getStatusCode());
            }
            httpResponse.setReturnCode(response.getStatusLine().getStatusCode());
            // 响应内容
            String responseContent = null;
            if (null == charset || "".equals(charset))
            {
                responseContent = EntityUtils.toString(entity, charset);
            }
            else
            {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
            }
            EntityUtils.consume(entity);
            httpResponse.setReturnContent(responseContent);

        }
        catch (Exception e)
        {
            httpGet.abort();
            log.error(e.getMessage(), e);
            throw new HttpClientException(e.getMessage(), e);
        }
        finally
        {
            try
            {
                // 释放资源
                if (response != null)
                {
                    response.close();
                }
                httpGet.releaseConnection();
            }
            catch (IOException e)
            {
                log.error(e.getMessage(), e);
            }
        }
        return httpResponse;
    }




    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    public HttpResponse sendHttpGet(String httpUrl, Map<String, String> header) throws HttpClientException
    {
        // 创建get请求
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet, header, null);
    }

    public HttpResponse sendHttpGet(String httpUrl, Map<String, String> header, String charset) throws HttpClientException
    {
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet, header, charset);
    }

    public HttpResponse sendHttpGet(String httpUrl, Map<String, String> param, Map<String, String> header) throws HttpClientException
    {
        HttpGet httpGet = new HttpGet(httpUrl);
        StringBuilder paramBuilder = new StringBuilder("?");
        if (param != null)
        {
            for (String name : param.keySet())
            {
                paramBuilder.append(name).append("=").append(param.get(name)).append("&");
            }
        }
        paramBuilder.deleteCharAt(paramBuilder.length() - 1);
        return sendHttpGet(httpUrl + paramBuilder.toString(), header, CHARSET_UTF_8);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     */
    public HttpResponse sendHttpPost(String httpUrl, Map<String, String> header) throws HttpClientException
    {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost, null, header, null);
    }


    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl   地址
     * @param maps      参数
     * @param fileLists 附件
     */
    public HttpResponse sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) throws HttpClientException
    {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if (maps != null)
        {
            for (String key : maps.keySet())
            {
                meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
            }
        }
        if (fileLists != null)
        {
            for (File file : fileLists)
            {
                FileBody fileBody = new FileBody(file);
                meBuilder.addPart("files", fileBody);
            }
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost, null, maps, CHARSET_UTF_8);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public HttpResponse sendHttpPost(String httpUrl, String params, Map<String, String> header) throws HttpClientException
    {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try
        {
            // 设置参数
            if (params != null && params.trim().length() > 0)
            {
                StringEntity stringEntity = new StringEntity(params, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
                httpPost.setEntity(stringEntity);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost, null, header, null);
    }

    /**
     * 发送 post请求
     *
     * @param params 参数
     */
    public HttpResponse sendHttpPost(String httpUrl, Map<String, String> params, Map<String, String> header) throws HttpClientException
    {
        String param = convertStringParamter(params);
        return sendHttpPost(httpUrl, param, header);
    }

    /**
     * 发送post请求（body）
     *
     * @param httpUrl
     * @param payload
     * @param header
     * @return
     */
    public <T> HttpResponse sendHttpPost(String httpUrl, Payload<T> payload, Map<String, String> header) throws HttpClientException
    {
        HttpResponse response = null;
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        response = sendHttpPost(httpPost, payload, header, CHARSET_UTF_8);
        return response;
    }

    /**
     * @Author summer
     * @Description 下载
     * @Date 14:43 2023/10/27
     * @Param [httpUrl, httpMethod, payload, header]
     * @return byte[]
     **/
    public <T> byte[] download(String httpUrl, HttpMethod httpMethod, Payload<T> payload, Map<String, String> header)
    {
        CloseableHttpResponse response = null;
        byte[] downloadContent = null;
        HttpGet httpGet = null;
        HttpPost httpPost = null;
        try
        {
            switch (httpMethod)
            {
                case GET:
                {
                    httpGet = new HttpGet(httpUrl);
                    response = doGet(httpGet, header, CHARSET_UTF_8);
                    break;
                }
                case POST:
                {
                    httpPost = new HttpPost(httpUrl);
                    response = doPost(httpPost, payload, header, CHARSET_UTF_8);
                    break;
                }
                default:
                {
                    log.error("Not supported http method.");
                }
            }
            // 得到响应实例
            HttpEntity entity = response.getEntity();
            downloadContent = EntityUtils.toByteArray(entity);
            EntityUtils.consume(entity);
        }
        catch (Exception e)
        {
            if (null != httpPost)
            {
                httpPost.abort();
            }
            if (null != httpGet)
            {
                httpGet.abort();
            }
            log.error(e.getMessage(), e);
        }
        finally
        {
            try
            {
                // 释放资源
                if (response != null)
                {
                    response.close();
                }
                if (null != httpPost)
                {
                    httpPost.releaseConnection();
                }
                if (null != httpGet)
                {
                    httpGet.releaseConnection();
                }
            }
            catch (IOException e)
            {
                log.error(e.getMessage(), e);
            }
        }
        return downloadContent;
    }

    /**
     * @return org.apache.http.client.methods.CloseableHttpResponse
     * @Author summer
     * @Description 发起post请求
     * @Date 15:55 2023/10/26
     * @Param httpPost
     * @Param payload     请求body: json,text
     * @Param header      请求头信息
     * @Param charset     字符集
     **/
    private <T> CloseableHttpResponse doPost(HttpPost httpPost, Payload<T> payload, Map<String, String> header, String charset) throws HttpClientException
    {
        log.debug("Max:" + pool.getTotalStats().getMax());
        log.debug("Pending:" + pool.getTotalStats().getPending());
        log.debug("Available:" + pool.getTotalStats().getAvailable());
        log.debug("Leased:" + pool.getTotalStats().getLeased());
        CloseableHttpResponse response = null;
        try
        {
            httpPost.setConfig(requestConfig);
            // 配置请求头信息, 默认CONTENT_TYPE为application/json，可以通过设置请求头修改
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON_URL);
            if (null != header)
            {
                if (!header.containsKey(HttpHeaders.CONNECTION))
                {
                    httpPost.addHeader(HttpHeaders.CONNECTION, "keep-alive");
                }
                for (String key : header.keySet())
                {
                    log.debug("header: {}   value: {}", key, header.get(key));
                    httpPost.addHeader(key, header.get(key));
                }
            }
            else
            {
                httpPost.addHeader(HttpHeaders.CONNECTION, "keep-alive");
            }
            if (null != payload)
            {
                String jsonString = JsonUtil.javaObject2String(payload.getPayload());
                log.debug("payload =======>" + jsonString);
                // 解决中文乱码问题
                StringEntity stringEntity = new StringEntity(jsonString, charset);
                httpPost.setEntity(stringEntity);
            }
            // 执行请求
            response = this.client.execute(httpPost);
        }
        catch (Exception e)
        {
            httpPost.abort();
            log.error(e.getMessage(), e);
            throw new HttpClientException(e.getMessage(), e);
        }
        return response;
    }

    private CloseableHttpResponse doGet(HttpGet httpGet, Map<String, String> header, String charset)
    {
        CloseableHttpResponse response = null;
        try
        {
            // 配置请求信息
            httpGet.setConfig(requestConfig);
            httpGet.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_FORM_URL);
            if (null != header)
            {
                if (!header.containsKey(HttpHeaders.CONNECTION))
                {
                    httpGet.addHeader(HttpHeaders.CONNECTION, "keep-alive");
                }
                for (String key : header.keySet())
                {
                    httpGet.addHeader(key, header.get(key));
                }
            }
            else
            {
                httpGet.addHeader(HttpHeaders.CONNECTION, "keep-alive");
            }

            // 执行请求
            response = this.client.execute(httpGet);
        }
        catch (Exception e)
        {
            httpGet.abort();
            log.error(e.getMessage(), e);
        }
        return response;
    }

    /**
     * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
     *
     * @param parameterMap 需要转化的键值对集合
     * @return 字符串
     */
    public String convertStringParamter(Map<String, String> parameterMap)
    {
        StringBuilder parameterBuffer = new StringBuilder();

        if (parameterMap != null && parameterMap.size() > 0)
        {
            parameterMap.entrySet().stream().forEach(stringStringEntry -> {
                parameterBuffer.append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("&");
            });
            parameterBuffer.deleteCharAt(parameterBuffer.length() - 1);
        }
        return parameterBuffer.toString();
    }
}
