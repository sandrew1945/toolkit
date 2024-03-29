package cn.nesc.alliance.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by summer on 2019/8/7.
 */
@ConfigurationProperties(value = "spring.toolkit.alliance", ignoreInvalidFields = true)
public class AllianceConfigurePerproties
{
    private String allianceUrl;

    private String agentId;

    private String corpId;

    private String corpSecret;

    private boolean dummySend = true;

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
