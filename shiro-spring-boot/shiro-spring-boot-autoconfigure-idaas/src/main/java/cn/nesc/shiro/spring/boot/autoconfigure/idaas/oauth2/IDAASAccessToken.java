/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: IDAASAccessToken
 * Author:   summer
 * Date:     2023/11/24 14:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @ClassName IDAASAccessToken
 * @Description
 * @Author summer
 * @Date 2023/11/24 14:32
 **/
public class IDAASAccessToken
{
    @JsonAlias("access_token")
    private String accessToken;
    @JsonAlias("token_type")
    private String tokenType;
    @JsonAlias("refresh_token")
    private String refreshToken;
    @JsonAlias("expires_in")
    private String expiresIn;
    private String scope;
    private String jti;

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getTokenType()
    {
        return tokenType;
    }

    public void setTokenType(String tokenType)
    {
        this.tokenType = tokenType;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn()
    {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn)
    {
        this.expiresIn = expiresIn;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public String getJti()
    {
        return jti;
    }

    public void setJti(String jti)
    {
        this.jti = jti;
    }
}
