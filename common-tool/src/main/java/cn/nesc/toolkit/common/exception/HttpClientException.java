/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: HttpClientException
 * Author:   summer
 * Date:     2023/10/27 13:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.exception;

/**
 * @ClassName HttpClientException
 * @Description HttpClientUtil异常定义
 * @Author summer
 * @Date 2023/10/27 13:49
 **/
public class HttpClientException extends BaseException
{
    public HttpClientException()
    {
        super();
    }

    public HttpClientException(String code, String message, Throwable cause)
    {
        super(code, message, cause);
    }

    public HttpClientException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public HttpClientException(String message)
    {
        super(message);
    }

    public HttpClientException(Throwable cause)
    {
        super(cause);
    }
}
