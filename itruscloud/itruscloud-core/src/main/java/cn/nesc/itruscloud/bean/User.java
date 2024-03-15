package cn.nesc.itruscloud.bean;

/**
 * @ClassName User
 * @Description 用户信息
 * @Author summer
 * @Date 2022/9/6 09:08
 **/
public class User
{
    private String userId;

    private Integer userType;

    private String identifyField;

    private String tidentifyField;

    private String personalName;

    private String idCardNum;

    private String mobile;

    private String orgName;

    private String legalPersonName;

    private String usci;

    private String transactorIdCardNum;

    private String transactorName;

    private String transactorMobile;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Integer getUserType()
    {
        return userType;
    }

    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }

    public String getIdentifyField()
    {
        return identifyField;
    }

    public void setIdentifyField(String identifyField)
    {
        this.identifyField = identifyField;
    }

    public String getTidentifyField()
    {
        return tidentifyField;
    }

    public void setTidentifyField(String tidentifyField)
    {
        this.tidentifyField = tidentifyField;
    }

    public String getPersonalName()
    {
        return personalName;
    }

    public void setPersonalName(String personalName)
    {
        this.personalName = personalName;
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

    public String getOrgName()
    {
        return orgName;
    }

    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    public String getLegalPersonName()
    {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName)
    {
        this.legalPersonName = legalPersonName;
    }

    public String getUsci()
    {
        return usci;
    }

    public void setUsci(String usci)
    {
        this.usci = usci;
    }

    public String getTransactorIdCardNum()
    {
        return transactorIdCardNum;
    }

    public void setTransactorIdCardNum(String transactorIdCardNum)
    {
        this.transactorIdCardNum = transactorIdCardNum;
    }

    public String getTransactorName()
    {
        return transactorName;
    }

    public void setTransactorName(String transactorName)
    {
        this.transactorName = transactorName;
    }

    public String getTransactorMobile()
    {
        return transactorMobile;
    }

    public void setTransactorMobile(String transactorMobile)
    {
        this.transactorMobile = transactorMobile;
    }

    @Override
    public String toString()
    {
        return "User [userId : " + userId +
                ", personalName : " + personalName +
                ", idCardNum : " + idCardNum +
                ", mobile : " + mobile +
                ", orgName : " + orgName +
                ", legalPersonName : " + legalPersonName +
                ", transactorIdCardNum : " + transactorIdCardNum +
                ", transactorName : " + transactorName +
                ", transactorMobile : " + transactorMobile +
                "]";
    }
}
