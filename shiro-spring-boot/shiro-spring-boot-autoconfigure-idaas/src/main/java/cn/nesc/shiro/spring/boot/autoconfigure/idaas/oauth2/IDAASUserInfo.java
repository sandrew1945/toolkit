package cn.nesc.shiro.spring.boot.autoconfigure.idaas.oauth2;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @ClassName IDAASUserInfo
 * @Description
 * @Author summer
 * @Date 2023/11/24 14:34
 **/
public class IDAASUserInfo
{
    private String sub;
    private String ouid;
    private String nickname;
    @JsonAlias("phone_number")
    private String phoneNumber;
    @JsonAlias("ou_name")
    private String ouName;
    private String email;
    private String username;

    public String getSub()
    {
        return sub;
    }

    public void setSub(String sub)
    {
        this.sub = sub;
    }

    public String getOuid()
    {
        return ouid;
    }

    public void setOuid(String ouid)
    {
        this.ouid = ouid;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getOuName()
    {
        return ouName;
    }

    public void setOuName(String ouName)
    {
        this.ouName = ouName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("IDAASUserInfo(");
        sb.append("sub=").append(this.getSub()).append(", ");
        sb.append("ouid=").append(this.getOuid()).append(", ");
        sb.append("nickname=").append(this.getNickname()).append(", ");
        sb.append("phoneNumber=").append(this.getPhoneNumber()).append(", ");
        sb.append("ouName=").append(this.getOuName()).append(", ");
        sb.append("email=").append(this.getEmail()).append(", ");
        sb.append("username=").append(this.getUsername());
        sb.append(")");
        return sb.toString();
    }
}
