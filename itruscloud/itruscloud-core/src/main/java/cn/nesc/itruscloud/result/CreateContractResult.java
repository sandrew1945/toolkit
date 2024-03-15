package cn.nesc.itruscloud.result;

import cn.nesc.itruscloud.bean.Contract;

/**
 * @ClassName CreateContractResult
 * @Description
 * @Author summer
 * @Date 2022/9/6 15:13
 **/
public class CreateContractResult extends ItrusBaseResult
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
