/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: DmnCustTgptapi1and2BO
 * Author:   summer
 * Date:     2022/1/10 15:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @ClassName DmnCustTgptapi1and2BO
 * @Description
 * @Author summer
 * @Date 2022/1/10 15:29
 **/
public class DmnCustTgptapi1and2BO
{
    @JsonProperty(value = "cust_no")
    private String custNo;

    @JsonProperty(value = "cust_name")
    private String custName;

    @JsonProperty(value = "org_no")
    private String orgNo;

    @JsonProperty(value = "org_name")
    private String orgName;

    @JsonProperty(value = "open_dt")
    private String openDt;

    @JsonProperty(value = "all_zc")
    private Double allZc;

    @JsonProperty(value = "all_zc_avg")
    private Double allZcAvg;

    @JsonProperty(value = "zc_xj")
    private Double zcXj;

    @JsonProperty(value = "all_zc_max")
    private Double allZcMax;

    @JsonProperty(value = "all_zc_max_dt")
    private Date allZcMaxDt;

    @JsonProperty(value = "sz_out")
    private Double szOut;

    @JsonProperty(value = "sz_in")
    private Double szIn;

    @JsonProperty(value = "sz_gjl")
    private Double szGjl;

    @JsonProperty(value = "gjjyl_cn_y")
    private Double gjjylCnY;

    @JsonProperty(value = "jjjyl_cn_y")
    private Double jjjylCnY;

    @JsonProperty(value = "jjjyl_cw_y")
    private Double jjjylCwY;

    @JsonProperty(value = "prd_y")
    private Double prdY;

    @JsonProperty(value = "gjjypc_cn_m")
    private String gjjypcCnM;

    @JsonProperty(value = "gjjypc_cw_m")
    private String gjjypcCwM;

    @JsonProperty(value = "gjjyjl")
    private String gjjyjl;

    @JsonProperty(value = "cpby_y")
    private Double cpbyY;

    @JsonProperty(value = "ret_date")
    private String retDate;

    @JsonProperty(value = "syl_m")
    private String sylM;

    @JsonProperty(value = "syl_gp_m")
    private String sylGpM;

    @JsonProperty(value = "syl_gmjj_m")
    private String sylGmjjM;

    @JsonProperty(value = "syl_lc_m")
    private String sylLcM;

    @JsonProperty(value = "syl_hy")
    private String sylHy;

    @JsonProperty(value = "syl_gp_hy")
    private String sylGpHy;

    @JsonProperty(value = "syl_gmjj_hy")
    private String sylGmjjHy;

    @JsonProperty(value = "syl_lc_hy")
    private String sylLcHy;

    @JsonProperty(value = "syl_y")
    private String sylY;

    @JsonProperty(value = "syl_gp_y")
    private String sylGpY;

    @JsonProperty(value = "syl_gmjj_y")
    private String sylGmjjY;

    @JsonProperty(value = "syl_lc_y")
    private String sylLcY;

    @JsonProperty(value = "khcs_y")
    private Double khcsY;

    @JsonProperty(value = "data_biz_date")
    private String dataBizDate;

    @JsonProperty(value = "service_relation")
    private String serviceRelation;

    @JsonProperty(value = "assess_relation")
    private String assessRelation;

    @JsonProperty(value = "sale_relation")
    private String saleRelation;

    @JsonProperty(value = "norm_dev_relation")
    private String normDevRelation;

    @JsonProperty(value = "cred_dev_relation")
    private String credDevRelation;

    @JsonProperty(value = "opt_dev_relation")
    private String optDevRelation;

    @JsonProperty(value = "inv_advr_sign")
    private String invAdvrSign;

    @JsonProperty(value = "pd_advr_sign")
    private String pdAdvrSign;

    @JsonProperty(value = "service_relation_no")
    private String serviceRelationNo;

    @JsonProperty(value = "agfin_pd_inc_serv_incm")
    private Double agfinPdIncServIncm;

    @JsonProperty(value = "net_cms_incm")
    private Double netCmsIncm;

    @JsonProperty(value = "int_incm")
    private Double intIncm;

    @JsonProperty(value = "fund_code")
    private String fundCode;

    @JsonProperty(value = "stock_code")
    private String stockCode;
}
