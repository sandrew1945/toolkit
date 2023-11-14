/**********************************************************************
* <pre>
* FILE : ExcelParser.java
* CLASS : ExcelParser
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
* $Id: ExcelParser.java,v 1.1 2013/03/15 08:32:58 jinxin Exp $
*/

package cn.nesc.doc.excel;

import java.io.IOException;
import java.io.InputStream;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-9-25
 * @version    :
 */
public interface ExcelParser
{
	/**
	 * 
	 * Function    : 解析EXCEL
	 * LastUpdate  : 2013-5-13
	 * @param fis
	 * @return
	 * @throws IOException
	 */
	public ExcelResult parse(InputStream fis) throws IOException;
}
