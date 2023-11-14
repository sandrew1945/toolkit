/**********************************************************************
* <pre>
* FILE : BlankResult.java
* CLASS : BlankResult
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
* 		  |2010-9-21| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: BlankResult.java,v 1.1 2013/03/15 08:32:58 jinxin Exp $
*/

package cn.nesc.doc.excel;

/**
 * Function    : 单元格验证结果
 * @author     : SuMMeR
 * CreateDate  : 2010-9-21
 * @version    :
 */
public class BlankResult
{
	private boolean isValidate; // 是否验证通过标识

	private String errMsg; // 错误信息

	public boolean isValidate()
	{
		return isValidate;
	}

	public String getErrMsg()
	{
		return errMsg;
	}

	public void setErrMsg(String errMsg)
	{
		this.errMsg = errMsg;
	}

	public void setValidate(boolean isValidate)
	{
		this.isValidate = isValidate;
	}

}
