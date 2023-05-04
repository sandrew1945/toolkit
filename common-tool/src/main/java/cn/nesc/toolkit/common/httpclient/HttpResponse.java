package cn.nesc.toolkit.common.httpclient;


/**
 * Created by summer on 2020/3/27.
 */
public class HttpResponse
{
    private int returnCode;

    private String returnContent;

    public int getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(int returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getReturnContent()
    {
        return returnContent;
    }

    public void setReturnContent(String returnContent)
    {
        this.returnContent = returnContent;
    }
}
