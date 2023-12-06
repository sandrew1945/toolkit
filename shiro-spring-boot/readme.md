# Springboot与Shiro集成自动配置Starter

Description: 自动集成shiro权限管理框架,提供基本的常规功能,并提供扩展能力
Type: 使用文档

## 安装

通过maven引入依赖,暂时不支持Gradle

```xml
<dependency>
    <groupId>cn.nesc</groupId>
    <artifactId>shiro-spring-boot-starter</artifactId>
    <version>NEWEST-VERSION</version>
</dependency>
```

## 集成方法

- 基于Springboot前后端不分离的项目

在项目的properties或yml文件中配置参数

```yaml
# 登录url
spring.toolkit.shiro.login-url=/login
# 登录成功跳转url
spring.toolkit.shiro.success-url=/showRoleSelect
# 登录失败跳转url
spring.toolkit.shiro.unauthorized-url=/server
# 密码的加密算法
spring.toolkit.shiro.algorithm-name=MD5
# 密码的加密迭代次数
spring.toolkit.shiro.hash-iterations=1
# 是否开启session过期验证
spring.toolkit.shiro.session-validate-enable=false
# 是否开启前后端分离模式
spring.toolkit.shiro.front-back-end-independent=false
# 在Cookie或header中存入session-id的key值, 默认为sid
spring.toolkit.shiro.session-id=sid
# 请求路径与拦截器对应关系
spring.toolkit.shiro.filter-chains-definition-mapping[0]=/login.html:anon
spring.toolkit.shiro.filter-chains-definition-mapping[1]=/:anon
spring.toolkit.shiro.filter-chains-definition-mapping[2]=/shutdown:anon
spring.toolkit.shiro.filter-chains-definition-mapping[3]=/register/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[4]=/assets/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[5]=/images/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[6]=/js/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[7]=/style/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[8]=/generate/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[9]=/logout:logout
spring.toolkit.shiro.filter-chains-definition-mapping[10]=/**/*.html:authc
spring.toolkit.shiro.filter-chains-definition-mapping[11]=/login:authc
spring.toolkit.shiro.filter-chains-definition-mapping[12]=/**:user
```

通过以上配置即可实现基本的shiro集成,session是在服务器内存中进行管理,使用shiro默认的拦截器链,在实际项目中可能需要将session存放到redis或mysql中进行统一管理,那么就需要进一步的扩展

- 基于Springboot前后端分离的项目

在项目的properties或yml文件中配置参数

```yaml
# 登录url
spring.toolkit.shiro.login-url=/login
# 密码的加密算法
spring.toolkit.shiro.algorithm-name=MD5
# 密码的加密迭代次数
spring.toolkit.shiro.hash-iterations=1
# 是否开启session过期验证
spring.toolkit.shiro.session-validate-enable=true
# 是否开启前后端分离模式
spring.toolkit.shiro.front-back-end-independent=true
# 在Cookie或header中存入session-id的key值, 默认为sid
spring.toolkit.shiro.session-id=sid
# 请求路径与拦截器对应关系
spring.toolkit.shiro.filter-chains-definition-mapping[0]=/:anon
spring.toolkit.shiro.filter-chains-definition-mapping[1]=/shutdown:anon
spring.toolkit.shiro.filter-chains-definition-mapping[2]=/register/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[3]=/generate/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[4]=/v2/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[5]=/webjars/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[6]=/swagger-ui.html:anon
spring.toolkit.shiro.filter-chains-definition-mapping[7]=/swagger-resources/**:anon
spring.toolkit.shiro.filter-chains-definition-mapping[8]=/csrf:anon
spring.toolkit.shiro.filter-chains-definition-mapping[9]=/login:authc
spring.toolkit.shiro.filter-chains-definition-mapping[10]=/**:user
```

与前后端不分离项目不同之处在于不需要配置登录成功/失败后的跳转url,并将front-back-end-independent设置为true

## 扩展点

可以通过覆盖`ShiroAutoConfiguration`下的方法对实现方式及功能进行扩展,下面给出几个常见扩展需求:

- **将session存入redis进行管理**

将session存入redis可以通过redis自带的过期机制进行session的过期处理,所以可以将session-validate-enable设置为false

在项目中创建自定义的ShiroConfigure,并添加`@Configuration`注解,使用`@Import`注解引入`ShiroAutoConfiguration.class`对其进行方法的重写,重写`sessionDAO`方法,代码如下:

```java
/**
     * @Author summer
     * @Description     自定义sessionDAO，使用外部存储sessino时需要自定义该bean
     * @Date 16:07 2023/12/4
     * @Param [sessionIdGenerator]
     * @return com.sandrew.boot.shiro.session.RedisSessionDAO
     **/
    @Bean
    public RedisSessionDAO sessionDAO(SessionIdGenerator sessionIdGenerator)
    {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;

    }
```

