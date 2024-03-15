package cn.nesc.itruscloud;

import cn.nesc.itruscloud.contract.ContractHandler;

/**
 * @ClassName AllianceFactory
 * @Description
 * @Author summer
 * @Date 2022/6/20 14:39
 **/
public class ItrusCloudFactory
{
    private String itrusUrl;

    private String apiId;

    private String apiSecret;

    private Boolean useCache;

    public ContractHandler getContractHandler()
    {
        ContractHandler contractHandler = new ContractHandler();
        contractHandler.setItrusUrl(this.itrusUrl);
        contractHandler.setApiId(this.apiId);
        contractHandler.setApiSecret(this.apiSecret);
        contractHandler.setUseCache(useCache);
        return contractHandler;
    }

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

    public Boolean getUseCache()
    {
        return useCache;
    }

    public void setUseCache(Boolean useCache)
    {
        this.useCache = useCache;
    }
}
