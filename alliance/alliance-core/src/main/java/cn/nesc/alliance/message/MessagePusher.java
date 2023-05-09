/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: MessagePusher
 * Author:   summer
 * Date:     2022/6/20 14:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.alliance.message;

import cn.nesc.alliance.exception.AllianceException;
import cn.nesc.alliance.result.AccessTokenResult;
import cn.nesc.alliance.result.PushMessageResult;
import cn.nesc.toolkit.common.Result;
import cn.nesc.toolkit.common.httpclient.HttpResponse;
import cn.nesc.toolkit.common.httpclient.PoolingHttpClientUtil;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MessagePusher
 * @Description
 * @Author summer
 * @Date 2022/6/20 14:01
 **/
public class MessagePusher
{

    private static Logger log = LoggerFactory.getLogger(MessagePusher.class);

    private String allianceUrl;

    private String agentId;

    private String corpId;

    private String corpSecret;

    private boolean dummySend;

    private final static String ACCESS_TOKEN_URL = "cgi-bin/gettoken";

    private final static String MSG_PUSH_URL = "cgi-bin/message/send";

    private PoolingHttpClientUtil poolingHttpClientUtil = new PoolingHttpClientUtil();


    public Result<String> sendMessage(String userCode, String message) throws AllianceException
    {
        log.debug("Send alliance message to " + userCode + ".");
        List<String> userCodes = new ArrayList<>();
        userCodes.add(userCode);
        return sendMessage(userCodes, message);
    }

    /**
     * @Author summer
     * @Description 推送联盟消息
     * @Date 09:02 2022/6/21
     * @Param [userCodes, message]
     * @return cn.nesc.toolkit.common.Result<java.lang.String>
     **/
    public Result<String> sendMessage(List<String> userCodes, String message) throws AllianceException
    {
        log.debug("Send alliance message to " + Arrays.toString(userCodes.toArray()) + ".");
        Result<String> result = new Result<>();
        if (dummySend)
        {
            return result.requestSuccess("The dummy jobId");
        }
        // 获取accessToken
        String accessToken = getAccessToken();
        // 推送消息
        String msgApiUrl = allianceUrl.endsWith("/") ? allianceUrl : allianceUrl + "/" + MSG_PUSH_URL + "?access_token=" + accessToken;
        String toUser = userCodes.stream().collect(Collectors.joining("|"));
        TextMessageBody body = new TextMessageBody();
        body.setAgentid(agentId);
        body.setTouser(toUser);
        body.setText(message);
        log.debug(JsonUtil.javaObject2String(body));
        HttpResponse pushRes = poolingHttpClientUtil.sendHttpPost(msgApiUrl, body, null);
        if (isSuccessful(pushRes))
        {
            PushMessageResult pushMessageResult = JsonUtil.string2JavaObject(pushRes.getReturnContent(), PushMessageResult.class);
            return result.requestSuccess(pushMessageResult.getJobId());
        }
        return result.requestFailure("消息发送失败");
    }

    /**
     * @Author summer
     * @Description  获取联盟accessToken
     * @Date 08:59 2022/6/21
     * @Param []
     * @return java.lang.String
     **/
    private String getAccessToken() throws AllianceException
    {
        String accessToken = null;
        // 获取accessToken
        String accessTokenUrl = allianceUrl.endsWith("/") ? allianceUrl : allianceUrl + "/" + ACCESS_TOKEN_URL;
        Map<String, String> params = new HashMap<>();
        params.put("corpid", corpId);
        params.put("corpsecret", corpSecret);
        HttpResponse tokenRes = poolingHttpClientUtil.sendHttpGet(accessTokenUrl, params, (Map<String, String>) null);
        if (isSuccessful(tokenRes))
        {
            AccessTokenResult accessTokenResult = JsonUtil.string2JavaObject(tokenRes.getReturnContent(), AccessTokenResult.class);
            accessToken = accessTokenResult.getAccessToken();
        }
        if (null == accessToken)
        {
            throw new AllianceException("消息推送失败: 无法获取accessToken");
        }
        return accessToken;
    }

    private boolean isSuccessful(HttpResponse response)
    {
        // 判断返回状态码
        if (200 == response.getReturnCode())
        {
            // 判断返回code
            JsonNode jsonNode = JsonUtil.string2JsonObject(response.getReturnContent());
            JsonNode code = jsonNode.get("errcode");
            if (null == code)
            {
                return false;
            }
            return code.asInt() == 0 ? true : false;
        }
        return false;
    }


    public String getAllianceUrl()
    {
        return allianceUrl;
    }

    public void setAllianceUrl(String allianceUrl)
    {
        this.allianceUrl = allianceUrl;
    }

    public String getAgentId()
    {
        return agentId;
    }

    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
    }

    public String getCorpId()
    {
        return corpId;
    }

    public void setCorpId(String corpId)
    {
        this.corpId = corpId;
    }

    public String getCorpSecret()
    {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret)
    {
        this.corpSecret = corpSecret;
    }

    public boolean isDummySend()
    {
        return dummySend;
    }

    public void setDummySend(boolean dummySend)
    {
        this.dummySend = dummySend;
    }

    public static void main(String[] args) throws AllianceException
    {
        MessagePusher pusher = new MessagePusher();
        pusher.setAllianceUrl("https://qywxlocal.nesc.cn:7443");
        pusher.setAgentId("1000053");
        pusher.setCorpId("ww3c6024bb94ecef59");
        pusher.setCorpSecret("KxMuOlI-d5sdihICM4dBV4Bto0_MofSV3f5-j7grUik");
        pusher.setDummySend(false);

        List<String> users = new ArrayList<>();
        users.add("6800");
        pusher.sendMessage(users, "通过工具推送");

    }
}
