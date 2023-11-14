/**********************************************************************
* <pre>
* FILE : POI03ExcelParseImpl.java
* CLASS : POI03ExcelParseImpl
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
* 		  |2013-5-13| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: POI03ExcelParseImpl.java,v 0.1 2013-5-13 下午05:05:09 SuMMeR Exp $
*/

package cn.nesc.doc.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2013-5-13
 * @version    :
 */
public class POI03ExcelParseImpl implements ExcelParser
{
	private int titleRows = 0;

	private Integer totalCol = null;

	private RowRule rRule;

	private ExcelResult result = new ExcelResult();

	private boolean hasErr = false;

	private POI03ExcelParseImpl()
	{
	}

	/**
	 * 
	 * Function : 实例化方法 LastUpdate : 2010-9-21
	 * 
	 * @param rRule
	 * @return
	 */
	public static POI03ExcelParseImpl newInstance(RowRule rRule, int aTitleRows, Integer aTotalCol)
	{
		POI03ExcelParseImpl parser = new POI03ExcelParseImpl();
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
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			//Workbook.getWorkbook(fis);
			int sheetNum = wb.getNumberOfSheets();
			//遍例所有SHEET
			for (int curSheet = 0; curSheet < sheetNum; curSheet++)
			{
				HSSFSheet sheet = wb.getSheetAt(curSheet);
				String sheetName = sheet.getSheetName();
				int rowNum = sheet.getLastRowNum() + 1;
				List<String> rowData = null;
				//遍例所有行
				//判断是否设置了头所在行，如果没有，那么默认为0
				for (int curRow = titleRows; curRow < rowNum; curRow++)
				{
					//是否获验证规则标识
					boolean next = true;
					//遍例每一行所有单元格数据
					//判断是否设置了EXCEL列数，如果没有，那么默认为sheet.getRow(curRow)获取的Cell[]的大小
					int cellNums = sheet.getRow(curRow).getLastCellNum();
//					HSSFCell[] cells = sheet.getRow(curRow).getLastCellNum();
//					int colSize = cells.length;
					if (null != totalCol)
					{
						cellNums = totalCol.intValue();
					}
					rowData = new ArrayList<String>();
					//拼接全部数据，判断是否为空字符串
					StringBuilder sb = new StringBuilder("");
					for (int colNum = 0; colNum < cellNums; colNum++)
					{
						//保存在LIST中
						Cell curCell = sheet.getRow(curRow).getCell(colNum);
						String cellContain = null;
						if(null != curCell && curCell.getCellType() == CellType.NUMERIC)
						{
							if(DateUtil.isCellDateFormatted(curCell))
							{
								Date date = curCell.getDateCellValue();
								DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
								cellContain = formater.format(date);
							}
							else
							{
								curCell.setCellType(CellType.STRING);
								cellContain = "" + curCell.getStringCellValue();
							}
						}
						else if (null != curCell && curCell.getCellType() == CellType.STRING)
						{
							cellContain = curCell.getStringCellValue();
						}
						else if(null != curCell && curCell.getCellType() == CellType.BLANK)
						{
							cellContain = "";
						}
						rowData.add(cellContain);
						sb.append(cellContain);
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
		catch (Exception e)
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
