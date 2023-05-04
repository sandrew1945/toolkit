/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: BaseMessageBody
 * Author:   summer
 * Date:     2022/6/20 15:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.alliance.message;

/**
 * @ClassName BaseMessageBody
 * @Description
 * @Author summer
 * @Date 2022/6/20 15:50
 **/
public class BaseMessageBody
{

    protected String touser;

    protected String toparty;

    protected String totag;

    protected String agentid;

    public String getTouser()
    {
        return touser;
    }

    public void setTouser(String touser)
    {
        this.touser = touser;
    }

    public String getToparty()
    {
        return toparty;
    }

    public void setToparty(String toparty)
    {
        this.toparty = toparty;
    }

    public String getTotag()
    {
        return totag;
    }

    public void setTotag(String totag)
    {
        this.totag = totag;
    }

    public String getAgentid()
    {
        return agentid;
    }

    public void setAgentid(String agentid)
    {
        this.agentid = agentid;
    }
}
