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
* $Id: ErrorContainer.java,v 1.1 2013/03/15 08:32:58 jinxin Exp $
*/

package cn.nesc.doc.excel;

import java.util.LinkedHashMap;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-9-21
 * @version    :
 */
public class ErrorContainer
{
	//private LinkedHashMap<Integer, List<String>> container;
	private LinkedHashMap<String, LinkedHashMap<Integer, String>> container;

	private ErrorContainer()
	{
	}

	/**
	 * 
	 * Function    : 实例化方法
	 * LastUpdate  : 2010-9-21
	 * @return
	 */
	public static ErrorContainer getInstance()
	{
		ErrorContainer retContainer = new ErrorContainer();
		retContainer.container = new LinkedHashMap<String, LinkedHashMap<Integer, String>>();
		return retContainer;
	}

	/**
	 * 
	 * Function    : 存入错误信息
	 * LastUpdate  : 2013-5-9
	 * @param sheetName
	 * @param rowNum
	 * @param errMsg
	 */
	public void add(String sheetName, Integer rowNum, String errMsg)
	{
		LinkedHashMap<Integer, String> sheetError = null;
		// 判断SHEET是否存在
		if (container.containsKey(sheetName))
		{
			// 如果存在，那么取出，追加错误信息
			sheetError = container.get(sheetName);
			sheetError.put(rowNum, errMsg);
		}
		else
		{
			// 如果不存在，那么新增
			sheetError = new LinkedHashMap<Integer, String>();
			sheetError.put(rowNum, errMsg);
		}
		container.put(sheetName, sheetError);
	}

	/**
	 * 
	 * Function    : 获取错误信息
	 * LastUpdate  : 2013-5-9
	 * @param sheetName
	 * @param rowNum
	 * @return
	 */
	public String get(String sheetName, Integer rowNum)
	{
		return container.get(sheetName).get(rowNum);
	}

	/**
	 * 
	 * Function    : 获取全部错误信息
	 * LastUpdate  : 2013-5-9
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashMap<Integer, String>> getAll()
	{
		return container;
	}

	/**
	 * 
	 * Function    : 清空所有错误信息
	 * LastUpdate  : 2013-5-9
	 */
	public void clear()
	{
		container.clear();
	}
}
