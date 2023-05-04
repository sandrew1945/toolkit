/**
 * Copyright (C), 2015-2021, 东北证券股份有限公司
 * FileName: JacksonValidator
 * Author:   summer
 * Date:     2021/12/29 13:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @ClassName JacksonValidator
 * @Description 通过jackson验证json是否符合规范
 * @Author summer
 * @Date 2021/12/29 13:23
 **/
public class JacksonValidator implements JsonValidator
{
    @Override
    public Boolean isJson(String jsonStr)
    {
        try
        {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonStr);
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }
}
