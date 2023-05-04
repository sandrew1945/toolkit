/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: AllianceFactory
 * Author:   summer
 * Date:     2022/6/20 14:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.alliance;

import cn.nesc.alliance.message.MessagePusher;

/**
 * @ClassName AllianceFactory
 * @Description
 * @Author summer
 * @Date 2022/6/20 14:39
 **/
public class AllianceFactory
{
    private String allianceUrl;

    private String agentId;

    private String corpId;

    private String corpSecret;

    public MessagePusher getMessagePusher()
    {
        MessagePusher messagePusher = new MessagePusher();
        messagePusher.setAgentId(agentId);
        messagePusher.setAllianceUrl(allianceUrl);
        messagePusher.setCorpId(corpId);
        messagePusher.setCorpSecret(corpSecret);
        return messagePusher;
    }

    public String getAllianceUrl()
    {
        return allianceUrl;
    }

    public void setAllianceUrl(String allianceUrl)
    {
        this.allianceUrl = allianceUrl;
    }

    public String getAgentId()
    {
        return agentId;
    }

    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
    }

    public String getCorpId()
    {
        return corpId;
    }

    public void setCorpId(String corpId)
    {
        this.corpId = corpId;
    }

    public String getCorpSecret()
    {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret)
    {
        this.corpSecret = corpSecret;
    }
}
