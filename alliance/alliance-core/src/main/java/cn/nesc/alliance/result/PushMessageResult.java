/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: AccessTokenResult
 * Author:   summer
 * Date:     2022/6/20 15:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.alliance.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName AccessTokenResult
 * @Description
 * @Author summer
 * @Date 2022/6/20 15:35
 **/
public class PushMessageResult extends AlliBaseResult
{
    @JsonProperty("jobid")
    private String jobId;

    public String getJobId()
    {
        return jobId;
    }

    public void setJobId(String jobId)
    {
        this.jobId = jobId;
    }
}
