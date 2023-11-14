/**********************************************************************
* <pre>
* FILE : ErrorContainer.java
* CLASS : ErrorContainer
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
* $Id: ResultContainer.java,v 1.1 2013/03/15 08:32:58 jinxin Exp $
*/

package cn.nesc.doc.excel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-9-21
 * @version    :
 */
public class ResultContainer
{
	private LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> container;

	private ResultContainer()
	{
	}

	/**
	 * 
	 * Function    : 实例化方法
	 * LastUpdate  : 2010-9-21
	 * @return
	 */
	public static ResultContainer getInstance()
	{
		ResultContainer retContainer = new ResultContainer();
		retContainer.container = new LinkedHashMap<String, LinkedHashMap<Integer, List<String>>>();
		return retContainer;
	}

	public void add(String sheetName, Integer rowNum, String content)
	{
		LinkedHashMap<Integer, List<String>> ret = null;
		if (container.containsKey(sheetName))
		{
			ret = container.get(sheetName);
			if (ret.containsKey(rowNum))
			{
				ret.get(rowNum).add(content);
			}
			else
			{
				List<String> list = new ArrayList<String>();
				list.add(content);
				ret.put(rowNum, list);
			}
		}
		else
		{
			ret = new LinkedHashMap<Integer, List<String>>();
			List<String> list = new ArrayList<String>();
			list.add(content);
			ret.put(rowNum, list);
			container.put(sheetName, ret);
		}
	}

	public List<String> get(String sheetName, Integer rowNum)
	{
		return container.get(sheetName).get(rowNum);
	}

	public LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> getAll()
	{
		return container;
	}

	public void clear()
	{
		container.clear();
	}
}
