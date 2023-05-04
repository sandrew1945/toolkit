/**
 * Copyright (C), 2015-2021, 东北证券股份有限公司
 * FileName: JsonValidator
 * Author:   summer
 * Date:     2021/12/29 13:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.json;

/**
 * @ClassName JsonValidator
 * @Description
 * @Author summer
 * @Date 2021/12/29 13:19
 **/
public interface JsonValidator
{
    /**
     * @Author summer
     * @Description 验证jsonStr是否符合json规范
     * @Date 13:22 2021/12/29
     * @Param [jsonStr]
     * @return java.lang.Boolean
     **/
    Boolean isJson(String jsonStr);
}
