
package cn.nesc.toolkit.es.common.properties;

import cn.nesc.toolkit.es.common.Types;

/**
 * @ClassName ESText
 * @Description
 * @Author summer
 * @Date 2021/12/31 14:19
 **/
public class ESFloat extends ESType
{
    public ESFloat()
    {
        this.type = Types.FLOAT;
    }

    @Override
    public String getFormat()
    {
        return null;
    }

    @Override
    public void setFormat(String format)
    {

    }
}
