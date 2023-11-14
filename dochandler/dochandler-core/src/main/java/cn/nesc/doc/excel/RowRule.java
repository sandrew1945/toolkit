/**********************************************************************
* <pre>
* FILE : RowRule.java
* CLASS : RowRule
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
* 		  |2010-9-20| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: RowRule.java,v 1.2 2013/03/15 09:27:13 weibin Exp $
*/

package cn.nesc.doc.excel;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-9-20
 * @version    :
 */
public class RowRule
{
	private LinkedHashMap<Integer, BlankRule> bRuleList = new LinkedHashMap<Integer, BlankRule>();

	private Iterator<Integer> it;

	/**
	 * 
	 * Function    : 检测本行是否还有需要验证的单元格
	 * LastUpdate  : 2010-9-20
	 * @return
	 */
	public boolean hasNextRule()
	{
		if (null == it)
		{
			it = bRuleList.keySet().iterator();
		}
		return it.hasNext();
	}

	/**
	 * 
	 * Function    : 获取下一个单元格验证规则
	 * LastUpdate  : 2010-9-21
	 * @return
	 */
	public BlankRule next()
	{
		if (null == it)
		{
			it = bRuleList.keySet().iterator();
		}
		return bRuleList.get(it.next());
	}
	
	public Set<Entry<Integer, BlankRule>> getRule()
	{
		return bRuleList.entrySet();
	}

	/**
	 * 
	 * Function    : 添加一个单元格验证规则
	 * LastUpdate  : 2010-9-20
	 * @param bRule
	 */
	public void addBlankRule(int idx, BlankRule bRule)
	{
		//bRuleList.add(bRule);
		bRuleList.put(idx, bRule);
	}

	public void initializeRule()
	{
		if (null != it)
		{
			it = null;
		}
	}
	
	public static void main(String[] args)
	{
		LinkedHashMap<Integer, String> lm = new LinkedHashMap<Integer, String>();
		lm.put(1, "S1");
		lm.put(2, "S2");
		lm.put(3, "S3");
		lm.put(4, "S4");
		Set<Entry<Integer, String>> set = lm.entrySet();
		Iterator<Entry<Integer, String>> it = set.iterator();
		System.out.println(it.next().getKey());
		System.out.println(it.next().getValue());
		
	}
}
