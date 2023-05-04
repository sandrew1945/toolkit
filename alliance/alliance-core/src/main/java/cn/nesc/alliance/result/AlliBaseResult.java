/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: AlliBaseResult
 * Author:   summer
 * Date:     2022/6/20 15:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.alliance.result;

/**
 * @ClassName AlliBaseResult
 * @Description
 * @Author summer
 * @Date 2022/6/20 15:33
 **/
public class AlliBaseResult
{
    private int errcode;

    private String errmsg;

    public int getErrcode()
    {
        return errcode;
    }

    public void setErrcode(int errcode)
    {
        this.errcode = errcode;
    }

    public String getErrmsg()
    {
        return errmsg;
    }

    public void setErrmsg(String errmsg)
    {
        this.errmsg = errmsg;
    }
}
