package cn.nesc.mailsender.mail;

/**
 * @ClassName PlainBody
 * @Description
 * @Author summer
 * @Date 2023/4/26 10:57
 **/
public class PlainBody extends MailBody
{
    public PlainBody()
    {
        this.emailType = EmailType.PLAIN;
    }

    public PlainBody(String content)
    {
        this.emailType = EmailType.PLAIN;
        this.content = content;

    }
}
