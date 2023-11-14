/**********************************************************************
* <pre>
* FILE : ExcelResult.java
* CLASS : ExcelResult
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
* $Id: ExcelResult.java,v 1.1 2013/03/15 08:32:58 jinxin Exp $
*/

package cn.nesc.doc.excel;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-9-21
 * @version    :
 */
public class ExcelResult
{
	private boolean isValidate;

	private Container errContainer = Container.getInstance();

	private Container resContainer = Container.getInstance();

	public boolean isValidate()
	{
		return this.isValidate;
	}

	public void setValidate(boolean isValidate)
	{
		this.isValidate = isValidate;
	}

	/**
	 * 
	 * Function    : 添加错误
	 * LastUpdate  : 2012-5-11
	 * @param rowNum
	 * @param msg
	 */
	public void addError(String sheetName, Integer rowNum, String msg)
	{
		errContainer.add(sheetName, rowNum, msg);
	}

	/**
	 * 
	 * Function    : 添加EXCEL数据
	 * LastUpdate  : 2012-5-11
	 * @param sheetNum
	 * @param rowNum
	 * @param content
	 */
	public void addResult(String sheetName, Integer rowNum, String content)
	{
		resContainer.add(sheetName, rowNum, content);
	}

	/**
	 * 
	 * Function    : 获取错误信息
	 * LastUpdate  : 2012-5-11
	 * @param rowNum
	 * @return
	 */
	public List<String> getError(String sheetName, Integer rowNum)
	{
		return errContainer.get(sheetName, rowNum);
	}

	/**
	 * 
	 * Function    : 获取EXCEL数据(如果验证通过返回EXCEL内容，严整不通过返回错误信息)
	 * LastUpdate  : 2012-5-11
	 * @param sheetNum
	 * @param rowNum
	 * @return
	 */
	public Container getResult()
	{
		if (isValidate)
		{
			return resContainer;
		}
		else
		{
			return errContainer;
		}
	}

	/**
	 * 
	 * Function    : 获取全部错误信息
	 * LastUpdate  : 2012-5-11
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> getAllError()
	{
		return errContainer.getAll();
	}

	/**
	 * 
	 * Function    : 获取全部EXCEL数据
	 * LastUpdate  : 2012-5-11
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashMap<Integer, List<String>>> getAllResult()
	{
		return resContainer.getAll();
	}

	/**
	 * 
	 * Function    : 清空
	 * LastUpdate  : 2013-5-9
	 */
	public void clear()
	{
		errContainer.clear();
		resContainer.clear();
	}
}
