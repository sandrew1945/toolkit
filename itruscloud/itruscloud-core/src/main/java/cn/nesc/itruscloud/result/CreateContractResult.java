/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: CreateContractResult
 * Author:   summer
 * Date:     2022/9/6 15:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

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
