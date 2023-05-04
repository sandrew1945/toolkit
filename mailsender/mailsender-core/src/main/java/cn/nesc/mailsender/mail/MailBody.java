/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: MailBody
 * Author:   summer
 * Date:     2023/4/26 10:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

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
