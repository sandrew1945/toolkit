/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: ASCSorter
 * Author:   summer
 * Date:     2022/1/18 14:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.common.sort;

import org.elasticsearch.search.sort.SortOrder;

/**
 * @ClassName ASCSorter
 * @Description
 * @Author summer
 * @Date 2022/1/18 14:54
 **/
public class ASCSorter extends Sorter
{
    public ASCSorter(String field, Boolean isText)
    {
        this.field = field;
        this.order = SortOrder.ASC;
        this.isText = isText;
    }

    public ASCSorter(String field)
    {
        this.field = field;
        this.order = SortOrder.ASC;
        this.isText = false;
    }
}
