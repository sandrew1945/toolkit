package cn.nesc.mailsender.recipient;

import cn.nesc.toolkit.common.validator.EmailValidator;

/**
 * @ClassName Recipient
 * @Description 接收人
 * @Author summer
 * @Date 2023/4/26 10:36
 **/
public abstract class Recipient
{
    protected String email;

    protected RecipientType recipientType;

    public String getEmail()
    {
        return this.email;
    }

    public RecipientType getRecipientType()
    {
        return recipientType;
    }

    protected boolean isEmail(String email)
    {
        if (null == email || email.length() == 0)
        {
            return false;
        }
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.validate(email);
    }
}
