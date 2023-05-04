package cn.nesc.alliance.springboot.autoconfigure;


import cn.nesc.alliance.AllianceFactory;
import cn.nesc.alliance.spring.SpringClientFactoryBean;
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
@EnableConfigurationProperties(AllianceConfigurePerproties.class)
public class AllianceAutoConfiguration
{
    private AllianceConfigurePerproties configurePerproties;

    public AllianceAutoConfiguration(AllianceConfigurePerproties configurePerproties)
    {
        this.configurePerproties = configurePerproties;
    }

    @Bean
    @ConditionalOnMissingBean
    public AllianceFactory allianceFactory() throws Exception
    {
        SpringClientFactoryBean factory = new SpringClientFactoryBean();
        factory.setAllianceUrl(this.configurePerproties.getAllianceUrl());
        factory.setAgentId(this.configurePerproties.getAgentId());
        factory.setCorpId(this.configurePerproties.getCorpId());
        factory.setCorpSecret(this.configurePerproties.getCorpSecret());
        return factory.getObject();
    }
}
