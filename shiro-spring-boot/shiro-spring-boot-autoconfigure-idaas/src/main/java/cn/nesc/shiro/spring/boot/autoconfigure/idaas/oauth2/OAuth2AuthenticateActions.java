package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;

import java.util.function.Consumer;

/**
 * @ClassName OAuth2AuthenticateActions
 * @Description
 * @Author summer
 * @Date 2023/11/27 15:17
 **/
public interface OAuth2AuthenticateActions
{

    OAuth2AuthenticateActions getAccessToken(Consumer<IDAASAccessToken> consumer);


    IDAASUserInfo getUserInfo();

}
