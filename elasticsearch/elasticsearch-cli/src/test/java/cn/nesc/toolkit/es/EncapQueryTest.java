package cn.nesc.toolkit.es;

import cn.nesc.toolkit.es.common.sort.ASCSorter;
import cn.nesc.toolkit.es.common.sort.DESCSorter;
import cn.nesc.toolkit.es.common.sort.Sorter;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncapQueryTest
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
            Sorter codeSorter = new ASCSorter("custName", true);
            Sorter zcSorter = new DESCSorter("allZcAvg");
            SearchResponse response = client.query("idx_cust", 100, codeSorter, zcSorter);
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
