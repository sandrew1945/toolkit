package cn.nesc.toolkit.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OriginEsClientTest
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

            // Create the HLRC
            RestHighLevelClient client = new RestHighLevelClient(httpClientBuilder);

            String index = "test1";
            String type = "_doc";
            // 唯一编号
            String id = "3";
            IndexRequest request = new IndexRequest(index, type);

            SearchRequest sr = new SearchRequest();
            sr.indices("test1");
            sr.types("_doc");
            SearchResponse response = client.search(sr, RequestOptions.DEFAULT);


            log.debug("" + response);
            log.debug("===========================");
            log.debug("count:" + response.getHits().getTotalHits().value);
            log.debug("sources:" + response.getHits().getHits().length);
            log.debug("source:" + response.getHits().getHits()[0].getSourceAsString());

//            String jsonString = "{" + "\"uid\":\"1235\","+ "\"phone\":\"12345678901\","+ "\"msgcode\":\"2\"," + "\"sendtime\":\"2019-01-14 01:57:04\","
//                    + "\"message\":\"xeniqwneennn qwen\"" + "}";

            String jsonString = "{" + "\"uid\":\"12dd35\","+ "\"mobile\":\"12345678988\","+ "\"code\":\"4\"," + "\"message\":\"xeniqwneennn qwen\"" + "}";
            request.source(jsonString, XContentType.JSON);
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            log.debug("" + indexResponse);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
