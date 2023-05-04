package cn.nesc.itruscloud.springboot.autoconfigure;


import cn.nesc.itruscloud.ItrusCloudFactory;
import cn.nesc.itruscloud.spring.SpringClientFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author summer
 * @Description
 * @Date 13:17 2022/9/7
 * @Param 
 * @return 
 **/
@Configuration
@ConditionalOnClass({ SpringClientFactoryBean.class })
@EnableConfigurationProperties(ItruscloudConfigurePerproties.class)
public class ItruscloudAutoConfiguration
{
    private ItruscloudConfigurePerproties configurePerproties;

    public ItruscloudAutoConfiguration(ItruscloudConfigurePerproties configurePerproties)
    {
        this.configurePerproties = configurePerproties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ItrusCloudFactory itrusCloudFactory() throws Exception
    {
        SpringClientFactoryBean factory = new SpringClientFactoryBean();
        factory.setItrusUrl(this.configurePerproties.getItrusUrl());
        factory.setApiId(this.configurePerproties.getApiId());
        factory.setApiSecret(this.configurePerproties.getApiSecret());
        factory.setUseCache(this.configurePerproties.isUseCache());
        return factory.getObject();
    }
}
