package cn.nesc.itruscloud.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by summer on 2019/8/7.
 */
@ConfigurationProperties(prefix = "spring.toolkit.itrus", ignoreInvalidFields = true)
public class ItruscloudConfigurePerproties
{
    private String itrusUrl;

    private String apiId;

    private String apiSecret;

    private boolean useCache;

    public String getItrusUrl()
    {
        return itrusUrl;
    }

    public void setItrusUrl(String itrusUrl)
    {
        this.itrusUrl = itrusUrl;
    }

    public String getApiId()
    {
        return apiId;
    }

    public void setApiId(String apiId)
    {
        this.apiId = apiId;
    }

    public String getApiSecret()
    {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;
    }

    public boolean isUseCache()
    {
        return useCache;
    }

    public void setUseCache(boolean useCache)
    {
        this.useCache = useCache;
    }
}
