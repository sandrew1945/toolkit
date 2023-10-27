/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: SearchPayload
 * Author:   summer
 * Date:     2023/10/25 13:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.httpclient;

/**
 * @ClassName SearchPayload
 * @Description
 * @Author summer
 * @Date 2023/10/25 13:39
 **/
public class SearchPayload
{
    private int pageNum;
    private int pageSize;
    private String keyword;

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

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
}
