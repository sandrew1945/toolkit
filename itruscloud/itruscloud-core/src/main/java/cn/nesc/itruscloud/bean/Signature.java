/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: Signature
 * Author:   summer
 * Date:     2022/9/6 15:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.itruscloud.bean;

/**
 * @ClassName Signature
 * @Description 创建新合同所需的签章位置信息
 * @Author summer
 * @Date 2022/9/6 15:02
 **/
public class Signature
{
    private String userId;

    private String userRef;

    private String orgUnit;         // 二级机构名称

    private Boolean needSign;       // 用户是否需要签署，默认为true，设置为false表示用户无需签署，只是合同的相关方，仅能阅读和下载

    private Integer page;           // 签章在合同文档中的页码，从1开始，不超过文档最大页码

    private String pageRange;       // 签章在合同文档中的页码范围

    private Boolean pagingSeal;     // 表示加盖于多个页面右侧边的骑缝章

    private String positionKeyword; // 要求在关键词出现的位置签章

    private Boolean multiSign;      // 默认为false，设置为true表明在关键词模式与页码范围模式下，要求每个印章上均附着数字签名

    private Integer stampMax;       // 默认为10，指定关键词模式下最多签署几个印章

    private String positionPoint;   // 将印章置于签章定位点的哪个位置

    private Integer positionX;      // 签章在页面内的x坐标

    private Integer positionY;      // 签章在页面内的y坐标

    private Integer offsetX;        // 指定签章在x方向上的偏移量，默认为0

    private Integer offsetY;        // 指定签章在y方向上的偏移量，默认为0

    private String stampStyle;     // 有此字段则固定为"none"，表示印章不带任何样式

    private String sealName;        // 指定由哪个印章签署，特殊值"none"表示不使用印章图案

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserRef()
    {
        return userRef;
    }

    public void setUserRef(String userRef)
    {
        this.userRef = userRef;
    }

    public String getOrgUnit()
    {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit)
    {
        this.orgUnit = orgUnit;
    }

    public Boolean getNeedSign()
    {
        return needSign;
    }

    public void setNeedSign(Boolean needSign)
    {
        this.needSign = needSign;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public String getPageRange()
    {
        return pageRange;
    }

    public void setPageRange(String pageRange)
    {
        this.pageRange = pageRange;
    }

    public Boolean getPagingSeal()
    {
        return pagingSeal;
    }

    public void setPagingSeal(Boolean pagingSeal)
    {
        this.pagingSeal = pagingSeal;
    }

    public String getPositionKeyword()
    {
        return positionKeyword;
    }

    public void setPositionKeyword(String positionKeyword)
    {
        this.positionKeyword = positionKeyword;
    }

    public Boolean getMultiSign()
    {
        return multiSign;
    }

    public void setMultiSign(Boolean multiSign)
    {
        this.multiSign = multiSign;
    }

    public Integer getStampMax()
    {
        return stampMax;
    }

    public void setStampMax(Integer stampMax)
    {
        this.stampMax = stampMax;
    }

    public String getPositionPoint()
    {
        return positionPoint;
    }

    public void setPositionPoint(String positionPoint)
    {
        this.positionPoint = positionPoint;
    }

    public Integer getPositionX()
    {
        return positionX;
    }

    public void setPositionX(Integer positionX)
    {
        this.positionX = positionX;
    }

    public Integer getPositionY()
    {
        return positionY;
    }

    public void setPositionY(Integer positionY)
    {
        this.positionY = positionY;
    }

    public Integer getOffsetX()
    {
        return offsetX;
    }

    public void setOffsetX(Integer offsetX)
    {
        this.offsetX = offsetX;
    }

    public Integer getOffsetY()
    {
        return offsetY;
    }

    public void setOffsetY(Integer offsetY)
    {
        this.offsetY = offsetY;
    }

    public String getStampStyle()
    {
        return stampStyle;
    }

    public void setStampStyle(String stampStyle)
    {
        this.stampStyle = stampStyle;
    }

    public String getSealName()
    {
        return sealName;
    }

    public void setSealName(String sealName)
    {
        this.sealName = sealName;
    }
}
