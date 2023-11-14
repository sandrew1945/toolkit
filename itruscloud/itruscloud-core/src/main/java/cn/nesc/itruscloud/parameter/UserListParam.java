/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: UserListParam
 * Author:   summer
 * Date:     2022/9/5 15:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.itruscloud.parameter;

/**
 * @ClassName UserListParam
 * @Description 查询用户列表输入参数
 * @Author summer
 * @Date 2022/9/5 15:21
 **/
public class UserListParam extends ItrusBaseParam
{
    private String apiId;

    private Integer userType;

    private String fullname;

    private String orgName;

    private String idCardNum;

    private String mobile;

    private Integer pageNum;

    private Integer pageSize;

    public String getApiId()
    {
        return apiId;
    }

    public void setApiId(String apiId)
    {
        this.apiId = apiId;
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

    public Integer getUserType()
    {
        return userType;
    }

    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public String getOrgName()
    {
        return orgName;
    }

    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getIdCardNum()
    {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum)
    {
        this.idCardNum = idCardNum;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
}
