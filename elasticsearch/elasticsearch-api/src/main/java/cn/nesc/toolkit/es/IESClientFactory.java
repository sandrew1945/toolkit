/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: IESClientFactory
 * Author:   summer
 * Date:     2022/1/4 10:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es;

/**
 * @ClassName IESClientFactory
 * @Description
 * @Author summer
 * @Date 2022/1/4 10:51
 **/
public interface IESClientFactory
{
    /**
     * @Author summer
     * @Description 获取elasticsearch客户端
     * @Date 10:52 2022/1/4
     * @Param connectTimeout
     * @Param socketTimeout
     * @Param requestTimeout
     * @return cn.nesc.toolkit.es.IESClient
     **/
    IESClient getClient(int connectTimeout, int socketTimeout, int requestTimeout);

    /**
     * @Author summer
     * @Description 获取elasticsearch客户端
     * @Date 10:52 2022/1/4
     * @Param connectTimeout  默认30000ms
     * @Param socketTimeout   默认30000ms
     * @Param requestTimeout  默认30000ms
     * @return cn.nesc.toolkit.es.IESClient
     **/
    IESClient getClient();
}
