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
* $Id: JsonException.java,v 1.1 2013/07/31 08:32:47 xin.jin Exp $
*/
package cn.nesc.toolkit.common.exception;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2009-11-2
 * @version    :
 */
public class BaseException extends Exception
{
	protected String code;
	/**
	 *
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public BaseException()
	{
		super();
	}

	public BaseException(String code, String message, Throwable cause)
	{
		super(message, cause);
		this.code = code;
	}


	public BaseException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public BaseException(String message)
	{
		super(message);
	}

	public BaseException(Throwable cause)
	{
		super(cause);
	}

	public String getCode()
	{
		return code;
	}
}
