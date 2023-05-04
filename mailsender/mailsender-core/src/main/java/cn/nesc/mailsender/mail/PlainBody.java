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
