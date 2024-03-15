package cn.nesc.itruscloud.parameter;

import cn.nesc.itruscloud.bean.Contract;

/**
 * @ClassName CreateContractParam
 * @Description
 * @Author summer
 * @Date 2022/9/6 15:11
 **/
public class CreateContractParam extends ItrusBaseParam
{
    private Contract contract;

    private Boolean docRequired;

    private Boolean applyForWitness;

    private String legalDocType;        // 当申请见证服务时需要。法律文书类型，固定为"签署见证意见书"

    public Contract getContract()
    {
        return contract;
    }

    public void setContract(Contract contract)
    {
        this.contract = contract;
    }

    public Boolean getDocRequired()
    {
        return docRequired;
    }

    public void setDocRequired(Boolean docRequired)
    {
        this.docRequired = docRequired;
    }

    public Boolean getApplyForWitness()
    {
        return applyForWitness;
    }

    public void setApplyForWitness(Boolean applyForWitness)
    {
        this.applyForWitness = applyForWitness;
    }

    public String getLegalDocType()
    {
        return legalDocType;
    }

    public void setLegalDocType(String legalDocType)
    {
        this.legalDocType = legalDocType;
    }
}
