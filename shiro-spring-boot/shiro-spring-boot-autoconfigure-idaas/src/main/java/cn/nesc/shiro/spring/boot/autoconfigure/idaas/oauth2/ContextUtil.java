/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: ContextUtil
 * Author:   summer
 * Date:     2023/12/12 14:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

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
