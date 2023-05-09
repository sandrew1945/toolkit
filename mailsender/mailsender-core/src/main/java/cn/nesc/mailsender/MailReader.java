/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: MailReader
 * Author:   summer
 * Date:     2023/5/5 10:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.mailsender;

import cn.nesc.mailsender.exceptions.MailFormatException;
import cn.nesc.mailsender.exceptions.MailSenderException;
import cn.nesc.mailsender.mail.*;
import cn.nesc.toolkit.common.util.DateTimeUtil;
import com.sun.mail.imap.IMAPFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.search.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName MailReader
 * @Description
 * @Author summer
 * @Date 2023/5/5 10:31
 **/
public class MailReader
{
    private static Logger log = LoggerFactory.getLogger(MailReader.class);

    private String mailServer;

    private String username;

    private String password;

    /**
     * @return cn.nesc.mailsender.mail.FolderStatusResult
     * @Author summer
     * @Description 获取文件夹邮件状态
     * @Date 15:01 2023/5/5
     * @Param [folderName]
     **/
    public FolderStatusResult getFolderStatus(String folderName) throws MailSenderException
    {
        try
        {
            Store store = buildStore();
            Folder folder = getFolder(store, folderName);
            if (null == folder)
            {
                return new FolderStatusResult();
            }
            FolderStatusResult result = new FolderStatusResult();
            result.setMessageCount(folder.getMessageCount());
            result.setNewMessageCount(folder.getNewMessageCount());
            result.setUnReadMessageCount(folder.getUnreadMessageCount());
            result.setDeleteMessageCount(folder.getDeletedMessageCount());
            return result;
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @return void
     * @Author summer
     * @Description 读取最后count封邮件
     * @Date 15:19 2023/5/5
     * @Param [folderName, count, consumer]
     **/
    public void readEmailLastly(String folderName, int count, Consumer<Message> consumer) throws MailSenderException
    {
        try
        {
            Store store = buildStore();
            Folder folder = getFolder(store, folderName);
            if (null == folder)
            {
                throw new MailSenderException("Folder is not found");
            }
            int messageCount = getFolderStatus(folderName).getMessageCount();
            int start = messageCount - count + 1;
            Message[] messages = folder.getMessages(start, messageCount);
            for (int i = messages.length - 1; i >= 0; i--)
            {
                try
                {
                    Message msg = messages[i];
                    consumer.accept(msg);
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @return java.util.List<cn.nesc.mailsender.mail.Mail>
     * @Author summer
     * @Description 读取最后count封邮件
     * @Date 16:20 2023/5/5
     * @Param [folderName, count]
     **/
    public List<Mail> readEmailLastly(String folderName, int count) throws MailSenderException
    {
        try
        {
            Store store = buildStore();
            Folder folder = getFolder(store, folderName);
            if (null == folder)
            {
                throw new MailSenderException("Folder is not found");
            }
            int messageCount = getFolderStatus(folderName).getMessageCount();
            int start = messageCount - count + 1;
            Message[] messages = folder.getMessages(start, messageCount);
            List<Mail> mails = new ArrayList<>();
            Arrays.stream(messages).forEach(message -> {
                try
                {
                    Mail mail = parsingToMail(message);
                    // mail.setAttachments();
                    mails.add(mail);

                }
                catch (MessagingException | IOException | MailFormatException e)
                {
                    throw new RuntimeException(e);
                }

            });
            return mails;
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 读取指定日期之后的邮件
     * @Date 16:47 2023/5/5
     * @Param [folderName, afterDate, consumer]
     * @return void
     **/
    public void readEmailAfterDate(String folderName, LocalDate afterDate, Consumer<Message> consumer) throws MailSenderException
    {
        Date date = DateTimeUtil.convertDate(afterDate);
        SearchTerm condition = new ReceivedDateTerm(ComparisonTerm.GE, date);
        readEmailCustomized(folderName, condition, consumer);
    }

    /**
     * @Author summer
     * @Description 读取指定日期之后的邮件
     * @Date 16:49 2023/5/5
     * @Param [folderName, afterDate]
     * @return java.util.List<cn.nesc.mailsender.mail.Mail>
     **/
    public List<Mail> readEmailAfterDate(String folderName, LocalDate afterDate) throws MailSenderException
    {
        Date date = Date.from(afterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        SearchTerm condition = new ReceivedDateTerm(ComparisonTerm.GE, date);
        return readEmailCustomized(folderName, condition);
    }

    /**
     * @Author summer
     * @Description 读取标题或正文包含keywords的邮件
     * @Date 16:56 2023/5/5
     * @Param [folderName, keyword, consumer]
     * @return void
     **/
    public void readEmailContains(String folderName, String keyword, Consumer<Message> consumer) throws MailSenderException
    {
        SearchTerm condition = new OrTerm(new SubjectTerm(keyword), new BodyTerm(keyword));
        readEmailCustomized(folderName, condition, consumer);
    }

    /**
     * @Author summer
     * @Description 读取标题或正文包含keywords的邮件
     * @Date 17:03 2023/5/5
     * @Param [folderName, keyword]
     * @return java.util.List<cn.nesc.mailsender.mail.Mail>
     **/
    public List<Mail> readEmailContains(String folderName, String keyword) throws MailSenderException
    {
        SearchTerm condition = new OrTerm(new SubjectTerm(keyword), new BodyTerm(keyword));
        return readEmailCustomized(folderName, condition);
    }

    /**
     * @Author summer
     * @Description 读取指定日期后标题或正文包含keywords的邮件
     * @Date 10:12 2023/5/8
     * @Param [folderName, keyword, afterDate, consumer]
     * @return void
     **/
    public void readEmailContainsAfterDate(String folderName, String keyword, LocalDate afterDate, Consumer<Message> consumer) throws MailSenderException
    {
        SearchTerm keywordsCond = new OrTerm(new SubjectTerm(keyword), new BodyTerm(keyword));
        Date date = DateTimeUtil.convertDate(afterDate);
        SearchTerm dateCond = new ReceivedDateTerm(ComparisonTerm.GE, date);
        SearchTerm condition = new AndTerm(keywordsCond, dateCond);
        readEmailCustomized(folderName, condition, consumer);
    }


    /**
     * @Author summer
     * @Description 读取指定日期后标题或正文包含keywords的邮件
     * @Date 10:15 2023/5/8
     * @Param [folderName, keyword, afterDate]
     * @return java.util.List<cn.nesc.mailsender.mail.Mail>
     **/
    public List<Mail> readEmailContainsAfterDate(String folderName, String keyword, LocalDate afterDate) throws MailSenderException
    {
        SearchTerm keywordsCond = new OrTerm(new SubjectTerm(keyword), new BodyTerm(keyword));
        Date date = DateTimeUtil.convertDate(afterDate);
        SearchTerm dateCond = new ReceivedDateTerm(ComparisonTerm.GE, date);
        SearchTerm condition = new AndTerm(keywordsCond, dateCond);
        return readEmailCustomized(folderName, condition);
    }

    /**
     * @Author summer
     * @Description 读取未读邮件
     * @Date 10:10 2023/5/9
     * @Param [folderName, consumer]
     * @return void
     **/
    public void readEmailUnread(String folderName, Consumer<Message> consumer) throws MailSenderException
    {
        SearchTerm condition = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        readEmailCustomized(folderName, condition, consumer);
    }

    /**
     * @Author summer
     * @Description 读取未读邮件
     * @Date 10:12 2023/5/9
     * @Param [folderName]
     * @return java.util.List<cn.nesc.mailsender.mail.Mail>
     **/
    public List<Mail> readEmailUnread(String folderName) throws MailSenderException
    {
        SearchTerm condition = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        return readEmailCustomized(folderName, condition);
    }

    /**
     * @Author summer
     * @Description 读取指定日期之后未读邮件
     * @Date 10:16 2023/5/9
     * @Param [folderName, afterDate, consumer]
     * @return void
     **/
    public void readEmailUnreadAfter(String folderName, LocalDate afterDate, Consumer<Message> consumer) throws MailSenderException
    {
        SearchTerm unreadCond = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Date date = DateTimeUtil.convertDate(afterDate);
        SearchTerm dateCond = new ReceivedDateTerm(ComparisonTerm.GE, date);
        SearchTerm condition = new AndTerm(unreadCond, dateCond);
        readEmailCustomized(folderName, condition, consumer);
    }

    /**
     * @Author summer
     * @Description 读取指定日期之后未读邮件
     * @Date 10:16 2023/5/9
     * @Param [folderName, afterDate]
     * @return java.util.List<cn.nesc.mailsender.mail.Mail>
     **/
    public List<Mail> readEmailUnreadAfter(String folderName, LocalDate afterDate) throws MailSenderException
    {
        SearchTerm unreadCond = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
        Date date = DateTimeUtil.convertDate(afterDate);
        SearchTerm dateCond = new ReceivedDateTerm(ComparisonTerm.GE, date);
        SearchTerm condition = new AndTerm(unreadCond, dateCond);
        return readEmailCustomized(folderName, condition);
    }

    /**
     * @return void
     * @Author summer
     * @Description 客制化筛选读取
     * @Date 16:31 2023/5/5
     * @Param [folderName, condition, consumer]
     **/
    public void readEmailCustomized(String folderName, SearchTerm condition, Consumer<Message> consumer) throws MailSenderException
    {
        try
        {
            Store store = buildStore();
            Folder folder = getFolder(store, folderName);
            if (null == folder)
            {
                throw new MailSenderException("Folder is not found");
            }
            Message[] messages = folder.search(condition);
            for (int i = messages.length - 1; i >= 0; i--)
            {
                try
                {
                    Message msg = messages[i];
                    consumer.accept(msg);
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 客制化筛选读取
     * @Date 16:32 2023/5/5
     * @Param [folderName, condition]
     * @return java.util.List<cn.nesc.mailsender.mail.Mail>
     **/
    public List<Mail> readEmailCustomized(String folderName, SearchTerm condition) throws MailSenderException
    {
        try
        {
            Store store = buildStore();
            Folder folder = getFolder(store, folderName);
            if (null == folder)
            {
                throw new MailSenderException("Folder is not found");
            }
            Message[] messages = folder.search(condition);
            List<Mail> mails = new ArrayList<>();
            Arrays.stream(messages).forEach(message -> {
                try
                {
                    Mail mail = parsingToMail(message);
                    // mail.setAttachments();
                    mails.add(mail);

                }
                catch (MessagingException | IOException | MailFormatException e)
                {
                    throw new RuntimeException(e);
                }
            });
            return mails;
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @return cn.nesc.mailsender.mail.Mail
     * @Author summer
     * @Description 将Message解析为Mail
     * @Date 16:18 2023/5/5
     * @Param [message]
     **/
    private Mail parsingToMail(Message message) throws MessagingException, IOException, MailFormatException
    {
        Mail mail = new Mail();
        // 解析邮件主题
        mail.setSubject(message.getSubject());
        // 解析邮件正文
        String contentType = message.getContentType();
        MailBody mailBody = null;
        if (contentType.toLowerCase().startsWith("text/html"))
        {
            mailBody = new HtmlBody(message.getContent().toString());
        }
        else if (contentType.toLowerCase().startsWith("text/plain"))
        {
            mailBody = new PlainBody(message.getContent().toString());
        }
        else if (contentType.toLowerCase().startsWith("multipart"))
        {
            mailBody = new HtmlBody(getText(message));
        }
        mail.setMailBody(mailBody);
        // 解析发件人
        String from = isCompositeRecipient(message.getFrom()[0].toString()) ? extractRecipient(message.getFrom()[0].toString()) : message.getFrom()[0].toString();
        mail.setFrom(from);

        // 解析接收时间
        mail.setReceiveDate(message.getReceivedDate());
        // 解析投递时间
        mail.setDeliveredDate(message.getSentDate());
        // 解析收件人
        Address[] toList = message.getRecipients(MimeMessage.RecipientType.TO);
        if (null != toList && toList.length > 0)
        {
            for (Address address : toList)
            {
                String to = isCompositeRecipient(address.toString()) ? extractRecipient(address.toString()) : address.toString();
                mail.addTO(to);
            }
        }
        // 解析抄送人
        Address[] ccList = message.getRecipients(MimeMessage.RecipientType.CC);
        if (null != ccList && ccList.length > 0)
        {
            for (Address address : ccList)
            {
                String to = isCompositeRecipient(address.toString()) ? extractRecipient(address.toString()) : address.toString();
                mail.addCC(to);
            }
        }
        return mail;
    }

    public void readInbox() throws MailSenderException
    {
        try
        {
            Store store = buildStore();
            doRead(store, "INBOX", messages -> {
                for (int i = messages.length; i >= 0; i--)
                {
                    try
                    {
                        Message msg = messages[i];
                        Address[] fromAddress = msg.getFrom();
                        String from = fromAddress[0].toString();
                        String subject = msg.getSubject();
                        Address[] toList = msg.getRecipients(MimeMessage.RecipientType.TO);
                        Address[] ccList = msg.getRecipients(MimeMessage.RecipientType.CC);
                        String contentType = msg.getContentType();
                        String content = msg.getContent().toString();
                        log.debug("from ========> " + from);
                        log.debug("subject ========> " + subject);
                        if (null != toList && toList.length > 0)
                        {
                            log.debug("toList ========> " + toList.length);
                        }
                        if (null != ccList && ccList.length > 0)
                        {
                            log.debug("ccList ========> " + ccList.length);
                        }
                        log.debug("contentType ========> " + contentType);
                        log.debug("content ========> " + content);
                        log.debug("================================================================");
                    }
                    catch (MessagingException | IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }

    private Store buildStore() throws MessagingException
    {
        Properties properties = System.getProperties();
        // Setup mail server
        //        properties.setProperty("mail.store.protocol", "imaps");
        //        properties.setProperty("mail.imap.host", this.mailServer);
        //        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //        properties.setProperty("mail.imap.socketFactory.fallback","false");
        //        properties.setProperty("mail.imap.socketFactory.port", "993");
        // SSL Factory
        properties.put("mail.imap.socketFactory.port", "993");
        properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.imap.ssl.trust", "*");
        properties.setProperty("mail.imap.ssl.enable", "true");
        Session session = Session.getDefaultInstance(properties, null);
        //connect to message store
        Store store = session.getStore("imap");
        store.connect(this.mailServer, 993, this.username, this.password);//open the inbox folder
        return store;
    }

    /**
     * @return javax.mail.Folder
     * @Author summer
     * @Description 打开文件夹
     * @Date 14:56 2023/5/5
     * @Param [store, folderName]
     **/
    private Folder getFolder(Store store, String folderName) throws MailSenderException
    {
        try
        {
            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_ONLY);
            return folder;
        }
        catch (MessagingException e)
        {
            throw new MailSenderException(e.getMessage(), e);
        }
    }

    /**
     * @return void
     * @Author summer
     * @Description 发送邮件
     * @Date 11:04 2023/4/26
     * @Param [message]
     **/
    private void doRead(Store store, String folder, Consumer<Message[]> consumer)
    {
        try
        {
            IMAPFolder inbox = (IMAPFolder) store.getFolder(folder);
            inbox.open(Folder.READ_WRITE);//fetch messages
            Message[] messages = inbox.getMessages();//read messages
            consumer.accept(messages);
            //            for (int i = 0; i < messages.length; i++)
            //            {
            //                Message msg = messages[i];
            //                Address[] fromAddress = msg.getFrom();
            //                String from = fromAddress[0].toString();
            //                String subject = msg.getSubject();
            //                Address[] toList = msg.getRecipients(MimeMessage.RecipientType.TO);
            //                Address[] ccList = msg.getRecipients(MimeMessage.RecipientType.CC);
            //                String contentType = msg.getContentType();
            //            }
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * @return java.lang.String
     * @Author summer
     * @Description 解析邮件内容
     * @Date 15:49 2023/5/5
     * @Param [part]
     **/
    private String getText(Part part) throws MessagingException, IOException
    {
        if (part.isMimeType("text/*"))
        {
            String s = (String) part.getContent();
            //            textIsHtml = part.isMimeType("text/html");
            return s;
        }

        if (part.isMimeType("multipart/alternative"))
        {
            // prefer html text over plain text
            Multipart mp = (Multipart) part.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++)
            {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain"))
                {
                    if (text == null)
                        text = getText(bp);
                    continue;
                }
                else if (bp.isMimeType("text/html"))
                {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                }
                else
                {
                    return getText(bp);
                }
            }
            return text;
        }
        else if (part.isMimeType("multipart/*"))
        {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++)
            {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }
        return null;
    }

    private boolean isCompositeRecipient(String recipient)
    {
        return Pattern.matches("^.*(<|>).*$", recipient);
    }

    private String extractRecipient(String recipient)
    {
        String match = null;
        Pattern pattern = Pattern.compile("\\<(.*?)\\>");
        Matcher matcher = pattern.matcher(recipient);
        if (matcher.find())
        {
            match = matcher.group(1);
        }
        return match;
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
            MailReader reader = new MailReader();
            reader.setMailServer("mail.nesc.cn");
            reader.setUsername("6800@nesc.cn");
            reader.setPassword("password");
            //            reader.getFolderStatus("INBOX");

//            reader.readEmailLastly("INBOX", 10, message -> {
//                try
//                {
//                    Message msg = message;
//                    Address[] fromAddress = msg.getFrom();
//                    String from = fromAddress[0].toString();
//                    String subject = msg.getSubject();
//                    Address[] toList = msg.getRecipients(MimeMessage.RecipientType.TO);
//                    Address[] ccList = msg.getRecipients(MimeMessage.RecipientType.CC);
//                    String contentType = msg.getContentType();
//                    //                    String content = msg.getContent().toString();
//                    log.debug("from ========> " + from);
//                    log.debug("subject ========> " + subject);
//                    if (null != toList && toList.length > 0)
//                    {
//                        log.debug("toList ========> " + toList.length);
//                    }
//                    if (null != ccList && ccList.length > 0)
//                    {
//                        log.debug("ccList ========> " + ccList.length);
//                    }
//                    log.debug("contentType ========> " + contentType);
//
//                    String content = reader.getText(message);
//                    log.debug("content ========> " + content);
//                    //                    if (msg.isMimeType("multipart/*"))
//                    //                    {
//                    //                        Multipart mp = (Multipart) msg.getContent();
//                    //                        for (int i = 0; i < mp.getCount(); i++)
//                    //                        {
//                    //                            String s = reader.getText(mp.getBodyPart(i));
//                    //
//                    //                        }
//                    //                    }
//                    //                    else
//                    //                    {
//                    //                        log.debug("content ========> " + content);
//                    //                    }
//
//                    log.debug("================================================================");
//                }
//                catch (MessagingException | IOException e)
//                {
//                    throw new RuntimeException(e);
//                }
//            });

//            System.out.println(reader.isCompositeRecipient("=?UTF-8?B?6LCt56OK?= <tanlei@nesc.cn>"));
//            System.out.println(reader.extractRecipient("=?UTF-8?B?IiDlkajlrZDlh6EgIg==?= <zhouzf@linkstec.com>"));
            //            reader.readInbox();

//            List<Mail> mails = reader.readEmailLastly("inbox", 10);
//            System.out.println(mails.size());


            // 自定义查询
//            LocalDate localDate = LocalDate.of(2023, Month.APRIL, 28);
//            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//            SearchTerm condition = new ReceivedDateTerm(ComparisonTerm.GE, date);
//            List<Mail> mails = reader.readEmailCustomized("inbox", condition);
//            System.out.println(mails.size());

//            SearchTerm condition = new OrTerm(new SubjectTerm("目前"), new BodyTerm("目前"));
//            List<Mail> mails = reader.readEmailCustomized("inbox", condition);
//            System.out.println(mails.size());

//            List<Mail> mails = reader.readEmailContainsAfterDate("inbox", "测试", DateTimeUtil.createLocalDate(2023, 5, 9));
//            System.out.println(mails.size());

//            List<Mail> mails = reader.readEmailUnread("inbox");
//            System.out.println(mails.size());

            List<Mail> mails = reader.readEmailUnreadAfter("inbox", DateTimeUtil.createLocalDate(2023, 5, 9));
            System.out.println(mails.size());
        }
        catch (MailSenderException e)
        {
            throw new RuntimeException(e);
        }
    }
}
