/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: MailSender
 * Author:   summer
 * Date:     2023/4/24 16:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.mailsender;

import cn.nesc.mailsender.exceptions.MailFormatException;
import cn.nesc.mailsender.exceptions.MailSenderException;
import cn.nesc.mailsender.mail.Attachment;
import cn.nesc.mailsender.mail.EmailType;
import cn.nesc.mailsender.mail.Mail;
import cn.nesc.mailsender.recipient.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * @ClassName MailSender
 * @Description
 * @Author summer
 * @Date 2023/4/24 16:24
 **/
public class MailSender
{
    private static Logger log = LoggerFactory.getLogger(MailSender.class);

    private String mailServer;

    private String username;

    private String password;

    /**
     * @Author summer
     * @Description 发送邮件
     * @Date 13:44 2023/4/27
     * @Param [to, subject, content, emailType]
     * @return void
     **/
    public void sendSimpleMail(String to, String subject, String content, EmailType emailType) throws MailSenderException
    {
        try
        {
            log.debug("sending email to " + to);
            Mail mail = null;
            switch (emailType)
            {
                case PLAIN:
                    mail = Mail.createPlainMail(to, subject, content);
                    break;
                case HTML:
                    mail = Mail.createHtmlMail(to, subject, content);
                    break;
            }
            MimeMessage message = buildMessage(mail, null);
            doSend(message);
            log.debug("email has bean sent.");
        }
        catch (MailFormatException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 发送邮件
     * @Date 13:45 2023/4/27
     * @Param [to, cc, subject, content, emailType]
     * @return void
     **/
    public void sendSimpleMail(String to, String cc, String subject, String content, EmailType emailType) throws MailSenderException
    {
        try
        {
            log.debug("sending email to " + to);
            Mail mail = null;
            switch (emailType)
            {
                case PLAIN:
                    mail = Mail.createPlainMail(to, cc, subject, content);
                    break;
                case HTML:
                    mail = Mail.createHtmlMail(to, cc, subject, content);
                    break;
            }
            MimeMessage message = buildMessage(mail, null);
            doSend(message);
            log.debug("email has bean sent.");
        }
        catch (MailFormatException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 发送邮件,包含附件
     * @Date 13:40 2023/4/27
     * @Param [to, subject, content, emailType, attachmentPath]
     * @return void
     **/
    public void sendMailWithAttachment(String to, String subject, String content, EmailType emailType, String attachmentPath) throws MailSenderException
    {
        try
        {
            log.debug("sending email to " + to);
            Mail mail = null;
            switch (emailType)
            {
                case PLAIN:
                    mail = Mail.createPlainMailWithAttachment(to, subject, content, attachmentPath);
                    break;
                case HTML:
                    mail = Mail.createHtmlMailWithAttachment(to, subject, content, attachmentPath);
                    break;
            }
            MimeMessage message = buildMessage(mail, null);
            doSend(message);
            log.debug("email has bean sent.");
        }
        catch (MailFormatException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 发送邮件,包含附件
     * @Date 13:40 2023/4/27
     * @Param [to, cc, subject, content, emailType, attachmentPath]
     * @return void
     **/
    public void sendMailWithAttachment(String to, String cc, String subject, String content, EmailType emailType, String attachmentPath) throws MailSenderException
    {
        try
        {
            log.debug("sending email to " + to);
            Mail mail = null;
            switch (emailType)
            {
                case PLAIN:
                    mail = Mail.createPlainMailWithAttachment(to, cc, subject, content, attachmentPath);
                    break;
                case HTML:
                    mail = Mail.createHtmlMailWithAttachment(to, cc, subject, content, attachmentPath);
                    break;
            }
            MimeMessage message = buildMessage(mail, null);
            doSend(message);
            log.debug("email has bean sent.");
        }
        catch (MailFormatException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 发送自定义邮件(能够覆盖全部场景)
     * @Date 14:05 2023/4/27
     * @Param [recipients]  收件人,包含收件人、抄送、秘密抄送
     * @Param [subject]     邮件主题
     * @Param [content]     邮件内容
     * @Param [emailType]   邮件类型: PLAIN|HTML
     * @Param [attachments] 附件
     * @Param [consumer]    自定义设置
     * @return void
     **/
    public void sendCustomMail(List<Recipient> recipients, String subject, String content, EmailType emailType, List<Attachment> attachments, Consumer<MimeMessage> consumer) throws MailSenderException
    {
        try
        {
            log.debug("sending custom email");
            Mail mail = Mail.createMail(recipients, subject, content, attachments, emailType);

            MimeMessage message = buildMessage(mail, consumer);
            doSend(message);
            log.debug("email has bean sent.");
        }
        catch (MailFormatException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }


    private MimeMessage buildMessage(Mail mail, Consumer<MimeMessage> consumer) throws MessagingException
    {
        Properties properties = System.getProperties();
        // Setup mail server
        properties.setProperty("mail.smtp.host", mailServer);
        // enable authentication
        properties.put("mail.smtp.auth", "true");
        // SSL Factory
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.trust", "*");
        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        // header field of the header.
        message.setFrom(new InternetAddress(username));
        for (Recipient to : mail.getToList())
        {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.getEmail()));
        }
        for (Recipient cc : mail.getCcList())
        {
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc.getEmail()));
        }
        for (Recipient bcc : mail.getBccList())
        {
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc.getEmail()));
        }
        message.setSubject(mail.getSubject());
        // 创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();
        switch (mail.getContentType())
        {
            case PLAIN:
                messageBodyPart.setText(mail.getContent());
                break;
            case HTML:
                messageBodyPart.setContent(mail.getContent(), "text/html;charset=UTF-8");
                break;
            default:
                messageBodyPart.setText(mail.getContent());
        }
        // 创建多重消息
        Multipart multipart = new MimeMultipart();
        // 设置文本消息部分
        multipart.addBodyPart(messageBodyPart);
        for (Attachment attachment : mail.getAttachments())
        {
            messageBodyPart = new MimeBodyPart();
            String path = attachment.getPath();
            DataSource source = new FileDataSource(path);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachment.getFilename());
            multipart.addBodyPart(messageBodyPart);
        }
        message.setContent(multipart);
        if (null != consumer)
        {
            consumer.accept(message);
        }
        return message;
    }

    /**
     * @Author summer
     * @Description 发送邮件
     * @Date 11:04 2023/4/26
     * @Param [message]
     * @return void
     **/
    private void doSend(MimeMessage message)
    {
        try
        {
            Transport.send(message);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    String getMailServer()
    {
        return mailServer;
    }

    void setMailServer(String mailServer)
    {
        this.mailServer = mailServer;
    }

    String getUsername()
    {
        return username;
    }

    void setUsername(String username)
    {
        this.username = username;
    }

    String getPassword()
    {
        return password;
    }

    void setPassword(String password)
    {
        this.password = password;
    }

    public static void main(String[] args)
    {
        try
        {
            MailSender sender = new MailSender();
            sender.setUsername("6800@nesc.cn");
            sender.setMailServer("mail.nesc.cn");
            sender.setPassword("password");
//            sender.sendMail("42951690@qq.com", "主题", "内容哦", EmailType.PLAIN);
//            sender.sendMailWithAttachment("42951690@qq.com", "6800@nesc.cn", "zhuti", "<h1>This is actual message</h1>", EmailType.HTML, "/Users/summer/Desktop/22.sql");


            sender.sendSimpleMail("42951690@qq.com", "主题", "内容哦", EmailType.PLAIN);



//            List<Recipient> recipients = new ArrayList<>();
//            Recipient to = new TORecipient("42951690@qq.com");
//            Recipient cc = new CCRecipient("1254687833@qq.com");
//            Recipient bcc = new BCCRecipient("6800@nesc.cn");
//            recipients.add(to);
//            recipients.add(cc);
//            recipients.add(bcc);
//            List<Attachment> attachments = new ArrayList<>();
//            Attachment a1 = new Attachment("/Users/summer/Desktop/22.sql");
//            Attachment a2 = new Attachment("/Users/summer/Desktop/东北ICRM无限期license.zip");
//            attachments.add(a1);
//            attachments.add(a2);
//            sender.sendCustomMail(recipients, "打开看看", "<h1><font color=\"red\">很棒啊</font></h1>", EmailType.HTML, attachments, mimeMessage -> {
//                try
//                {
//                    mimeMessage.addHeader("Disposition-Notification-To", "1");
//                    mimeMessage.reply(true);
//                    mimeMessage.saveChanges();
//                }
//                catch (MessagingException e)
//                {
//                    throw new RuntimeException(e);
//                }
//            });
        }
//        catch (MailFormatException e)
//        {
//            throw new RuntimeException(e);
//        }
        catch (MailSenderException e)
        {
            throw new RuntimeException(e);
        }
    }
}
