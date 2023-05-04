/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: HLClientFactory
 * Author:   summer
 * Date:     2022/1/4 13:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es;

import cn.nesc.toolkit.common.json.JacksonValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

/**
 * @ClassName HLClientFactory
 * @Description
 * @Author summer
 * @Date 2022/1/4 13:25
 **/
public class HLClientFactory extends UsernamePasswordClientFactory
{


    @Override
    public IESClient getClient(int connectTimeout, int socketTimeout, int requestTimeout)
    {
        String [] nodeArray = this.nodes.split(",");
        HttpHost[] httpHosts = new HttpHost[nodeArray.length];
        for (int i = 0; i < nodeArray.length; i++)
        {
            String[] ipAndPort = nodeArray[i].split(":");
            if (ipAndPort.length < 2)
            {
                throw new ParseException("Elastic server address error. Current address is " + nodeArray[i] + ". Correct format is 127.0.0.1:9200");
            }
            httpHosts[i] = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
        }
        // Create the low-level client
        RestClientBuilder httpClientBuilder = RestClient.builder(httpHosts);
        httpClientBuilder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeout);
            requestConfigBuilder.setSocketTimeout(socketTimeout);
            requestConfigBuilder.setConnectionRequestTimeout(requestTimeout);
            return requestConfigBuilder;
        });

        doCredential(httpClientBuilder);
        IESClient client = new HighLevelClient(httpClientBuilder, true, new JacksonValidator());
        return client;
    }

    @Override
    public IESClient getClient()
    {
        String [] nodeArray = this.nodes.split(",");
        HttpHost[] httpHosts = new HttpHost[nodeArray.length];
        for (int i = 0; i < nodeArray.length; i++)
        {
            String[] ipAndPort = nodeArray[i].split(":");
            if (ipAndPort.length < 2)
            {
                throw new ParseException("Elastic server address error. Current address is " + nodeArray[i] + ". Correct format is 127.0.0.1:9200");
            }
            httpHosts[i] = new HttpHost(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
        }
        // Create the low-level client
        RestClientBuilder httpClientBuilder = RestClient.builder(httpHosts);
        httpClientBuilder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(30000);
            requestConfigBuilder.setSocketTimeout(30000);
            requestConfigBuilder.setConnectionRequestTimeout(30000);
            return requestConfigBuilder;
        });

        doCredential(httpClientBuilder);
        IESClient client = new HighLevelClient(httpClientBuilder, true, new JacksonValidator());
        return client;
    }

    /**
     * @Author summer
     * @Description 登录验证
     * @Date 11:16 2022/5/10
     * @Param httpClientBuilder
     * @return void
     **/
    private void doCredential(RestClientBuilder httpClientBuilder)
    {
        if (null != this.credential && StringUtils.isNotEmpty(this.credential.getUserName()) && StringUtils.isNotEmpty(this.credential.getPassword()))
        {
            //填充用户名密码
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.credential.getUserName(), this.credential.getPassword()));
            httpClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                httpAsyncClientBuilder.setMaxConnTotal(30);
                httpAsyncClientBuilder.setMaxConnPerRoute(30);
                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                return httpAsyncClientBuilder;
            });
        }
    }


}
