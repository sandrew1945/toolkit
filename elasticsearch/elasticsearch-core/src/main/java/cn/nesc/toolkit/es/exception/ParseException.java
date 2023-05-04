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
* $Id: ESException.java,v 1.1 2013/07/31 08:32:47 xin.jin Exp $
*/
package cn.nesc.toolkit.es.exception;

import cn.nesc.toolkit.common.exception.BaseException;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2009-11-2
 * @version    :
 */
public class ParseException extends BaseException
{

	/**
	 *
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public ParseException()
	{
		super();
	}

	public ParseException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ParseException(String message)
	{
		super(message);
	}

	public ParseException(Throwable cause)
	{
		super(cause);
	}

	public ParseException(String code, String message, Throwable cause)
	{
		super(code, message, cause);
	}

	public ParseException(BaseException e, String message)
	{
		super(e.getCode(), message, e);
	}

	public ParseException(BaseException e)
	{
		super(e.getCode(), e.getMessage(), e);
	}
}
