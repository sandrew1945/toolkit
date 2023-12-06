package cn.nesc.shiro.spring.boot.autoconfigure;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


/**
 * 管理员的认证,角色,权限控制
 */
public class AccountAuthorizationRealm extends AuthorizingRealm
{
	@Resource
	@Lazy
	protected UserService userService;
	/**
	 * 查询获得用户信息
	 * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
	 *
	 * AuthenticationInfo有两个作用：
	 * 1、如果Realm 是AuthenticatingRealm 子类，则提供给AuthenticatingRealm 内部使用的
	 *    CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
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
				return new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
			}
			else
			{
				return null;
			}
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


}
