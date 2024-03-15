package cn.nesc.shiro.spring.boot;

/**
 * @ClassName IDAAS
 * @Description
 * @Author summer
 * @Date 2023/12/6 15:55
 **/
public class IDAAS
{

    private String idaasUrl;
    private String loginUrl;    // idaas登录url
    private String clientId;
    private String clientSecret;
    private String apiKey;
    private String apiSecret;
    private String redirectURL;

    public String getIdaasUrl()
    {
        return idaasUrl;
    }

    public void setIdaasUrl(String idaasUrl)
    {
        this.idaasUrl = idaasUrl;
    }

    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getClientSecret()
    {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getApiSecret()
    {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;
    }

    public String getRedirectURL()
    {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL)
    {
        this.redirectURL = redirectURL;
    }
}
