/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: IDAASAuthenticationFilter
 * Author:   summer
 * Date:     2023/12/11 10:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;

import cn.nesc.shiro.spring.boot.autoconfigure.ShiroUser;
import cn.nesc.shiro.spring.boot.autoconfigure.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName IDAASAuthenticationFilter
 * @Description
 * @Author summer
 * @Date 2023/12/11 10:56
 **/
public class IDAASAuthenticationFilter extends AuthenticatingFilter
{
    private static Logger log = LoggerFactory.getLogger(IDAASAuthenticationFilter.class);

    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

    public static final String DEFAULT_CODE_PARAM = "code";

    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    private String codeParam = DEFAULT_CODE_PARAM;

    private String IDAASLoginUrl;

    @Resource
    @Lazy
    private UserService userService;


    public IDAASAuthenticationFilter()
    {
        setLoginUrl(DEFAULT_LOGIN_URL);
    }

    @Override
    public void setLoginUrl(String loginUrl)
    {
        String previous = getLoginUrl();
        if (previous != null)
        {
            this.appliedPaths.remove(previous);
        }
        super.setLoginUrl(loginUrl);
        if (log.isTraceEnabled())
        {
            log.trace("Adding login url to applied paths.");
        }
        this.appliedPaths.put(getLoginUrl(), null);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception
    {
        String code = getCode(request);
        OAuth2Token token = new OAuth2Token(code);
        return token;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        if (super.isAccessAllowed(request, response, mappedValue))
        {
            return true;
        }
        else if (this.isLoginRequest(request, response))
        {
            return false;
        }
        else
        {
            return true;
        }
//        return !super.isAccessAllowed(request, response, mappedValue) && !this.isLoginRequest(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        if (isLoginRequest(request, response))
        {
            if (isLoginSubmission(request, response))
            {
                if (log.isTraceEnabled())
                {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            }
            else
            {
                if (log.isTraceEnabled())
                {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        }
        else
        {
            if (log.isTraceEnabled())
            {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " + "Authentication url [" + getLoginUrl() + "]");
            }
            return false;
        }
    }

    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response)
    {
        return (request instanceof HttpServletRequest) && WebUtils.toHttp(request).getMethod().equalsIgnoreCase(GET_METHOD);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception
    {
//        ShiroUser shiroUser = (ShiroUser) ThreadContext.get("loginUser");
        ShiroUser shiroUser = ContextUtil.getLoginUser();
        ShiroUser databaseUser = userService.getUserByCode(shiroUser.getUserCode());
        shiroUser.setUserId(databaseUser.getUserId());
        return true;
    }

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response)
    {
        return this.pathsMatch(this.getIDAASLoginUrl(), request);
    }

    protected String getCode(ServletRequest request)
    {
        return WebUtils.getCleanParam(request, getCodeParam());
    }

    public String getCodeParam()
    {
        return codeParam;
    }

    public String getFailureKeyAttribute()
    {
        return failureKeyAttribute;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute)
    {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    public String getIDAASLoginUrl()
    {
        return IDAASLoginUrl;
    }

    public void setIDAASLoginUrl(String IDAASLoginUrl)
    {
        this.IDAASLoginUrl = IDAASLoginUrl;
    }
}
