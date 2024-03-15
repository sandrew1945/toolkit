package cn.nesc.toolkit.es.common.properties;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ESType
 * @Description
 * @Author summer
 * @Date 2021/12/31 14:18
 **/
public abstract class ESType
{
    protected String type;

    public abstract String getFormat();

    public abstract void setFormat(String format);

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Map<String, String> toMap()
    {
        Map<String, String> propMap = new HashMap<>();
        propMap.put("type", this.type);
        if (null != getFormat())
        {
            propMap.put("format", getFormat());
        }
        return propMap;
    }
}
