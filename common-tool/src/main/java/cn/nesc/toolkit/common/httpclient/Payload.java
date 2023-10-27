/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: Payload
 * Author:   summer
 * Date:     2023/10/26 10:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.httpclient;

/**
 * @ClassName Payload
 * @Description HTTP message payload body
 * @Author summer
 * @Date 2023/10/26 10:50
 **/
public class Payload<T>
{
    private T payload;


    public Payload(T payload)
    {
        this.payload = payload;
    }

    /**
     * @Author summer
     * @Description 获取实际body对象
     * @Date 10:56 2023/10/26
     * @Param []
     * @return T
     **/
    public T getPayload()
    {
        return payload;
    }
}
