package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;

import cn.nesc.shiro.spring.boot.autoconfigure.ShiroUser;
import org.apache.shiro.util.ThreadContext;

/**
 * @ClassName ContextUtil
 * @Description
 * @Author summer
 * @Date 2023/12/12 14:02
 **/
public class ContextUtil
{
    public static ShiroUser getLoginUser()
    {
        ShiroUser shiroUser = (ShiroUser) ThreadContext.get("loginUser");
        return shiroUser;
    }

    public static void saveLoginUser(ShiroUser shiroUser)
    {
        ThreadContext.put("loginUser", shiroUser);
    }
}
