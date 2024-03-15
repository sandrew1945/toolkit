package cn.nesc.mailsender.mail;

/**
 * @ClassName MailBody
 * @Description
 * @Author summer
 * @Date 2023/4/26 10:56
 **/
public abstract class MailBody
{
    protected String content;

    protected EmailType emailType;

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public EmailType getEmailType()
    {
        return emailType;
    }

    public void setEmailType(EmailType emailType)
    {
        this.emailType = emailType;
    }
}
