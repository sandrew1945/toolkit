/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: ListResult
 * Author:   summer
 * Date:     2022/9/6 09:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.itruscloud.result;

import java.util.List;

/**
 * @ClassName ListResult
 * @Description
 * @Author summer
 * @Date 2022/9/6 09:11
 **/
public class ListResult<T>
{
    private Integer total;

    private List<T> list;

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }

    public List<T> getList()
    {
        return list;
    }

    public void setList(List<T> list)
    {
        this.list = list;
    }
}
