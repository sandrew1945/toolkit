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

package cn.nesc.itruscloud.spring;

import cn.nesc.itruscloud.ItrusCloudFactory;
import org.springframework.context.ApplicationEvent;


/**
 * @ClassName SpringClientFactoryBean
 * @Description
 * @Author summer
 * @Date 2022/5/10 14:25
 **/
public class SpringClientFactoryBean
{
    private ItrusCloudFactory itrusCloudFactory;

    private String itrusUrl;

    private String apiId;

    private String apiSecret;

    private Boolean useCache;


    public void onApplicationEvent(ApplicationEvent applicationEvent)
    {

    }

    public ItrusCloudFactory getObject() throws Exception
    {
        itrusCloudFactory = new ItrusCloudFactory();
        itrusCloudFactory.setItrusUrl(this.itrusUrl);
        itrusCloudFactory.setApiId(this.apiId);
        itrusCloudFactory.setApiSecret(this.apiSecret);
        itrusCloudFactory.setUseCache(this.useCache);
        return itrusCloudFactory;
    }

    public Class<?> getObjectType()
    {
        return this.itrusCloudFactory == null ? ItrusCloudFactory.class : this.itrusCloudFactory.getClass();
    }

    public boolean isSingleton()
    {
        return true;
    }

    public void afterPropertiesSet() throws Exception
    {

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
