package cn.nesc.shiro.spring.boot.autoconfigure;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Function    : 
 * @author     : Administrator
 * CreateDate  : 2017年5月8日
 * @version    :
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter
{
    private static Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        // 如果验证码验证失败,跳过验证
        if (request.getAttribute(getFailureKeyAttribute()) != null)
        {
			return true;
        }
        return super.onAccessDenied(request, response);
    }

}