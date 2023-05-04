/**********************************************************************
* <pre>
* FILE : EBException.java
* CLASS : EBException
*
* AUTHOR : SuMMeR
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2009-11-2| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: AllianceException.java,v 1.1 2013/07/31 08:32:47 xin.jin Exp $
*/
package cn.nesc.mailsender.exceptions;

import cn.nesc.toolkit.common.exception.BaseException;

/**
 * Function    :  邮件格式异常
 * @author     : SuMMeR
 * CreateDate  : 2009-11-2
 * @version    :
 */
public class MailFormatException extends BaseException
{

	/**
	 *
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public MailFormatException()
	{
		super();
	}

	public MailFormatException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public MailFormatException(String message)
	{
		super(message);
	}

	public MailFormatException(Throwable cause)
	{
		super(cause);
	}

	public MailFormatException(String code, String message, Throwable cause)
	{
		super(code, message, cause);
	}

	public MailFormatException(BaseException e, String message)
	{
		super(e.getCode(), message, e);
	}

	public MailFormatException(BaseException e)
	{
		super(e.getCode(), e.getMessage(), e);
	}
}
