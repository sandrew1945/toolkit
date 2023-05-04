/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: SealListResult
 * Author:   summer
 * Date:     2022/9/6 09:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.itruscloud.result;

import cn.nesc.itruscloud.bean.Seal;

/**
 * @ClassName SealResult
 * @Description 查询印章result
 * @Author summer
 * @Date 2022/9/6 09:35
 **/
public class SealResult extends ItrusBaseResult
{
    private Seal seal;

    public Seal getSeal()
    {
        return seal;
    }

    public void setSeal(Seal seal)
    {
        this.seal = seal;
    }
}
