package cn.nesc.mailsender.mail;

/**
 * @ClassName PlainBody
 * @Description
 * @Author summer
 * @Date 2023/4/26 10:57
 **/
public class HtmlBody extends MailBody
{
    public HtmlBody()
    {
        this.emailType = EmailType.HTML;
    }

    public HtmlBody(String content)
    {
        this.emailType = EmailType.HTML;
        this.content = content;
    }
}
