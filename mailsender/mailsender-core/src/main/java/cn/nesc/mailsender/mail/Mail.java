package cn.nesc.mailsender.mail;

import cn.nesc.mailsender.exceptions.MailFormatException;
import cn.nesc.mailsender.recipient.*;
import cn.nesc.toolkit.common.validator.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Mail
 * @Description 邮件内容定义
 * @Author summer
 * @Date 2023/4/26 10:29
 **/
public class Mail
{
    private static Logger log = LoggerFactory.getLogger(Mail.class);

    private String subject;

    private MailBody mailBody;

    private String from;

    private Date receiveDate;

    private Date deliveredDate;

    private List<Recipient> toList = new ArrayList<>();

    private List<Recipient> ccList = new ArrayList<>();

    private List<Recipient> bccList = new ArrayList<>();

    private List<Attachment> attachments = new ArrayList<>();

    /**
     * @Author summer
     * @Description 创建文本格式的email
     * @Date 15:51 2023/4/26
     * @Param [to, subject, content]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createPlainMail(String to, String subject, String content) throws MailFormatException
    {
        return createSimpleMail(to, null, null, subject, content, EmailType.PLAIN);
    }

    public static Mail createPlainMail(String to, String cc, String subject, String content) throws MailFormatException
    {
        return createSimpleMail(to, cc, null, subject, content, EmailType.PLAIN);
    }

    /**
     * @Author summer
     * @Description 创建html格式的email
     * @Date 15:51 2023/4/26
     * @Param [to, subject, content]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createHtmlMail(String to, String subject, String content) throws MailFormatException
    {
        return createSimpleMail(to, null, null, subject, content, EmailType.HTML);
    }

    /**
     * @Author summer
     * @Description 创建html格式的email
     * @Date 16:21 2023/4/26
     * @Param [to, subject, content]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createHtmlMail(String to, String cc, String subject, String content) throws MailFormatException
    {
        return createSimpleMail(to, cc, null, subject, content, EmailType.HTML);
    }

    /**
     * @Author summer
     * @Description 创建简单的email
     * @Date 16:13 2023/4/26
     * @Param [to, cc, bcc, subject, content, emailType]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createSimpleMail(String to, String cc, String bcc, String subject, String content, EmailType emailType) throws MailFormatException
    {
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(to))
        {
            throw new MailFormatException("Recipient format is illegal");
        }
        if (null != cc && !emailValidator.validate(cc))
        {
            throw new MailFormatException("Carbon copy recipient format is illegal");
        }
        if (null != bcc && !emailValidator.validate(bcc))
        {
            throw new MailFormatException("Blind carbon copy recipient format is illegal");
        }
        List<String> toList = new ArrayList<>();
        toList.add(to);
        List<String> ccList = null;
        if (null != cc && cc.length() > 0)
        {
            ccList = new ArrayList<>();
            ccList.add(cc);
        }
        List<String> bccList = null;
        if (null != bcc && bcc.length() > 0)
        {
            bccList = new ArrayList<>();
            bccList.add(bcc);
        }
        return doCreateMail(toList, ccList, bccList, subject, content, null, emailType);
    }

    /**
     * @Author summer
     * @Description 创建文本格式的email并带附件
     * @Date 16:08 2023/4/26
     * @Param [to, subject, content, attachmentPath]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createPlainMailWithAttachment(String to, String subject, String content, String attachmentPath) throws MailFormatException
    {
        return createMail(to, null, null, subject, content, attachmentPath, EmailType.PLAIN);
    }

    /**
     * @Author summer
     * @Description 创建文本格式的email并带附件
     * @Date 16:42 2023/4/26
     * @Param [to, cc, subject, content, attachmentPath]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createPlainMailWithAttachment(String to, String cc, String subject, String content, String attachmentPath) throws MailFormatException
    {
        return createMail(to, cc, null, subject, content, attachmentPath, EmailType.PLAIN);
    }

    /**
     * @Author summer
     * @Description 创建html格式的email并带附件
     * @Date 16:09 2023/4/26
     * @Param [to, subject, content, attachmentPath]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createHtmlMailWithAttachment(String to, String subject, String content, String attachmentPath) throws MailFormatException
    {
        return createMail(to, null, null, subject, content, attachmentPath, EmailType.HTML);
    }

    /**
     * @Author summer
     * @Description 创建html格式的email并带附件
     * @Date 16:42 2023/4/26
     * @Param [to, cc, subject, content, attachmentPath]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createHtmlMailWithAttachment(String to, String cc, String subject, String content, String attachmentPath) throws MailFormatException
    {
        return createMail(to, cc, null, subject, content, attachmentPath, EmailType.HTML);
    }

    /**
     * @Author summer
     * @Description 创建email
     * @Date 16:05 2023/4/26
     * @Param [toList, ccList, bccList, subject, content, attachmentPath, emailType]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    private static Mail createMail(String to, String cc, String bcc, String subject, String content, String attachmentPath, EmailType emailType) throws MailFormatException
    {
        EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(to))
        {
            throw new MailFormatException("Recipient format is illegal");
        }
        if (null != cc && !emailValidator.validate(cc))
        {
            throw new MailFormatException("Carbon copy recipient format is illegal");
        }
        if (null != bcc && !emailValidator.validate(bcc))
        {
            throw new MailFormatException("Blind carbon copy recipient format is illegal");
        }
        List<String> toList = new ArrayList<>();
        toList.add(to);
        List<String> ccList = null;
        if (null != cc && cc.length() > 0)
        {
            ccList = new ArrayList<>();
            ccList.add(cc);
        }
        List<String> bccList = null;
        if (null != bcc && bcc.length() > 0)
        {
            bccList = new ArrayList<>();
            bccList.add(bcc);
        }
        List<String> attachments = null;
        if (null != attachmentPath && attachmentPath.length() > 0)
        {
            attachments = new ArrayList<>();
            attachments.add(attachmentPath);
        }
        return doCreateMail(toList, ccList, bccList, subject, content, attachments, emailType);
    }

    /**
     * @Author summer
     * @Description 创建email(通用,可涵盖所有情况)
     * @Date 13:58 2023/4/27
     * @Param [recipients, subject, content, attachments, emailType]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    public static Mail createMail(List<Recipient> recipients, String subject, String content, List<Attachment> attachments, EmailType emailType) throws MailFormatException
    {
        // 验证接收人是否合法
        if (null == recipients || recipients.size() == 0)
        {
            throw new MailFormatException("No recipient");
        }
        // 验证是否有收件人
        boolean hasTO = false;
        for (Recipient recipient : recipients)
        {
            if (recipient.getRecipientType().equals(RecipientType.TO))
            {
                hasTO = true;
                break;
            }
        }
        if (!hasTO)
        {
            throw new MailFormatException("No recipient");
        }

        return doCreateMail(recipients, subject, content, attachments, emailType);
    }

    /**
     * @Author summer
     * @Description 创建邮件
     * @Date 15:59 2023/4/26
     * @Param [toList, ccList, bccList, subject, content, attachmentPath, emailType]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    private static Mail doCreateMail(List<String> toList, List<String> ccList, List<String> bccList, String subject, String content, List<String> attachments, EmailType emailType) throws MailFormatException
    {
        Mail mail = new Mail();
        for (String to : toList)
        {
            mail.addTO(to);
        }
        if (null != ccList)
        {
            for (String cc : ccList)
            {
                mail.addCC(cc);
            }
        }
        if (null != bccList)
        {
            for (String bcc : bccList)
            {
                mail.addBCC(bcc);
            }
        }
        mail.setSubject(subject);
        MailBody body;
        switch (emailType)
        {
            case HTML:
                body = new HtmlBody(content);
                break;
            case PLAIN:
                body = new PlainBody(content);
                break;
            default:
                body = new PlainBody(content);
        }
        mail.setMailBody(body);
        if (null != attachments && attachments.size() > 0)
        {
            attachments.forEach(attachment -> mail.addAttachment(attachment));
        }
        return mail;
    }

    /**
     * @Author summer
     * @Description 创建邮件(通用,可涵盖所有情况)
     * @Date 13:59 2023/4/27
     * @Param [recipients, subject, content, attachments, emailType]
     * @return cn.nesc.mailsender.mail.Mail
     **/
    private static Mail doCreateMail(List<Recipient> recipients, String subject, String content, List<Attachment> attachments, EmailType emailType) throws MailFormatException
    {
        Mail mail = new Mail();
        for (Recipient recipient : recipients)
        {
            switch (recipient.getRecipientType())
            {
                case TO:
                    mail.addTO(recipient);
                    break;
                case CC:
                    mail.addCC(recipient);
                    break;
                case BCC:
                    mail.addBCC(recipient);
                    break;
            }
        }
        mail.setSubject(subject);
        MailBody body;
        switch (emailType)
        {
            case HTML:
                body = new HtmlBody(content);
                break;
            case PLAIN:
                body = new PlainBody(content);
                break;
            default:
                body = new PlainBody(content);
        }
        mail.setMailBody(body);
        if (null != attachments && attachments.size() > 0)
        {
            attachments.forEach(attachment -> mail.addAttachment(attachment));
        }
        return mail;
    }

