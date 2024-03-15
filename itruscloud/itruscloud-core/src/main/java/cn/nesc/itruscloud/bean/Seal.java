package cn.nesc.itruscloud.bean;

/**
 * @ClassName Seal
 * @Description 印章信息
 * @Author summer
 * @Date 2022/9/6 09:36
 **/
public class Seal
{
    private String sealId;

    private boolean isDefault;

    private String sealName;

    private String sealDesc;

    private String sealImage;

    private String sealImageType;

    private int sealImageSize;

    private int sealImageHeight;

    private int sealImageWidth;

    public String getSealId()
    {
        return sealId;
    }

    public void setSealId(String sealId)
    {
        this.sealId = sealId;
    }

    public boolean isDefault()
    {
        return isDefault;
    }

    public void setDefault(boolean aDefault)
    {
        isDefault = aDefault;
    }

    public String getSealName()
    {
        return sealName;
    }

    public void setSealName(String sealName)
    {
        this.sealName = sealName;
    }

    public String getSealDesc()
    {
        return sealDesc;
    }

    public void setSealDesc(String sealDesc)
    {
        this.sealDesc = sealDesc;
    }

    public String getSealImage()
    {
        return sealImage;
    }

    public void setSealImage(String sealImage)
    {
        this.sealImage = sealImage;
    }

    public String getSealImageType()
    {
        return sealImageType;
    }

    public void setSealImageType(String sealImageType)
    {
        this.sealImageType = sealImageType;
    }

    public int getSealImageSize()
    {
        return sealImageSize;
    }

    public void setSealImageSize(int sealImageSize)
    {
        this.sealImageSize = sealImageSize;
    }

    public int getSealImageHeight()
    {
        return sealImageHeight;
    }

    public void setSealImageHeight(int sealImageHeight)
    {
        this.sealImageHeight = sealImageHeight;
    }

    public int getSealImageWidth()
    {
        return sealImageWidth;
    }

    public void setSealImageWidth(int sealImageWidth)
    {
        this.sealImageWidth = sealImageWidth;
    }
}
