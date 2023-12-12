/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: IDAASUtil
 * Author:   summer
 * Date:     2023/12/7 15:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;

/**
 * @ClassName IDAASUtil
 * @Description
 * @Author summer
 * @Date 2023/12/7 15:25
 **/
public class IDAASUtil
{
    private static final String TOKEN = "oauth/token";        // 获取accessToken
    private static final String USER_INFO = "api/bff/v1.2/oauth2/userinfo";   // 获取用户信息
    private static final String GRANT_TYPE = "authorization_code";             // grant_type
    private static final String SLO = "public/sp/slo/testplugin_oauth2140";   // 登出

    /**
     * @Author summer
     * @Description 获取查询accessToken服务url
     * @Date 15:38 2023/12/7
     * @Param [idaasUrl]
     * @return java.lang.String
     **/
    public static String accessTokenUrl(String idaasUrl)
    {
        if (null != idaasUrl && idaasUrl.length() > 0)
        {
            return idaasUrl.endsWith("/") ? idaasUrl + TOKEN : idaasUrl + "/" + TOKEN;
        }
        return null;
    }

    /**
     * @Author summer
     * @Description 获取查询用户信息服务url
     * @Date 15:38 2023/12/7
     * @Param [idaasUrl]
     * @return java.lang.String
     **/
    public static String userInfoUrl(String idaasUrl)
    {
        if (null != idaasUrl && idaasUrl.length() > 0)
        {
            return idaasUrl.endsWith("/") ? idaasUrl + USER_INFO : idaasUrl + "/" + USER_INFO;
        }
        return null;
    }

    /**
     * @Author summer
     * @Description 获取登出服务url
     * @Date 15:40 2023/12/7
     * @Param [idaasUrl]
     * @return java.lang.String
     **/
    public static String logoutUrl(String idaasUrl)
    {
        if (null != idaasUrl && idaasUrl.length() > 0)
        {
            return idaasUrl.endsWith("/") ? idaasUrl + SLO : idaasUrl + "/" + SLO;
        }
        return null;
    }

    /**
     * @Author summer
     * @Description 获取GrantType
     * @Date 15:41 2023/12/7
     * @Param []
     * @return java.lang.String
     **/
    public static String getGrantType()
    {
        return GRANT_TYPE;
    }
}
