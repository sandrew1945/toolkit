/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: Sorter
 * Author:   summer
 * Date:     2022/1/18 14:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.common.sort;

import org.elasticsearch.search.sort.SortOrder;

/**
 * @ClassName Sorter
 * @Description
 * @Author summer
 * @Date 2022/1/18 14:52
 **/
public abstract class Sorter
{
    protected String field;

    protected SortOrder order;

    protected Boolean isText;

    public String getField()
    {
        if (isText)
        {
            return field + ".keyword";
        }
        return field;
    }

    public SortOrder getOrder()
    {
        return order;
    }

    public Boolean getText()
    {
        return isText;
    }
}
