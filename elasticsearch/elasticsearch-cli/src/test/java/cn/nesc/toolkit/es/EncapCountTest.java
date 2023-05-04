package cn.nesc.toolkit.es;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncapCountTest
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
            long count = client.countForSync("idx_cust", QueryBuilders.matchAllQuery());
            System.out.println("count ==========>" + count);

            AbstractQueryBuilder queryBuilder = QueryBuilders.simpleQueryStringQuery("101* + 刘 + 长春").field("custNo").field("custName").field("orgName");      // 多字段模糊查询
            client.countForAsync("idx_cust", queryBuilder, new ActionListener<CountResponse>()
            {
                @Override
                public void onResponse(CountResponse countResponse)
                {
                    System.out.println("async count" + countResponse.getCount());
                }

                @Override
                public void onFailure(Exception e)
                {
                    System.out.println(e.getCause());
                }
            });
            for (;;){}
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

    }


}
