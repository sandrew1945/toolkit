package cn.nesc.mailsender;


/**
 * @ClassName AllianceFactory
 * @Description
 * @Author summer
 * @Date 2022/6/20 14:39
 **/
public class MailSenderFactory
{
    private String mailServer;

    private String username;

    private String password;


    public MailSender getMailSender()
    {
        MailSender mailSender = new MailSender();
        mailSender.setMailServer(this.mailServer);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);
        return mailSender;
    }

    public MailReader getMailReader()
    {
        MailReader mailReader = new MailReader();
        mailReader.setMailServer(this.mailServer);
        mailReader.setUsername(this.username);
        mailReader.setPassword(this.password);
        return mailReader;
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
