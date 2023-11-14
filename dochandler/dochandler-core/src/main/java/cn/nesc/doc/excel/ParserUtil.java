/**********************************************************************
* <pre>
* FILE : ExcelUtil.java
* CLASS : ExcelUtil
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
* 		  |2010-9-25| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: ParserUtil.java,v 1.2 2013/03/15 09:27:13 weibin Exp $
*/

package cn.nesc.doc.excel;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-9-25
 * @version    :
 */
public class ParserUtil
{
	public static String spellErrMsg(int colNum, String errMsg)
	{
		return colNum + "." + errMsg;
	}
}
