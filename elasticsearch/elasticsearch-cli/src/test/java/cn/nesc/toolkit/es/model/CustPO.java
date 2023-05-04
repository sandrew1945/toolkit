/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: CustPO
 * Author:   summer
 * Date:     2022/5/12 10:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.model;

/**
 * @ClassName CustPO
 * @Description
 * @Author summer
 * @Date 2022/5/12 10:42
 **/
public class CustPO
{
    private String custNo;
    private String custName;
    private String orgNo;
    private String orgName;
    private String openDt;

    public String getCustNo()
    {
        return custNo;
    }

    public void setCustNo(String custNo)
    {
        this.custNo = custNo;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getOrgNo()
    {
        return orgNo;
    }

    public void setOrgNo(String orgNo)
    {
        this.orgNo = orgNo;
    }

    public String getOrgName()
    {
        return orgName;
    }

    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getOpenDt()
    {
        return openDt;
    }

    public void setOpenDt(String openDt)
    {
        this.openDt = openDt;
    }
}
