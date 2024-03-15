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
