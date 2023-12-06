package cn.nesc.shiro.spring.boot.autoconfigure;

/**
 * @ClassName UserService
 * @Description
 * @Author summer
 * @Date 2023/11/29 15:14
 **/
public interface UserService
{
    ShiroUser getUserByCode(String userCode);
}
