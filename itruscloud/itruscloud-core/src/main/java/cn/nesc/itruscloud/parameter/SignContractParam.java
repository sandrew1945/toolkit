/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: SignContractParam
 * Author:   summer
 * Date:     2022/9/6 16:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.itruscloud.parameter;

/**
 * @ClassName SignContractParam
 * @Description 签署合同param
 * @Author summer
 * @Date 2022/9/6 16:35
 **/
public class SignContractParam extends ItrusBaseParam
{
    private String contractId;

    private String userId;

    private String operatorId;

    private String sealName;        // 印章名称，指定签署时使用哪个印章签署，默认使用用户默认印章

    private String signatureId;     // 签章标识，指示在合同内的哪个签章上签名，不传此参数表示在该参与者的所有签章上签名

    private String authCode;        // 终端用户填写的短信验证码，暂时不需传

    private String authSign;        // authSign，用于授权证书签署

    private String clientIp;        // 终端用户的网络ip地址

    private Boolean docRequired;    // 值为true指示返回值需包含合同文档

    public String getContractId()
    {
        return contractId;
    }

    public void setContractId(String contractId)
    {
        this.contractId = contractId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(String operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getSealName()
    {
        return sealName;
    }

    public void setSealName(String sealName)
    {
        this.sealName = sealName;
    }

    public String getSignatureId()
    {
        return signatureId;
    }

    public void setSignatureId(String signatureId)
    {
        this.signatureId = signatureId;
    }

    public String getAuthCode()
    {
        return authCode;
    }

    public void setAuthCode(String authCode)
    {
        this.authCode = authCode;
    }

    public String getAuthSign()
    {
        return authSign;
    }

    public void setAuthSign(String authSign)
    {
        this.authSign = authSign;
    }

    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    public Boolean getDocRequired()
    {
        return docRequired;
    }

    public void setDocRequired(Boolean docRequired)
    {
        this.docRequired = docRequired;
    }
}
