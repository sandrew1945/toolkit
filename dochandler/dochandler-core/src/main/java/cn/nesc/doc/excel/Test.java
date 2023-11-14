/**********************************************************************
* <pre>
* FILE : Test.java
* CLASS : Test
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
* 		  |2014年5月28日| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: Test.java,v 0.1 2014年5月28日 下午3:39:45 SuMMeR Exp $
*/

package cn.nesc.doc.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2014年5月28日
 * @version    :
 */
public class Test
{

	/**
	 * Function    : 
	 * LastUpdate  : 2014年5月28日
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		ExcelParser parser = POI07ExcelParseImpl.newInstance(null, 0, null);
		InputStream is = new FileInputStream(new File("/Users/summer/Desktop/兼任信息表.xlsx"));
		ExcelResult res = parser.parse(is);
		LinkedHashMap<Integer, List<String>> lm = res.getResult().getSheet("Sheet1");
		List<String> values = lm.get(3);
        for (String value : values)
        {
            System.out.println("value:" + value);
        }
	}

}
