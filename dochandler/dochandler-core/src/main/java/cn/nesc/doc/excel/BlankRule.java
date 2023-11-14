/**********************************************************************
 * <pre> FILE : BlankRule.java CLASS : BlankRule AUTHOR : SuMMeR FUNCTION : TODO
 * ====================================================================== CHANGE HISTORY LOG
 * ---------------------------------------------------------------------- MOD. NO.| DATE | NAME |
 * REASON | CHANGE REQ. ----------------------------------------------------------------------
 * |2010-9-20| SuMMeR| Created | DESCRIPTION: </pre>
 ***********************************************************************/
/**
 * $Id: BlankRule.java,v 1.3 2013/03/18 02:30:45 weibin Exp $
 */

package cn.nesc.doc.excel;

/**
 * Function    : 单元格验证规则
 * @author     : SuMMeR
 * CreateDate  : 2010-9-20
 * @version    :
 */
public abstract class BlankRule
{

	protected final int length;

	public BlankRule(int length)
	{
		this.length = length;
	}

	/**
	 * 
	 * Function    : 解析单元格
	 * LastUpdate  : 2010-9-21
	 * @param value
	 * @return
	 */
	public abstract BlankResult parseBlank(String value);
}
