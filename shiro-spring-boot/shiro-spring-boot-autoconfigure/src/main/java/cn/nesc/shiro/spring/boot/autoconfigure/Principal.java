package cn.nesc.shiro.spring.boot.autoconfigure;

import java.io.Serializable;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2016年10月25日
 * @version    :
 */
public class Principal implements Serializable
{
	private String name;

	private Integer Type;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getType()
	{
		return Type;
	}

	public void setType(Integer type)
	{
		Type = type;
	}
}
