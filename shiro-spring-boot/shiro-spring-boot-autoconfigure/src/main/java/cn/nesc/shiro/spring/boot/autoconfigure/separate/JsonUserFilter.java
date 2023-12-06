package cn.nesc.shiro.spring.boot.autoconfigure.separate;

import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by summer on 2019/12/19.
 */
public class JsonUserFilter extends UserFilter
{
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        // 这里也可以不用保存 保存当前request 可在登陆后重新请求当前 request
        this.saveRequest(request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json;charset=utf-8");
        String result = "{\"result\":false,\"msg\":\"The user is not logged in\",\"data\":null}";
        httpResponse.getWriter().write(result);
        return false;
    }
}
