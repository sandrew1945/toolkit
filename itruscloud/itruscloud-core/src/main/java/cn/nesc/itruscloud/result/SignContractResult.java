package cn.nesc.itruscloud.result;

import cn.nesc.itruscloud.bean.Contract;

/**
 * @ClassName SignContractResult
 * @Description 签署合同result
 * @Author summer
 * @Date 2022/9/6 16:40
 **/
public class SignContractResult extends ItrusBaseResult
{
    private Contract contract;

    public Contract getContract()
    {
        return contract;
    }

    public void setContract(Contract contract)
    {
        this.contract = contract;
    }
}
