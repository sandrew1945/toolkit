package cn.nesc.toolkit.es;

import cn.nesc.toolkit.es.common.ESField;
import cn.nesc.toolkit.es.common.properties.ESDate;
import cn.nesc.toolkit.es.common.properties.ESDouble;
import cn.nesc.toolkit.es.common.properties.ESText;
import cn.nesc.toolkit.es.model.DmnCustTgptapi1and2PO;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.SqlSessionFactoryBuilder;
import com.sandrew.bury.callback.POCallBack;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EncapBatchInsertListenerTest
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
            // 创建索引
            List<ESField> fields = new ArrayList<>();
            fields.add(new ESField("cust_no", new ESText()));
            fields.add(new ESField("cust_name", new ESText()));
            fields.add(new ESField("org_no", new ESText()));
            fields.add(new ESField("org_name", new ESText()));
            fields.add(new ESField("open_dt", new ESText()));
            fields.add(new ESField("all_zc", new ESDouble()));
            fields.add(new ESField("all_zc_avg", new ESDouble()));
            fields.add(new ESField("zc_xj", new ESDouble()));
            fields.add(new ESField("all_zc_max", new ESDouble()));
            fields.add(new ESField("all_zc_max_dt", new ESDate()));
            fields.add(new ESField("sz_out", new ESDouble()));
            fields.add(new ESField("sz_in", new ESDouble()));
            fields.add(new ESField("sz_gjl", new ESDouble()));
            fields.add(new ESField("gjjyl_cn_y", new ESDouble()));
            fields.add(new ESField("jjjyl_cn_y", new ESDouble()));
            fields.add(new ESField("jjjyl_cw_y", new ESDouble()));
            fields.add(new ESField("prd_y", new ESDouble()));
            fields.add(new ESField("gjjypc_cn_m", new ESText()));
            fields.add(new ESField("gjjypc_cw_m", new ESText()));
            fields.add(new ESField("gjjyjl", new ESText()));
            fields.add(new ESField("cpby_y", new ESDouble()));
            fields.add(new ESField("ret_date", new ESText()));
            fields.add(new ESField("syl_m", new ESText()));
            fields.add(new ESField("syl_gp_m", new ESText()));
            fields.add(new ESField("syl_gmjj_m", new ESText()));
            fields.add(new ESField("syl_lc_m", new ESText()));
            fields.add(new ESField("syl_hy", new ESText()));
            fields.add(new ESField("syl_gp_hy", new ESText()));
            fields.add(new ESField("syl_gmjj_hy", new ESText()));
            fields.add(new ESField("syl_lc_hy", new ESText()));
            fields.add(new ESField("syl_y", new ESText()));
            fields.add(new ESField("syl_gp_y", new ESText()));
            fields.add(new ESField("syl_gmjj_y", new ESText()));
            fields.add(new ESField("syl_lc_y", new ESText()));
            fields.add(new ESField("khcs_y", new ESDouble()));
            fields.add(new ESField("data_biz_date", new ESText()));
            fields.add(new ESField("service_relation", new ESText()));
            fields.add(new ESField("assess_relation", new ESText()));
            fields.add(new ESField("sale_relation", new ESText()));
            fields.add(new ESField("norm_dev_relation", new ESText()));
            fields.add(new ESField("cred_dev_relation", new ESText()));
            fields.add(new ESField("opt_dev_relation", new ESText()));
            fields.add(new ESField("inv_advr_sign", new ESText()));
            fields.add(new ESField("pd_advr_sign", new ESText()));
            fields.add(new ESField("service_relation_no", new ESText()));
            fields.add(new ESField("agfin_pd_inc_serv_incm", new ESDouble()));
            fields.add(new ESField("net_cms_incm", new ESDouble()));
            fields.add(new ESField("int_incm", new ESDouble()));
            fields.add(new ESField("fund_code", new ESText()));
            fields.add(new ESField("stock_code", new ESText()));
            CreateIndexResponse response = client.createIndex("idx_cust", 2, 1, fields);
            log.debug("isAcknowledged --->" + response.isAcknowledged());
            log.debug("isShardsAcknowledged --->" + response.isShardsAcknowledged());
            log.debug("" + response);

            String configFile = "bury-config.xml";
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(configFile);
            Session session = factory.openSession();
            String sql = "SELECT * FROM DMN_CUST_TGPTAPI1AND2";
            List<DmnCustTgptapi1and2PO> list = session.select(sql, null, new POCallBack(DmnCustTgptapi1and2PO.class));
//            list.stream().forEach(dmnCustTgptapi1and2PO -> {
//                System.out.println(dmnCustTgptapi1and2PO.getCustName());
//                System.out.println(dmnCustTgptapi1and2PO.getAllZc());
//                System.out.println(dmnCustTgptapi1and2PO.getFundCode());
//            });
            /********* java对象创建 **********/
            client.addDoc("idx_cust", list, 5000, (executionId, bulkRequest) -> {
                System.out.println("before:" + executionId);
                System.out.println("before:" + bulkRequest.numberOfActions());
            }, (executionId, bulkRequest, bulkResponse) -> {
                System.out.println("after:" + executionId);
                System.out.println("after:" + bulkRequest);
                System.out.println("after:" + bulkResponse.getItems()[0].getOpType());
            }, (executionId, bulkRequest, failure) -> {
                System.out.println("exception:" + executionId);
                System.out.println("exception:" + bulkRequest);
                System.out.println("exception:" + failure);
            });
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

    }

}
