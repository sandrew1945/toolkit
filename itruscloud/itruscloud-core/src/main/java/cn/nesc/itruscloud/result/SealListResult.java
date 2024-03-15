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
