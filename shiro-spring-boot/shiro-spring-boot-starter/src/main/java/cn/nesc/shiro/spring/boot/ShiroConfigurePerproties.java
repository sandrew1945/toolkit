package cn.nesc.shiro.spring.boot;

import cn.nesc.shiro.spring.boot.autoconfigure.AlgorithmName;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author summer
 * @Description
 * @Date 10:32 2023/12/6
 * @Param 
 * @return 
 **/
@ConfigurationProperties(prefix = "spring.toolkit.shiro", ignoreInvalidFields = true)
public class ShiroConfigurePerproties
{
    private String loginUrl;    // 登录url

    private String successUrl;  // 登录成功跳转url

    private String unauthorizedUrl; // 认证失败跳转url

    private AlgorithmName algorithmName;    // 加密算法

    private int hashIterations = 1;     // 加密次数

    private String sessionId = "sid";   // session key

    private boolean sessionValidateEnable = false;  // 是否进行session过期验证

    private List<String> filterChainsDefinitionMapping; // 拦截器链映射关系

    private boolean externalSessionStoreEnable = false; // 是否使用外部存储session

    private boolean frontBackEndIndependent = false;    // 是否前后端分离模式

    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl()
    {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl)
    {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl()
    {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl)
    {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public AlgorithmName getAlgorithmName()
    {
        return algorithmName;
    }

    public void setAlgorithmName(AlgorithmName algorithmName)
    {
        this.algorithmName = algorithmName;
    }

    public int getHashIterations()
    {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations)
    {
        this.hashIterations = hashIterations;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public boolean isSessionValidateEnable()
    {
        return sessionValidateEnable;
    }

    public void setSessionValidateEnable(boolean sessionValidateEnable)
    {
        this.sessionValidateEnable = sessionValidateEnable;
    }

    public List<String> getFilterChainsDefinitionMapping()
    {
        return filterChainsDefinitionMapping;
    }

    public void setFilterChainsDefinitionMapping(List<String> filterChainsDefinitionMapping)
    {
        this.filterChainsDefinitionMapping = filterChainsDefinitionMapping;
    }

    public boolean isExternalSessionStoreEnable()
    {
        return externalSessionStoreEnable;
    }

    public void setExternalSessionStoreEnable(boolean externalSessionStoreEnable)
    {
        this.externalSessionStoreEnable = externalSessionStoreEnable;
    }

    public boolean isFrontBackEndIndependent()
    {
        return frontBackEndIndependent;
    }

    public void setFrontBackEndIndependent(boolean frontBackEndIndependent)
    {
        this.frontBackEndIndependent = frontBackEndIndependent;
    }
}