    public void addTO(Recipient toRecipient)
    {
        toList.add(toRecipient);
    }

    public void addCC(Recipient ccRecipient)
    {
        ccList.add(ccRecipient);
    }

    public void addBCC(Recipient bccRecipient)
    {
        bccList.add(bccRecipient);
    }

    /**
     * @Author summer
     * @Description 增加收件人
     * @Date 13:37 2023/4/26
     * @Param [email]
     * @return void
     **/
    public void addTO(String email) throws MailFormatException
    {
        TORecipient toRecipient = new TORecipient(email);
        toList.add(toRecipient);
    }

    /**
     * @Author summer
     * @Description 增加抄送人
     * @Date 13:37 2023/4/26
     * @Param [email]
     * @return void
     **/
    public void addCC(String email) throws MailFormatException
    {
        CCRecipient ccRecipient = new CCRecipient(email);
        ccList.add(ccRecipient);
    }

    /**
     * @Author summer
     * @Description 增加秘密抄送
     * @Date 13:37 2023/4/26
     * @Param [email]
     * @return void
     **/
    public void addBCC(String email) throws MailFormatException
    {
        BCCRecipient bccRecipient = new BCCRecipient(email);
        bccList.add(bccRecipient);
    }

    /**
     * @Author summer
     * @Description  获取邮件正文
     * @Date 13:47 2023/4/26
     * @Param []
     * @return java.lang.String
     **/
    public String getContent()
    {
        return this.mailBody.getContent();
    }

