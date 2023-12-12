package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;


import cn.nesc.shiro.spring.boot.autoconfigure.Principal;
import cn.nesc.shiro.spring.boot.autoconfigure.ShiroUser;
import cn.nesc.shiro.spring.boot.autoconfigure.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


/**
 * 管理员的认证,角色,权限控制
 */
public class OAuth2AccountAuthorizationRealm extends AuthorizingRealm
{
    private static Logger log = LoggerFactory.getLogger(OAuth2AccountAuthorizationRealm.class);

    @Resource
    @Lazy
    protected UserService userService;
    private String idaasUrl;
    private String clientId;
    private String clientSecret;
    private String apiKey;
    private String apiSecret;
    private String redirectURL;

    /**
     * 查询获得用户信息
     * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
     * <p>
     * AuthenticationInfo有两个作用：
     * 1、如果Realm 是AuthenticatingRealm 子类，则提供给AuthenticatingRealm 内部使用的
     * CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
     * 2、提供给SecurityManager来创建Subject（提供身份信息）；
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        try
        {
            ShiroUser loginUser = new ShiroUser();
//            ThreadContext.put("loginUser", loginUser);
            ContextUtil.saveLoginUser(loginUser);
            if (authcToken instanceof UsernamePasswordToken)
            {
                // 登录页面登录
                // 通过token获取登录账号并查询数据库获取用户信息存入AuthenticationInfo
                /*这里编写认证代码*/
                UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
                if (token.getUsername() == null)
                {
                    throw new AccountException("用户名密码不正确");
                }
                ShiroUser user = userService.getUserByCode(token.getUsername());
                if (user != null)
                {
                    Principal principal = new Principal();
                    principal.setName(user.getUserCode());
                    loginUser.setUserCode(user.getUserCode());
                    loginUser.setUserName(user.getUserName());
                    return new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
                }
                else
                {
                    return null;
                }
            }
            else if (authcToken instanceof OAuth2Token)
            {
                // OAuth2登录
                // 通过code换取accessToken，再通过accessToken查询用户信息存入AuthenticationInfo
                OAuth2Token token = (OAuth2Token) authcToken;
                String code = token.getCode();
                log.debug("code:" + code);

                IDAASAccessToken accessToken = new IDAASAccessToken();
                IDAASUserInfo userInfo = null;
                userInfo = IDAASAuthenticateProcess.perform(idaasUrl, clientId, clientSecret, redirectURL, code).getAccessToken(idaasAccessToken -> BeanUtils.copyProperties(idaasAccessToken, accessToken)).getUserInfo();
                log.debug(userInfo.toString());

                if (null != userInfo)
                {
                    loginUser.setUserCode(userInfo.getUsername());
                    loginUser.setUserName(userInfo.getNickname());
                    Principal principal = new Principal();
                    principal.setName(userInfo.getUsername());
                    return new SimpleAuthenticationInfo(principal, accessToken.getAccessToken(), getName());
                }
                else
                {
                    return null;
                }
            }
            return null;
        }
        catch (Exception e)
        {
            throw new AuthenticationException("用户认证失败", e);
        }
    }

    /**
     * 表示根据用户身份获取授权信息
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token)
    {
        return token != null && token instanceof AuthenticationToken;
    }

    public String getIdaasUrl()
    {
        return idaasUrl;
    }

    public void setIdaasUrl(String idaasUrl)
    {
        this.idaasUrl = idaasUrl;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getClientSecret()
    {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret)
    {
        this.clientSecret = clientSecret;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getApiSecret()
    {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;
    }

    public String getRedirectURL()
    {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL)
    {
        this.redirectURL = redirectURL;
    }
}
