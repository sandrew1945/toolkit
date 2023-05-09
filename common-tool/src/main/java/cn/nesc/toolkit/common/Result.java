/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: Result
 * Author:   summer
 * Date:     2022/6/20 16:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common;

import cn.nesc.toolkit.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName Result
 * @Description
 * @Author summer
 * @Date 2022/6/20 16:38
 **/
public class Result<T>
{

    private String code;    // 返回代码

    private String msg;     // 错误信息

    private T data;         // 返回数据


    public Result requestSuccess(T data)
    {
        this.code = "200";
        this.msg = "请求成功";
        this.data = data;
        return this;
    }

    public Result requestSuccess()
    {
        this.code = "200";
        this.msg = "请求成功";
        return this;
    }

    public Result requestFailure(String errMsg)
    {
        this.code = "500";
        this.msg = StringUtils.isEmpty(errMsg) ? "系统错误" : errMsg;
        return this;
    }

    public Result requestFailure(BaseException ex)
    {
        this.code = "500";
        this.msg = StringUtils.isEmpty(ex.getMessage()) ? "系统错误" : ex.getMessage();
        return this;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
