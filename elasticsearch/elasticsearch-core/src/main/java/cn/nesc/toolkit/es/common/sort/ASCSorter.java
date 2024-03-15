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
