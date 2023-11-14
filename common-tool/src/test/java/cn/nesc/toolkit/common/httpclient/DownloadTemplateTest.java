package cn.nesc.toolkit.common.httpclient;

import cn.nesc.toolkit.common.json.JsonUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadTemplateTest
{
    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test()
    {
        try
        {
            List<String> tabs = new ArrayList<>();
            tabs.add("t_hth_agmt_other");
//            tabs.add("t_istu_ins_cust_cms");
//            tabs.add("t_ins_cust_sys_proj");
//            tabs.add("t_pb_serv_agmt_flow");
//            tabs.add("t_cust_cms_adj_dtl");
//            tabs.add("t_pte_cust_pool_tmp");
//            tabs.add("t_cust_rel_th_tmp");
//            tabs.add("t_cust_linkman_th_tmp");
//            tabs.add("t_manager_info");
//            tabs.add("t_product_info");
//            tabs.add("t_system_dictionary");
//            tabs.add("t_pte_lable");
//            tabs.add("t_fudt_atta");
//            tabs.add("t_pte_cust_info");
//            tabs.add("t_cust_cms_adj_dtl_log");
//            tabs.add("t_mkt_cms_actl_data");
//            tabs.add("t_mkt_cms_esti_data");
//            tabs.add("t_mkt_cms_actl_data_tmp");
//            tabs.add("t_cust_rgn_rela");
//            tabs.add("t_competition_info");
//            tabs.add("t_eml_matn");
//            tabs.add("t_cntr_udtk_rat_info");
//            tabs.add("t_prsn_role_jour");
//            tabs.add("t_pro_base_info_th_jour");
//            tabs.add("t_tel_serv");
//            tabs.add("t_istu_week_jour");
//            tabs.add("t_dept_mngr_jour");
//            tabs.add("t_busi_tp_map_rela_jour");
//            tabs.add("dm_icdata_pb_pd_busi_incm_dd_tmp");
//            tabs.add("t_pb_pd_busi_incm");
//            tabs.add("t_meet_sys_exec_info");
//            tabs.add("t_per_city_meet");
//            tabs.add("t_busi_client_anly");
//            tabs.add("t_cmop_info_anly");
//            tabs.add("t_co_busi_info_anly");
//            tabs.add("t_ins_cust_co_incm_yr_anly_dtl");
//            tabs.add("t_cont");
//            tabs.add("d_contract_opposite");
//            tabs.add("d_contract_our_parties");
            tabs.stream().forEach(tabName -> downloadTemplete(tabName));
//            downloadTemplete("t_pro_base_info_th");
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

    }

    private void downloadTemplete(String tableName)
    {
        try
        {
            HttpClientUtil clientUtil = HttpClientUtil.getInstance();
            // Search
            SearchPayload searchPayload = new SearchPayload();
            searchPayload.setKeyword(tableName);
            searchPayload.setPageNum(1);
            searchPayload.setPageSize(30);

            Map<String, String> searchHeader = new HashMap<>();
            searchHeader.put("Content-Type", "application/json");
            searchHeader.put("Cookie","");
            searchHeader.put("Host", "192.18.27.41:11062");
            searchHeader.put("Origin", "http://192.18.27.41:11062");
            searchHeader.put("Referer", "http://192.18.27.41:11062/easydmap/metamanage/list?accountId=16&clusterId=easyops-cluster&timeamp=1697436775557");
            HttpResponse searchHttpResponse = clientUtil.sendHttpPost("http://192.18.27.41:11062/dataMap_server/api/datamap/logic-basic-data/table/list?product=happytest&accountId=6&clusterId=easyops-cluster&groupId=1", new Payload<SearchPayload>(searchPayload), searchHeader);
            SearchResponse searchResponse = JsonUtil.string2JavaObject(searchHttpResponse.getReturnContent(), SearchResponse.class);
            System.out.println(searchResponse);

            // Download
            if (searchResponse.getCode() == 200 && searchResponse.getResult().getTotal() == 1)
            {
                // 只需要处理一条
                String tableIdCode = searchResponse.getResult().getItems().get(0).getTableIdCode();
//                log.debug("tableIdCode =========> {}", tableIdCode);
//                String downloadPayload = "{\"tableIdCodes\":[\""+tableIdCode+"\"]}";
                DownloadPayload downloadPayload = new DownloadPayload(new String[]{tableIdCode});
                byte[] fileBytes = clientUtil.download("http://192.18.27.41:11062/dataMap_server/api/datamap/template/data/download?product=happytest&accountId=6&clusterId=easyops-cluster&groupId=1", HttpMethod.POST, new Payload<DownloadPayload>(downloadPayload), searchHeader);
//                log.debug("fileBytes size : {}", fileBytes.length);
//                log.debug(new String(fileBytes));

                File downloadFile = new File("/Users/summer/Desktop/templates/" + tableName + ".xlsx");
                Files.write(downloadFile.toPath(), fileBytes);
            }
            else if (searchResponse.getCode() == 200 && searchResponse.getResult().getTotal() > 1)
            {
                // 多条结果，需要再次定位
                searchResponse.getResult().getItems().stream().filter(searchItem -> searchItem.getTableName().equals(tableName)).findFirst().ifPresent(searchItem -> {
//                    log.debug("tableIdCode =========> {}", searchItem.getTableIdCode());
//                    String downloadPayload = "{\"tableIdCodes\":[\""+searchItem.getTableIdCode()+"\"]}";
                    DownloadPayload downloadPayload = new DownloadPayload(new String[]{searchItem.getTableIdCode()});
                    byte[] fileBytes = clientUtil.download("http://192.18.27.41:11062/dataMap_server/api/datamap/template/data/download?product=happytest&accountId=6&clusterId=easyops-cluster&groupId=1", HttpMethod.POST, new Payload<DownloadPayload>(downloadPayload), searchHeader);
//                    log.debug("fileBytes size : {}", fileBytes.length);
//                    log.debug(new String(fileBytes));

                    File downloadFile = new File("/Users/summer/Desktop/templates/" + tableName + ".xlsx");
                    try
                    {
                        Files.write(downloadFile.toPath(), fileBytes);
                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                });
            }
            else
            {
                log.error("Table:" + tableName + "下载错误");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


}
