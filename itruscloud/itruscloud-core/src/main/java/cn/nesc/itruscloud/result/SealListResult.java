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
 * @ClassName SealListResult
 * @Description 查询印章列表result
 * @Author summer
 * @Date 2022/9/6 09:35
 **/
public class SealListResult extends ItrusBaseResult
{
    private ListResult<Seal> seals;

    public ListResult<Seal> getSeals()
    {
        return seals;
    }

    public void setSeals(ListResult<Seal> seals)
    {
        this.seals = seals;
    }
}
