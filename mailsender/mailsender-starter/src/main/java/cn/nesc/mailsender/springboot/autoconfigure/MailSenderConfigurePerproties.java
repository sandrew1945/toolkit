package cn.nesc.mailsender.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by summer on 2019/8/7.
 */
@ConfigurationProperties(prefix = "spring.toolkit.mail", ignoreInvalidFields = true)
public class MailSenderConfigurePerproties
{
    private String mailServer;

    private String username;

    private String password;

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
