/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: SearchPayload
 * Author:   summer
 * Date:     2023/10/25 13:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.httpclient;

/**
 * @ClassName SearchPayload
 * @Description
 * @Author summer
 * @Date 2023/10/25 13:39
 **/
public class DownloadPayload
{
    private String[] tableIdCodes;

    public DownloadPayload(String[] tableIdCodes)
    {
        this.tableIdCodes = tableIdCodes;
    }


    public String[] getTableIdCodes()
    {
        return tableIdCodes;
    }

    public void setTableIdCodes(String[] tableIdCodes)
    {
        this.tableIdCodes = tableIdCodes;
    }
}
