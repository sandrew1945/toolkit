package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;

import cn.nesc.toolkit.common.exception.HttpClientException;
import cn.nesc.toolkit.common.httpclient.HttpClientUtil;
import cn.nesc.toolkit.common.httpclient.HttpResponse;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * @ClassName IDAASAuthenticateProcess
 * @Description 使用IDAAS平台处理OAuth2认证
 * @Author summer
 * @Date 2023/11/27 15:30
 **/
public class IDAASAuthenticateProcess
{

    private static Logger log = LoggerFactory.getLogger(IDAASAuthenticateProcess.class);

    /**
     * @return cn.nesc.general.authcenter.config.shiro.oauth2.OAuth2AuthenticateActions
     * @Author summer
     * @Description
     * @Date 16:11 2023/11/27
     * @Param [code]
     **/
    public static OAuth2AuthenticateActions perform(String idaasUrl, String clientId, String clientSecret, String redirectURL, String code)
    {
        HttpClientUtil clientUtil = new HttpClientUtil();
        final IDAASAccessToken accessToken = new IDAASAccessToken();

        OAuth2AuthenticateActions actions = new OAuth2AuthenticateActions()
        {
            @Override
            public OAuth2AuthenticateActions getAccessToken(Consumer<IDAASAccessToken> consumer)
            {
                try
                {
                    String getAccessTokenUrl = IDAASUtil.accessTokenUrl(idaasUrl) + "?grant_type=" + IDAASUtil.getGrantType() + "&code=" + code + "&client_id=" + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8.toString());
                    HttpResponse accessTokenRes = clientUtil.sendHttpPost(getAccessTokenUrl, null);
                    if (200 == accessTokenRes.getReturnCode())
                    {
                        IDAASAccessToken token = JsonUtil.string2JavaObject(accessTokenRes.getReturnContent(), IDAASAccessToken.class);
                        BeanUtils.copyProperties(token, accessToken);
                        consumer.accept(token);
                        log.debug("access token:" + accessToken.getAccessToken());
                    }
                }
                catch (HttpClientException e)
                {
                    throw new RuntimeException(e);
                }
                catch (UnsupportedEncodingException e)
                {
                    throw new RuntimeException(e);
                }
                return this;
            }

            @Override
            public IDAASUserInfo getUserInfo()
            {
                IDAASUserInfo userInfo = null;
                try
                {
                    // 获取userInfo
                    String getUserInfoUrl = IDAASUtil.userInfoUrl(idaasUrl) + "?access_token=" + accessToken.getAccessToken();
                    HttpResponse userInfoRes = clientUtil.sendHttpGet(getUserInfoUrl, null);
                    if (200 == userInfoRes.getReturnCode())
                    {
                        JsonNode userInfoNode = JsonUtil.string2JsonObject(userInfoRes.getReturnContent()).get("data");
                        userInfo = JsonUtil.jsonObject2JavaObject(userInfoNode, IDAASUserInfo.class);
                        log.debug("usercode:" + userInfo.getUsername());
                        log.debug("username:" + userInfo.getNickname());
                    }
                }
                catch (HttpClientException e)
                {
                    throw new RuntimeException(e);
                }
                return userInfo;
            }
        };
        return actions;
    }
}
