package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;


import cn.nesc.shiro.spring.boot.ShiroAutoConfiguration;
import cn.nesc.shiro.spring.boot.ShiroConfigurePerproties;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IDAASShiroConfigure
 * @Description
 * @Author summer
 * @Date 2023/11/30 19:15
 **/
@Configuration
@Import(ShiroAutoConfiguration.class)
@EnableConfigurationProperties(ShiroConfigurePerproties.class)
public class IDAASShiroConfigure
{
    private static Logger log = LoggerFactory.getLogger(IDAASShiroConfigure.class);

    private ShiroConfigurePerproties configurePerproties;

    public IDAASShiroConfigure(ShiroConfigurePerproties configurePerproties)
    {
        this.configurePerproties = configurePerproties;
    }

    @Bean(name = "realm")
    public AuthorizingRealm realm()
    {
        OAuth2AccountAuthorizationRealm accountAuthorizationRealm = new OAuth2AccountAuthorizationRealm();
        accountAuthorizationRealm.setIdaasUrl(this.configurePerproties.getIdaas().getIdaasUrl());
        accountAuthorizationRealm.setClientId(this.configurePerproties.getIdaas().getClientId());
        accountAuthorizationRealm.setClientSecret(this.configurePerproties.getIdaas().getClientSecret());
        accountAuthorizationRealm.setApiKey(this.configurePerproties.getIdaas().getApiKey());
        accountAuthorizationRealm.setApiSecret(this.configurePerproties.getIdaas().getApiSecret());
        accountAuthorizationRealm.setRedirectURL(this.configurePerproties.getIdaas().getRedirectURL());
        accountAuthorizationRealm.setCredentialsMatcher(matcher());
        return accountAuthorizationRealm;
    }

    /**
     * @return org.apache.shiro.authc.credential.CredentialsMatcher
     * @Author summer
     * @Description 自定义CredentialsMatcher
     * @Date 11:00 2023/12/5
     * @Param []
     **/
    @Bean(name = {"matcher"})
    public CredentialsMatcher matcher()
    {
        log.debug("getAlgorithmName name is " + this.configurePerproties.getAlgorithmName().name());
        IDAASCredentialsMatcher matcher = new IDAASCredentialsMatcher();
        matcher.setHashAlgorithmName(this.configurePerproties.getAlgorithmName().name());
        matcher.setStoredCredentialsHexEncoded(true);
        matcher.setHashIterations(this.configurePerproties.getHashIterations());
        return matcher;
    }

    @Bean
    public Map<String, Filter> getCustomFilters()
    {
        Map<String, Filter> filters = new HashMap<>();
//        filters.put("anon", new AnonymousFilter());
//        filters.put("authc", new FormAuthenticationFilter());
//        filters.put("logout", new LogoutFilter());
//        filters.put("roles", new RolesAuthorizationFilter());
//        filters.put("user", new JsonUserFilter());
        filters.put("idaas", getIDAASFilter());
        return filters;
    }

    @Bean
    public IDAASAuthenticationFilter getIDAASFilter()
    {
        IDAASAuthenticationFilter filter = new IDAASAuthenticationFilter();
        filter.setIDAASLoginUrl(this.configurePerproties.getIdaas().getLoginUrl());
        return filter;
    }
}
