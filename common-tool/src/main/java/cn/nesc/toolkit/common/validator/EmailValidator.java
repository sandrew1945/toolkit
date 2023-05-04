/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: EmailValidator
 * Author:   summer
 * Date:     2023/4/26 13:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName EmailValidator
 * @Description
 * @Author summer
 * @Date 2023/4/26 13:23
 **/
public class EmailValidator
{
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator()
    {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * 验证输入的字符串是否为有效的电子邮件地址
     *
     * @param email 输入的电子邮件地址
     * @return 如果电子邮件地址有效，则返回 true；否则返回 false。
     */
    public boolean validate(final String email)
    {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
