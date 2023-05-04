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
public class ESException extends BaseException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public ESException()
	{
		super();
	}

	public ESException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ESException(String message)
	{
		super(message);
	}

	public ESException(Throwable cause)
	{
		super(cause);
	}

	public ESException(String code, String message, Throwable cause)
	{
		super(code, message, cause);
	}

	public ESException(BaseException e, String message)
	{
		super(e.getCode(), message, e);
	}

	public ESException(BaseException e)
	{
		super(e.getCode(), e.getMessage(), e);
	}
}
