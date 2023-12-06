package cn.nesc.shiro.spring.boot;


import cn.nesc.shiro.spring.boot.autoconfigure.AccountAuthorizationRealm;
import cn.nesc.shiro.spring.boot.autoconfigure.separate.HeaderSessionManager;
import cn.nesc.shiro.spring.boot.autoconfigure.separate.HeaderShiroFilterFactoryBean;
import com.sun.javafx.binding.StringFormatter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author summer
 * @Description
 * @Date 13:17 2022/9/7
 * @Param 
 * @return 
 **/
@Configuration
@EnableConfigurationProperties(ShiroConfigurePerproties.class)
public class ShiroAutoConfiguration
{
    private static Logger log = LoggerFactory.getLogger(ShiroAutoConfiguration.class);

    private ShiroConfigurePerproties configurePerproties;

    public ShiroAutoConfiguration(ShiroConfigurePerproties configurePerproties)
    {
        this.configurePerproties = configurePerproties;
    }


    /**
     * 配置shiroFilter
     *
     * @return
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter()
    {
        ShiroFilterFactoryBean shiroFilter;
        try
        {
            if (this.configurePerproties.isFrontBackEndIndependent())
            {
                shiroFilter = new HeaderShiroFilterFactoryBean();
            }
            else
            {
                shiroFilter = new ShiroFilterFactoryBean();
                shiroFilter.setSuccessUrl(configurePerproties.getSuccessUrl());
                shiroFilter.setUnauthorizedUrl(configurePerproties.getUnauthorizedUrl());
            }
            shiroFilter.setLoginUrl(configurePerproties.getLoginUrl());
            if (null != getCustomFilters() && getCustomFilters().size() > 0)
            {
                // 自定义filters
                shiroFilter.setFilters(getCustomFilters());
            }
            shiroFilter.setSecurityManager(securityManager());
            Map<String, String> filterChainDefinitionMapping = new LinkedHashMap<>();
            List<String> mappings = configurePerproties.getFilterChainsDefinitionMapping();
            if (null != mappings)
            {
                mappings.stream().forEach(mapping -> {
                    String[] mappingPair = mapping.split(":");
                    if (null == mappingPair || mappingPair.length != 2)
                    {
                        throw new RuntimeException(StringFormatter.format("FilterChainDefinitionMapping [%s] is not correct format.", mapping).getValue());
                    }
                    filterChainDefinitionMapping.put(mappingPair[0], mappingPair[1]);
                });
            }
            shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
            log.debug("===============配置shiroFilter完毕!=============");
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return shiroFilter;
    }

    @Bean
    public Map<String, Filter> getCustomFilters()
    {
        Map<String, Filter> filters = new HashMap<>();
        return filters;
    }

    /**
     * @return
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager()
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean(name="realm")
    public AuthorizingRealm realm()
    {
        AccountAuthorizationRealm accountAuthorizationRealm = new AccountAuthorizationRealm();
        accountAuthorizationRealm.setCredentialsMatcher(matcher());
        return accountAuthorizationRealm;
    }

    @Bean(name="matcher")
    public CredentialsMatcher matcher()
    {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(configurePerproties.getAlgorithmName().name());
        matcher.setStoredCredentialsHexEncoded(true);
        matcher.setHashIterations(configurePerproties.getHashIterations());
        return matcher;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean()
    {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(securityManager());
        return methodInvokingFactoryBean;
    }

    /**
     *  自定义session验证任务, 定时检查session是否过期，如果开启检查需要覆盖该bean
     * @return
     */
    @Bean
    public SessionValidationScheduler sessionValidationScheduler()
    {
        return null;
    }


    @Bean
    public DefaultSessionManager sessionManager()
    {
        DefaultSessionManager sessionManager;
        if (this.configurePerproties.isFrontBackEndIndependent())
        {
            sessionManager = new HeaderSessionManager(this.configurePerproties.getSessionId());
        }
        else
        {
            sessionManager = new DefaultWebSessionManager();
            ((DefaultWebSessionManager)sessionManager).setSessionIdCookieEnabled(true);
            ((DefaultWebSessionManager)sessionManager).setSessionIdCookie(sessionIdCookie());
        }
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(this.configurePerproties.isSessionValidateEnable());

        SessionValidationScheduler sessionValidationScheduler = sessionValidationScheduler();
        if (null != sessionValidationScheduler)
        {
            sessionManager.setSessionValidationScheduler(sessionValidationScheduler());
        }
        CachingSessionDAO sessionDAO = sessionDAO();
        if (null != sessionDAO)
        {
            sessionManager.setSessionDAO(sessionDAO);
        }
        return sessionManager;
    }


    /**
     *  自定义sessionDao, 如果需要将session存储到外部，需要覆盖该bean
     * @return
     */
    @Bean
    @Order(Integer.MAX_VALUE)
    public CachingSessionDAO sessionDAO()
    {
        return null;
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator()
    {
        SessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
        return sessionIdGenerator;
    }

    @Bean
    public SimpleCookie sessionIdCookie()
    {
        SimpleCookie simpleCookie = new SimpleCookie(configurePerproties.getSessionId());
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        simpleCookie.setDomain("");
        simpleCookie.setPath("/");
        return simpleCookie;
    }
}
