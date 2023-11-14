/**********************************************************************
 * <pre> FILE : ExcelParser.java CLASS : ExcelParser AUTHOR : SuMMeR FUNCTION : TODO
 * ====================================================================== CHANGE HISTORY LOG
 * ---------------------------------------------------------------------- MOD. NO.| DATE | NAME |
 * REASON | CHANGE REQ. ----------------------------------------------------------------------
 * |2010-9-17| SuMMeR| Created | DESCRIPTION: </pre>
 ***********************************************************************/
/**
 * $Id: JXLExcelParserImpl.java,v 1.6 2013/03/18 03:58:23 weibin Exp $
 */

package cn.nesc.doc.excel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Function : Excel解析工具
 * 
 * @author : SuMMeR CreateDate : 2010-9-17
 * @version :
 */
public class JXLExcelParserImpl implements ExcelParser
{
	private int titleRows = 0;

	private Integer totalCol = null;

	private RowRule rRule;

	private ExcelResult result = new ExcelResult();

	private boolean hasErr = false;

	private JXLExcelParserImpl()
	{
	}

	/**
	 * 
	 * Function : 实例化方法 LastUpdate : 2010-9-21
	 * 
	 * @param rRule
	 * @return
	 */
	public static JXLExcelParserImpl newInstance(RowRule rRule, int aTitleRows, Integer aTotalCol)
	{
		JXLExcelParserImpl parser = new JXLExcelParserImpl();
		parser.rRule = rRule;
		parser.titleRows = aTitleRows;
		parser.totalCol = aTotalCol;
		return parser;
	}

	/*
	 * (non-Javadoc)
	 * @see com.autosys.bkm.util.excel.ExcelParser#parse(java.io.FileInputStream)
	 */
	public ExcelResult parse(InputStream fis)
	{
		try
		{
			//读取EXCEL数据
			Workbook wb = Workbook.getWorkbook(fis);
			int sheetNum = wb.getNumberOfSheets();
			//遍例所有SHEET
			for (int curSheet = 0; curSheet < sheetNum; curSheet++)
			{
				Sheet sheet = wb.getSheet(curSheet);
				String sheetName = sheet.getName();
				int rowNum = sheet.getRows();
				List<String> rowData = null;
				//遍例所有行
				//判断是否设置了头所在行，如果没有，那么默认为0
				for (int curRow = titleRows; curRow < rowNum; curRow++)
				{
					//是否获验证规则标识
					boolean next = true;
					//遍例每一行所有单元格数据
					//判断是否设置了EXCEL列数，如果没有，那么默认为sheet.getRow(curRow)获取的Cell[]的大小
					Cell[] cells = sheet.getRow(curRow);
					int colSize = cells.length;
					if (null != totalCol)
					{
						colSize = totalCol.intValue();
					}
					rowData = new ArrayList<String>();
					//拼接全部数据，判断是否为空字符串
					StringBuilder sb = new StringBuilder("");
					for (int colNum = 0; colNum < colSize; colNum++)
					{
						//保存在LIST中
						rowData.add(cells[colNum].getContents());
						sb.append(cells[colNum].getContents());
					}
					//如果是空,则说明EXCEL已经没有数据,不再处理
					if ("".equals(sb.toString()))
					{
						break;
					}
					Iterator<Entry<Integer, BlankRule>> it = null;
					int valColNum = 0;
					BlankRule bRule = null;
					if (null != rRule)
					{
						//						entry = getNextValidate();
						//						it = initValidate();
						Set<Entry<Integer, BlankRule>> set = rRule.getRule();
						// 遍历单元格，验证是否有错误
						//						int colNum = 0;
						it = set.iterator();
					}
					for (int i = 0; i < rowData.size(); i++)
					{

						//判断验证规则是否为空
						if (null == rRule)
						{
							//将数据保存到result里
							loadExcelData(sheetName, curRow, rowData.get(i));
						}
						else
						{

							if (next)
							{
								//判断该列是否有验证规则
								if (it.hasNext())
								{
									Entry<Integer, BlankRule> entry = it.next();
									valColNum = entry.getKey();
									bRule = entry.getValue();
								}
							}
							if (i + 1 == valColNum)
							{
								//验证该单元格数据是否满足要求,如果验证通过将数据保存到result里,否则将验证通过标识变为false，并将错误信息保存在error里
								doValidate(sheetName, curRow, i, bRule, rowData.get(i));
								next = true;
							}
							else
							{
								//将数据保存到result里
								loadExcelData(sheetName, curRow, rowData.get(i));
								next = false;
							}
						}
					}
				}
			}
		}
		catch (BiffException e)
		{
			e.printStackTrace();
			throw new RuntimeException("EXCEL无法识别,请联系管理员");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("读取EXCEL出错,请联系管理员");
		}
		return result;
	}



	/**
	 * 
	 * Function    : 验证EXCEL数据
	 * LastUpdate  : 2013-5-10
	 * @param sheetName
	 * @param curRow
	 * @param colNum
	 * @param bRule
	 * @param cellVal
	 */
	private void doValidate(String sheetName, int curRow, int colNum, BlankRule bRule, String cellVal)
	{
		BlankResult bResult = bRule.parseBlank(cellVal);
		if (bResult.isValidate() && !hasErr)
		{
			loadExcelData(sheetName, curRow, cellVal);
		}
		else
		{
			hasErr = true;
			if (result.isValidate())
			{
				result.clear();
			}
			result.setValidate(!hasErr);
			if (null != bResult.getErrMsg() && !"".equals(bResult.getErrMsg()))
			{
				result.addError(sheetName, new Integer(curRow + 1), ParserUtil.spellErrMsg(colNum + 1, bResult.getErrMsg()));
			}
		}
	}

	/**
	 * 
	 * Function    : 将EXCEL数据保存到结果集中到
	 * LastUpdate  : 2013-5-10
	 * @param sheetName
	 * @param curRow
	 * @param cellVal
	 */
	private void loadExcelData(String sheetName, int curRow, String cellVal)
	{
		result.setValidate(!hasErr);
		if (!hasErr)
		{
			result.addResult(sheetName, new Integer(curRow + 1), cellVal);
		}
	}
}
