/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: TextMessageBody
 * Author:   summer
 * Date:     2022/6/20 15:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.alliance.message;

/**
 * @ClassName TextMessageBody
 * @Description
 * @Author summer
 * @Date 2022/6/20 15:53
 **/
public class TextMessageBody extends BaseMessageBody
{
    private String  msgtype = "text";

    private Context text = new Context();

    public String getMsgtype()
    {
        return msgtype;
    }

    public Context getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text.setContent(text);
    }

    class Context
    {
        private String content;

        public String getContent()
        {
            return content;
        }

        public void setContent(String content)
        {
            this.content = content;
        }
    }
}
