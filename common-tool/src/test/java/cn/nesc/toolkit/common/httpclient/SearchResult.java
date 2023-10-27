/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: SearchResult
 * Author:   summer
 * Date:     2023/10/25 13:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.httpclient;

import java.util.List;

/**
 * @ClassName SearchResult
 * @Description
 * @Author summer
 * @Date 2023/10/25 13:54
 **/
public class SearchResult<T>
{
    private int pageNum;

    private int pageSize;

    private int total;

    private String reqId;

    private List<T> items;

    public int getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public String getReqId()
    {
        return reqId;
    }

    public void setReqId(String reqId)
    {
        this.reqId = reqId;
    }

    public List<T> getItems()
    {
        return items;
    }

    public void setItems(List<T> items)
    {
        this.items = items;
    }
}
