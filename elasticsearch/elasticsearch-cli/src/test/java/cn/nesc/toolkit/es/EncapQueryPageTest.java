package cn.nesc.toolkit.es;

import cn.nesc.toolkit.es.common.ESCallback;
import cn.nesc.toolkit.es.common.ESPageResult;
import cn.nesc.toolkit.es.common.sort.ASCSorter;
import cn.nesc.toolkit.es.common.sort.DESCSorter;
import cn.nesc.toolkit.es.common.sort.Sorter;
import cn.nesc.toolkit.es.model.CustPO;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Map;

public class EncapQueryPageTest
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

//            AbstractQueryBuilder queryBuilder = new MatchQueryBuilder("user", "kimchy");

            SearchResponse response = client.pageQuery("idx_cust", 1, 5, null, codeSorter, zcSorter);
            System.out.println(response.getHits());
            System.out.println(response.getHits().getHits().length);
            SearchHit[] searchHits = response.getHits().getHits();
            for (SearchHit searchHit : searchHits)
            {
                System.out.println("====>" + searchHit.getSourceAsString());
            }
            System.out.println(response);

            ESPageResult<CustPO> pageResult = client.pageQuery("idx_cust", 1, 5, null, CustPO.class, codeSorter, zcSorter);
            System.out.println(pageResult);


            ESPageResult<CustPO> pageResult2 = client.pageQuery("idx_cust", 1, 5, null, new ESCallback<CustPO>()
            {
                @Override
                public CustPO wrapper(SearchHit searchHit) throws SQLException
                {
                    Map<String, Object> map = searchHit.getSourceAsMap();
                    CustPO cust = new CustPO();
                    cust.setCustName(map.get("custName").toString());
                    return cust;
                }
            }, codeSorter, zcSorter);
            System.out.println(pageResult2);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
