/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: ItrusBaseResult
 * Author:   summer
 * Date:     2022/9/5 15:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.itruscloud.parameter;

/**
 * @ClassName ItrusBaseResult
 * @Description
 * @Author summer
 * @Date 2022/9/5 15:03
 **/
public class ItrusBaseParam
{
    protected String apiId;

    protected String timestamp = "" + System.currentTimeMillis();

    protected String apiVersion = "1";

    public String getApiId()
    {
        return apiId;
    }

    public void setApiId(String apiId)
    {
        this.apiId = apiId;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getApiVersion()
    {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion)
    {
        this.apiVersion = apiVersion;
    }
}
