/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: ESDoc
 * Author:   summer
 * Date:     2022/1/10 13:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es.common;

/**
 * @ClassName ESDoc
 * @Description ES文档
 * @Author summer
 * @Date 2022/1/10 13:10
 **/
public class ESDoc<T>
{
    // 文档id
    public String id;

    // 文档数据
    public T data;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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
