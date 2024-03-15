package cn.nesc.itruscloud.parameter;

/**
 * @ClassName SealParam
 * @Description 印章请求param
 * @Author summer
 * @Date 2022/9/6 09:32
 **/
public class SealParam extends ItrusBaseParam
{
    private String userId;

    private String sealName;

    private Integer pageNum;

    private Integer pageSize;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getSealName()
    {
        return sealName;
    }

    public void setSealName(String sealName)
    {
        this.sealName = sealName;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
}
