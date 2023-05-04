/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: POGenUtil
 * Author:   summer
 * Date:     2022/1/10 15:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.es;

import com.sandrew.bury.util.POGenerator;

/**
 * @ClassName POGenUtil
 * @Description
 * @Author summer
 * @Date 2022/1/10 15:22
 **/
public class POGenUtil
{
    public static void main(String[] args)
    {
        try
        {
            String configFile = "POConf.xml";
            POGenerator poGenerator = new POGenerator();
            poGenerator.genForPack("POConf.xml");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
