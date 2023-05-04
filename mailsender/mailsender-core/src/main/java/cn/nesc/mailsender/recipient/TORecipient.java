/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: TORecipient
 * Author:   summer
 * Date:     2023/4/26 10:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.mailsender.recipient;

import cn.nesc.mailsender.exceptions.MailFormatException;

/**
 * @ClassName TORecipient
 * @Description 发送接收人
 * @Author summer
 * @Date 2023/4/26 10:38
 **/
public class TORecipient extends Recipient
{
    public TORecipient(String email) throws MailFormatException
    {
        if (isEmail(email))
        {
            this.email = email;
            this.recipientType = RecipientType.TO;
        }
        else
        {
            throw new MailFormatException("Recipient format is illegal");
        }
    }
}
