/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: PlainBody
 * Author:   summer
 * Date:     2023/4/26 10:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

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
