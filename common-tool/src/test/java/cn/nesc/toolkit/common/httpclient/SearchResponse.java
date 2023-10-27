/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: SearchResponse
 * Author:   summer
 * Date:     2023/10/25 13:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.httpclient;

/**
 * @ClassName SearchResponse
 * @Description
 * @Author summer
 * @Date 2023/10/25 13:52
 **/
public class SearchResponse
{
    private int code;
    private int action;
    private String msg;
    private SearchResult<SearchItem> result;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getAction()
    {
        return action;
    }

    public void setAction(int action)
    {
        this.action = action;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public SearchResult<SearchItem> getResult()
    {
        return result;
    }

    public void setResult(SearchResult<SearchItem> result)
    {
        this.result = result;
    }
}
