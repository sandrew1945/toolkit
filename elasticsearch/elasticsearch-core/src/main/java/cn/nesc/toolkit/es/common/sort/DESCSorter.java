package cn.nesc.toolkit.es.common.sort;

import org.elasticsearch.search.sort.SortOrder;

/**
 * @ClassName DESCSorter
 * @Description
 * @Author summer
 * @Date 2022/1/18 14:56
 **/
public class DESCSorter extends Sorter
{

    public DESCSorter(String field, Boolean isText)
    {
        this.field = field;
        this.order = SortOrder.DESC;
        this.isText = isText;
    }

    public DESCSorter(String field)
    {
        this.field = field;
        this.order = SortOrder.DESC;
        this.isText = false;
    }
}
