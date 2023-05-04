package cn.nesc.toolkit.es;

import cn.nesc.toolkit.es.common.Types;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OriginCreateIndexTest
{
    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test()
    {
        try
        {
            // Create the low-level client
            RestClientBuilder httpClientBuilder = RestClient.builder(
                    new HttpHost("10.6.22.187", 9200)
            );
            httpClientBuilder.setRequestConfigCallback(requestConfigBuilder -> {
                requestConfigBuilder.setConnectTimeout(2000);
                requestConfigBuilder.setSocketTimeout(2000);
                requestConfigBuilder.setConnectionRequestTimeout(2000);
                return requestConfigBuilder;
            });
            RestHighLevelClient client = new RestHighLevelClient(httpClientBuilder);
            //填充用户名密码
//            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("userName", "password"));
//
//            httpClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
//                httpAsyncClientBuilder.setMaxConnTotal(30);
//                httpAsyncClientBuilder.setMaxConnPerRoute(30);
//                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                return httpAsyncClientBuilder;
//            });
            String type = "create_db";
            String index = "test3";
            // setting 的值
            Map<String, Object> setmapping = new HashMap<>();
            // 分区数、副本数、缓存刷新时间
            setmapping.put("number_of_shards", 2);
            setmapping.put("number_of_replicas", 2);
            setmapping.put("refresh_interval", "5s");
            Map<String, Object> keyword = new HashMap<>();
            //设置类型
            keyword.put("type", Types.KEYWORD);
            Map<String, Object> lon = new HashMap<>();
            //设置类型
            lon.put("type", Types.LONG);
            Map<String, Object> date = new HashMap<>();
            //设置类型
            date.put("type", Types.DATE);
            date.put("format", "yyyy-MM-dd HH:mm:ss");

            Map<String, Object> jsonMap2 = new HashMap<>();
            Map<String, Object> properties = new HashMap<>();
            //设置字段message信息
            properties.put("uid", lon);
            properties.put("phone", lon);
            properties.put("msgcode", lon);
            properties.put("message", keyword);
            properties.put("sendtime", date);
            Map<String, Object> mapping = new HashMap<>();
            mapping.put("properties", properties);
            jsonMap2.put(type, mapping);

            GetIndexRequest getRequest = new GetIndexRequest();
            getRequest.indices(index);
            getRequest.local(false);
            getRequest.humanReadable(true);
            boolean exists2 = client.indices().exists(getRequest, RequestOptions.DEFAULT);
            //如果存在就不创建了
            if(exists2) {
                System.out.println(index+"索引库已经存在!");
                return;
            }
            // 开始创建库
            CreateIndexRequest request = new CreateIndexRequest(index);
            try {
                // 加载数据类型
                request.settings(setmapping);
                //设置mapping参数
                request.mapping(type, jsonMap2);
                //设置别名
                request.alias(new Alias("pancm_alias"));
                CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
                boolean falg = createIndexResponse.isAcknowledged();
                if(falg){
                    System.out.println("创建索引库:"+index+"成功！" );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    class PlainObject
    {
        private Integer id;
        private String code;
        private String name;
        private Date birthday;

        public Integer getId()
        {
            return id;
        }

        public void setId(Integer id)
        {
            this.id = id;
        }

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public Date getBirthday()
        {
            return birthday;
        }

        public void setBirthday(Date birthday)
        {
            this.birthday = birthday;
        }
    }

}
