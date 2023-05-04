package cn.nesc.mailsender.springboot.autoconfigure;


import cn.nesc.mailsender.MailSenderFactory;
import cn.nesc.mailsender.spring.SpringClientFactoryBean;
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
@EnableConfigurationProperties(MailSenderConfigurePerproties.class)
public class MailSenderAutoConfiguration
{
    private MailSenderConfigurePerproties configurePerproties;

    public MailSenderAutoConfiguration(MailSenderConfigurePerproties configurePerproties)
    {
        this.configurePerproties = configurePerproties;
    }

    @Bean
    @ConditionalOnMissingBean
    public MailSenderFactory itrusCloudFactory() throws Exception
    {
        SpringClientFactoryBean factory = new SpringClientFactoryBean();
        factory.setMailServer(this.configurePerproties.getMailServer());
        factory.setUsername(this.configurePerproties.getUsername());
        factory.setPassword(this.configurePerproties.getPassword());
        return factory.getObject();
    }
}
