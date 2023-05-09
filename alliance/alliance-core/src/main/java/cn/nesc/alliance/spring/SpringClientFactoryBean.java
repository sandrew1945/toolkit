/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: SpringClientFactoryBean
 * Author:   summer
 * Date:     2022/5/10 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.alliance.spring;

import cn.nesc.alliance.AllianceFactory;
import org.springframework.context.ApplicationEvent;


/**
 * @ClassName SpringClientFactoryBean
 * @Description
 * @Author summer
 * @Date 2022/5/10 14:25
 **/
public class SpringClientFactoryBean
{
    private AllianceFactory allianceFactory;

    private String allianceUrl;

    private String agentId;

    private String corpId;

    private String corpSecret;

    private boolean dummySend;


    public void onApplicationEvent(ApplicationEvent applicationEvent)
    {

    }

    public AllianceFactory getObject() throws Exception
    {
        allianceFactory = new AllianceFactory();
        allianceFactory.setAllianceUrl(allianceUrl);
        allianceFactory.setAgentId(agentId);
        allianceFactory.setCorpId(corpId);
        allianceFactory.setCorpSecret(corpSecret);
        allianceFactory.setDummySend(dummySend);
        return allianceFactory;
    }

    public Class<?> getObjectType()
    {
        return this.allianceFactory == null ? AllianceFactory.class : this.allianceFactory.getClass();
    }

    public boolean isSingleton()
    {
        return true;
    }

    public void afterPropertiesSet() throws Exception
    {

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

    public boolean isDummySend()
    {
        return dummySend;
    }

    public void setDummySend(boolean dummySend)
    {
        this.dummySend = dummySend;
    }
}
