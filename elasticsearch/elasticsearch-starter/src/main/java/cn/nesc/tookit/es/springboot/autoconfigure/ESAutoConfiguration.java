package cn.nesc.tookit.es.springboot.autoconfigure;

import cn.nesc.toolkit.es.IESClientFactory;
import cn.nesc.toolkit.es.spring.SpringClientFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by summer on 2019/8/7.
 */
@Configuration
@ConditionalOnClass({ SpringClientFactoryBean.class })
@EnableConfigurationProperties(ESConfigurePerproties.class)
public class ESAutoConfiguration
{
    private ESConfigurePerproties configurePerproties;

    public ESAutoConfiguration(ESConfigurePerproties configurePerproties)
    {
        this.configurePerproties = configurePerproties;
    }

    @Bean
    @ConditionalOnMissingBean
    public IESClientFactory esClientFactory() throws Exception
    {
        SpringClientFactoryBean factory = new SpringClientFactoryBean();
        factory.setNodes(this.configurePerproties.getNodes());
        factory.setCredential(this.configurePerproties.getCredential());
        return factory.getObject();
    }
}
