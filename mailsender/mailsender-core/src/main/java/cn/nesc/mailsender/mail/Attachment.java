/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: Attachment
 * Author:   summer
 * Date:     2023/4/27 10:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.mailsender.mail;

import java.io.File;

/**
 * @ClassName Attachment
 * @Description
 * @Author summer
 * @Date 2023/4/27 10:45
 **/
public class Attachment
{
    private String path;

    private String filename;

    public Attachment()
    {
    }

    public Attachment(String path)
    {
        this.path = path;
        File file = new File(path);
        this.filename =  file.getName();
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }
}
