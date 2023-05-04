/**
 * Copyright (C), 2015-2021, 东北证券股份有限公司
 * FileName: ESField
 * Author:   summer
 * Date:     2021/12/31 15:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.common;

import cn.nesc.toolkit.es.common.properties.ESType;

/**
 * @ClassName ESField
 * @Description  表字段
 * @Author summer
 * @Date 2021/12/31 15:10
 **/
public class ESField
{
    private String name;        // 字段名

    private ESType esType;      // 字段类型

    private String esStringType;// 字符型类型名

    public ESField()
    {
    }

    public ESField(String name, ESType esType)
    {
        this.name = name;
        this.esType = esType;
    }

    public ESField(String name, String esStringType)
    {
        this.name = name;
        this.esStringType = esStringType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ESType getEsType()
    {
        return esType;
    }

    public void setEsType(ESType esType)
    {
        this.esType = esType;
    }

    public String getEsStringType()
    {
        return esStringType;
    }

    public void setEsStringType(String esStringType)
    {
        this.esStringType = esStringType;
    }
}