    /**
     * @Author summer
     * @Description 获取邮件正文类型
     * @Date 13:48 2023/4/26
     * @Param []
     * @return java.lang.Enum
     **/
    public EmailType getContentType()
    {
        return this.mailBody.getEmailType();
    }

    public void addAttachment(String filePath)
    {
        Attachment attachment = new Attachment(filePath);
        this.attachments.add(attachment);
    }

    public void addAttachment(Attachment attachment)
    {
        this.attachments.add(attachment);
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public MailBody getMailBody()
    {
        return mailBody;
    }

    public void setMailBody(MailBody mailBody)
    {
        this.mailBody = mailBody;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public Date getReceiveDate()
    {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate)
    {
        this.receiveDate = receiveDate;
    }

    public Date getDeliveredDate()
    {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate)
    {
        this.deliveredDate = deliveredDate;
    }

    public List<Recipient> getToList()
    {
        return toList;
    }

    public void setToList(List<Recipient> toList)
    {
        this.toList = toList;
    }

    public List<Recipient> getCcList()
    {
        return ccList;
    }

    public void setCcList(List<Recipient> ccList)
    {
        this.ccList = ccList;
    }

    public List<Recipient> getBccList()
    {
        return bccList;
    }

    public void setBccList(List<Recipient> bccList)
    {
        this.bccList = bccList;
    }

    public List<Attachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }
}
