/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: SpringClientFactoryBean
 * Author:   summer
 * Date:     2022/5/10 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.mailsender.spring;

import cn.nesc.mailsender.MailSenderFactory;
import org.springframework.context.ApplicationEvent;


/**
 * @ClassName SpringClientFactoryBean
 * @Description
 * @Author summer
 * @Date 2022/5/10 14:25
 **/
public class SpringClientFactoryBean
{
    private MailSenderFactory mailSenderFactory;

    private String mailServer;

    private String username;

    private String password;


    public void onApplicationEvent(ApplicationEvent applicationEvent)
    {

    }

    public MailSenderFactory getObject() throws Exception
    {
        mailSenderFactory = new MailSenderFactory();
        mailSenderFactory.setMailServer(mailServer);
        mailSenderFactory.setUsername(username);
        mailSenderFactory.setPassword(password);
        return mailSenderFactory;
    }

    public Class<?> getObjectType()
    {
        return this.mailSenderFactory == null ? MailSenderFactory.class : this.mailSenderFactory.getClass();
    }

    public boolean isSingleton()
    {
        return true;
    }

    public void afterPropertiesSet() throws Exception
    {

    }

    public String getMailServer()
    {
        return mailServer;
    }

    public void setMailServer(String mailServer)
    {
        this.mailServer = mailServer;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
