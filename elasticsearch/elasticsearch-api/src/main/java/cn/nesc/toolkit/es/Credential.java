/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: Credential
 * Author:   summer
 * Date:     2022/5/10 10:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es;

/**
 * @ClassName Credential
 * @Description 登录凭证
 * @Author summer
 * @Date 2022/5/10 10:57
 **/
public class Credential
{
    private String userName;

    private String password;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
