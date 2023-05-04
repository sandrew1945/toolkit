/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: Base64Util
 * Author:   summer
 * Date:     2022/9/6 15:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @ClassName Base64Util
 * @Description
 * @Author summer
 * @Date 2022/9/6 15:39
 **/
public class Base64Util
{
    private static Logger log = LoggerFactory.getLogger(Base64Util.class);

    /**
     * @return java.lang.String
     * @Author summer
     * @Description base64编码
     * @Date 15:43 2022/9/6
     * @Param [original]
     **/
    public static String encode(String original)
    {
        String encodedStr = null;
        try
        {
            encodedStr = Base64.getEncoder().encodeToString(original.getBytes());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return encodedStr;
    }

    /**
     * @return java.lang.String
     * @Author summer
     * @Description base64解码
     * @Date 15:46 2022/9/6
     * @Param [encoded]
     **/
    public static String decode(String encoded)
    {
        String original = null;
        try
        {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            original = new String(decodedBytes);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return original;
    }

    /**
     * @return java.lang.String
     * @Author summer
     * @Description base64编码(文件)
     * @Date 15:43 2022/9/6
     * @Param original  源文件
     **/
    public static String encode(File original)
    {
        String encodedStr = null;
        try
        {
            byte[] inFileBytes = Files.readAllBytes(original.toPath());
            encodedStr = Base64.getEncoder().encodeToString(inFileBytes);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return encodedStr;
    }

    /**
     * @return java.lang.String
     * @Author summer
     * @Description base64编码(文件)
     * @Date 15:54 2022/9/6
     * @Param filePath  文件目录
     * @Param fileName  文件名称
     **/
    public static String encode(String filePath, String fileName)
    {
        String encodedStr = null;
        try
        {
            byte[] inFileBytes = Files.readAllBytes(Paths.get(filePath, fileName));
            encodedStr = Base64.getEncoder().encodeToString(inFileBytes);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return encodedStr;
    }

    /**
     * @Author summer
     * @Description 解码(文件)
     * @Date 15:58 2022/9/6
     * @Param encoded       编码字符串
     * @Param outputFile    输出的文件
     * @return void
     **/
    public static void decode(String encoded, File outputFile)
    {
        String original = null;
        try (FileOutputStream fos = new FileOutputStream(outputFile))
        {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            fos.write(decodedBytes);
            fos.flush();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @Author summer
     * @Description 解码(文件)
     * @Date 16:01 2022/9/6
     * @Param encoded           编码字符串
     * @Param fileOutputStream  输出文件流
     * @return void
     **/
    public static void decode(String encoded, FileOutputStream fileOutputStream)
    {
        try
        {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            fileOutputStream.write(decodedBytes);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            try
            {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            catch (IOException e)
            {
                log.error(e.getMessage(), e);
            }
        }
    }
}
