package cn.nesc.shiro.spring.boot.autoconfigure;

/**
 * @ClassName ShiroUser
 * @Description
 * @Author summer
 * @Date 2023/11/29 15:27
 **/
public class ShiroUser
{

    private String userId;
    private String userCode;

    private String userName;

    private String password;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

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
