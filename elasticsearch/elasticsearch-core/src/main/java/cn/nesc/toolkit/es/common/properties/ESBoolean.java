/**
 * Copyright (C), 2015-2021, 东北证券股份有限公司
 * FileName: ESText
 * Author:   summer
 * Date:     2021/12/31 14:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.common.properties;

import cn.nesc.toolkit.es.common.Types;

/**
 * @ClassName ESText
 * @Description
 * @Author summer
 * @Date 2021/12/31 14:19
 **/
public class ESBoolean extends ESType
{
    public ESBoolean()
    {
        this.type = Types.BOOLEAN;
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
