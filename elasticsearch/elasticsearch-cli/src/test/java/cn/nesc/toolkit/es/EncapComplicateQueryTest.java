package cn.nesc.toolkit.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncapComplicateQueryTest
{
    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test()
    {
        try
        {
            UsernamePasswordClientFactory clientFactory = new HLClientFactory();
            clientFactory.setNodes("10.6.22.187:9200");
            IESClient client = clientFactory.getClient();

//            AbstractQueryBuilder queryBuilder = QueryBuilders.matchQuery("custNo", "68");     // 精确查询
//            AbstractQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("custNo", "*318*");     // 模糊查询

            /**
             *  '+' signifies AND operation
             *  '|' signifies OR operation
             *  '-' negates a single token " wraps a number of tokens to signify a phrase for searching
             *  '*' at the end of a term signifies a prefix query
             **/
            AbstractQueryBuilder queryBuilder = QueryBuilders.simpleQueryStringQuery("101* + 刘 + 长春").field("custNo").field("custName").field("orgName");      // 多字段模糊查询
            SearchResponse response = client.queryForCustom("idx_cust", 100, queryBuilder, null);
            System.out.println(response.getHits());
            System.out.println(response.getHits().getHits().length);
            SearchHit[] searchHits = response.getHits().getHits();
            for (SearchHit searchHit : searchHits)
            {
                System.out.println("====>" + searchHit.getSourceAsString());
            }
            System.out.println(response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
