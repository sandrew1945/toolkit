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
package cn.nesc.itruscloud.exception;

import cn.nesc.toolkit.common.exception.BaseException;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2009-11-2
 * @version    :
 */
public class ItrusException extends BaseException
{

	/**
	 *
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public ItrusException()
	{
		super();
	}

	public ItrusException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ItrusException(String message)
	{
		super(message);
	}

	public ItrusException(Throwable cause)
	{
		super(cause);
	}

	public ItrusException(String code, String message, Throwable cause)
	{
		super(code, message, cause);
	}

	public ItrusException(BaseException e, String message)
	{
		super(e.getCode(), message, e);
	}

	public ItrusException(BaseException e)
	{
		super(e.getCode(), e.getMessage(), e);
	}
}
