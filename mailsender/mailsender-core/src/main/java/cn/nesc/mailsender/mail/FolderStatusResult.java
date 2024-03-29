package cn.nesc.mailsender.mail;

/**
 * @ClassName FolderStatusResult
 * @Description
 * @Author summer
 * @Date 2023/5/5 14:51
 **/
public class FolderStatusResult
{
    private int messageCount;

    private int newMessageCount;

    private int unReadMessageCount;

    private int deleteMessageCount;

    public int getMessageCount()
    {
        return messageCount;
    }

    public void setMessageCount(int messageCount)
    {
        this.messageCount = messageCount;
    }

    public int getNewMessageCount()
    {
        return newMessageCount;
    }

    public void setNewMessageCount(int newMessageCount)
    {
        this.newMessageCount = newMessageCount;
    }

    public int getUnReadMessageCount()
    {
        return unReadMessageCount;
    }

    public void setUnReadMessageCount(int unReadMessageCount)
    {
        this.unReadMessageCount = unReadMessageCount;
    }

    public int getDeleteMessageCount()
    {
        return deleteMessageCount;
    }

    public void setDeleteMessageCount(int deleteMessageCount)
    {
        this.deleteMessageCount = deleteMessageCount;
    }
}