其中`RedisSessionDAO`是继承了shiro定义的`CachingSessionDAO`覆盖其中的方法,使用redis对session进行增删改查操作

```java
public class RedisSessionDAO extends CachingSessionDAO
{

    private static final Long EXPIRE_TIME = 30 * 60l;

    @Resource
    private RedisUtil redisUtil;

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO#doUpdate(org.apache.shiro.session.Session)
     */
    @Override
    protected void doUpdate(Session session)
    {
        //如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid())
        {
            return;
        }
        redisUtil.set(String.valueOf(session.getId()), session, EXPIRE_TIME);
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO#doDelete(org.apache.shiro.session.Session)
     */
    @Override
    protected void doDelete(Session session)
    {
        redisUtil.remove(String.valueOf(session.getId()));
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session)
     */
    @Override
    protected Serializable doCreate(Session session)
    {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisUtil.set(String.valueOf(sessionId), session);
        return session.getId();
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io.Serializable)
     */
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        Session session = (Session) redisUtil.get(String.valueOf(sessionId));
        return session;
    }

}
```

同理可以使用该方法将session存入任何想要存储的中间件内.

- 扩展自定义的拦截器

覆盖`getCustomFilters()`方法,可以增加自定义的拦截器,同时也可以使用自己的拦截器替代框架内自带拦截器

```java
@Bean
public Map<String, Filter> getCustomFilters()
{
    Map<String, Filter> filters = new HashMap<>();
    filters.put("anon", new AnonymousFilter());
    filters.put("authc", new MyFormAuthenticationFilter());
    filters.put("logout", new LogoutFilter());
    filters.put("roles", new RolesAuthorizationFilter());
    filters.put("user", new UserFilter());
    return filters;
}
```

- 使用自己的密码校验算法

shiro框架中封装了几种默认的校验算法:*MD2*, *MD5*, *SHA1*, *SHA256*, *SHA384*, *SHA512.* 如果需要自定义那么可以覆盖*`matcher()`方法*

```java
/**
 * @Author summer
 * @Description 自定义CredentialsMatcher
 * @Date 11:00 2023/12/5
 * @Param []
 * @return org.apache.shiro.authc.credential.CredentialsMatcher
 **/
@Bean(name = {"matcher"})
public CredentialsMatcher matcher() {
    PlaintextCredentialsMatcher matcher = new PlaintextCredentialsMatcher();
    return matcher;
}
```

通过自定义的PlaintextCredentialsMatcher对校验算法进行自定义,`PlaintextCredentialsMatcher`继承自`HashedCredentialsMatcher`

- 使用自定义的session有效性验证定时任务

如果想要使用自己定义的session有效性验证方法那么可以覆盖*`sessionValidationScheduler()`方法*

```java
/**
 * @Author summer
 * @Description     自定义session有效性验证任务
 * @Date 16:06 2023/12/4
 * @Param []
 * @return cn.nesc.shiro.spring.boot.autoconfigure.AbstractSessionValidationScheduler
 **/
@Bean
public SessionValidationScheduler sessionValidationScheduler()
{
    try
    {
        MySqlSessionValidationScheduler sqlSessionValidationScheduler = new MySqlSessionValidationScheduler();
        sqlSessionValidationScheduler.setInterval(1000l);
        return sqlSessionValidationScheduler;
    }
    catch (Exception e)
    {
    throw new RuntimeException(e);
    }
}
```

其中`MySqlSessionValidationScheduler`继承自`SessionValidationScheduler`

- 自定义未登录返回消息

在前后端分离模式下,默认的未登录返回格式如下:

```java
HTTP/1.1 401 
sid: a06151e4-9c59-4bbe-b2f1-1d7fb6513c9f
Content-Type: application/json;charset=utf-8
Content-Length: 63
Date: Wed, 06 Dec 2023 06:29:26 GMT
Connection: close

{
  "result": false,
  "msg": "The user is not logged in",
  "data": null
}
```

可以通过替换`user`拦截器进行自定义,参考[扩展自定义的拦截器](https://www.notion.so/c4696fb246414ece94b16123895be064?pvs=21),使用自定义的`UserFilter`,该拦截器需要继承自`UserFilter`,示例代码如下:

```java
public class MyUserFilter extends UserFilter
{
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        // 这里也可以不用保存 保存当前request 可在登陆后重新请求当前 request
        this.saveRequest(request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json;charset=utf-8");
        JsonResult result = new JsonResult();
        result.requestFailure("The user is not logged in!");
        ObjectMapper objectMapper = new ObjectMapper();
        httpResponse.getWriter().write(objectMapper.writeValueAsString(result));
        return false;

    }
}
```