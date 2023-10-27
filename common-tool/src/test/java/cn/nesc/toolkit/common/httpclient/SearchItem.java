/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: SearchItem
 * Author:   summer
 * Date:     2023/10/25 13:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.httpclient;

/**
 * @ClassName SearchItem
 * @Description
 * @Author summer
 * @Date 2023/10/25 13:53
 **/
public class SearchItem
{
    private String tableIdCode;

    private String tableCnName;

    private String tableName;

    public String getTableIdCode()
    {
        return tableIdCode;
    }

    public void setTableIdCode(String tableIdCode)
    {
        this.tableIdCode = tableIdCode;
    }

    public String getTableCnName()
    {
        return tableCnName;
    }

    public void setTableCnName(String tableCnName)
    {
        this.tableCnName = tableCnName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }
}
