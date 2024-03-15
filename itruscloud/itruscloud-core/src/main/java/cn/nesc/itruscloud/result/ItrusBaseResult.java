package cn.nesc.itruscloud.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName ItrusBaseResult
 * @Description
 * @Author summer
 * @Date 2022/9/5 15:03
 **/
public class ItrusBaseResult
{
    @JsonProperty("isOk")
    protected boolean success;

    protected Integer code;

    protected String message;

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
