/**********************************************************************
* <pre>
* FILE : Container.java
* CLASS : Container
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
* 		  |2013-5-9| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: Container.java,v 0.1 2013-5-9 下午04:06:47 SuMMeR Exp $
*/

package cn.nesc.doc.excel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2013-5-9
 * @version    :
 */
public class Container
{
	private LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> container;

	private Container()
	{
	}

	/**
	 * 
	 * Function    : 实例化方法
	 * LastUpdate  : 2010-9-21
	 * @return
	 */
	public static Container getInstance()
	{
		Container container = new Container();
		container.container = new LinkedHashMap<String, LinkedHashMap<Integer, List<String>>>();
		return container;
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

	public LinkedHashMap<Integer, List<String>> getSheet(String sheetName)
	{
		return container.get(sheetName);
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
