/***************************************************************************************************
 * <pre>
* FILE : ESPageResult.java
* CLASS : Session
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
* 		  |2010-4-16| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/
/**
 * 
 */
package cn.nesc.toolkit.es.common;

import java.util.List;

/**
 * 
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-5-21
 * @version    :
 * @param <T>
 */
@SuppressWarnings("serial")
public class ESPageResult<T> implements DataBean
{
	/**
	 * @uml.property name="curPage"
	 */
	private int curPage = 1;

	/**
	 * @uml.property name="pageSize"
	 */
	private int pageSize = 10;

	/**
	 * @uml.property name="totalPages"
	 */
	private long totalPages = 0;

	/**
	 * @uml.property name="totalRecords"
	 */
	private long totalRecords = 0;

	/**
	 * @uml.property name="records"
	 */
	private List<T> records = null;

	/**
	 * @return
	 * @uml.property name="curPage"
	 */
	public int getCurPage()
	{
		return curPage;
	}

	/**
	 * @param curPage
	 * @uml.property name="curPage"
	 */
	public void setCurPage(int curPage)
	{
		this.curPage = curPage;
	}

	/**
	 * @return
	 * @uml.property name="pageSize"
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize
	 * @uml.property name="pageSize"
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return
	 * @uml.property name="totalPages"
	 */
	public long getTotalPages()
	{
		return totalPages;
	}

	/**
	 * @param totalPages
	 * @uml.property name="totalPages"
	 */
	public void setTotalPages(long totalPages)
	{
		this.totalPages = totalPages;
	}

	/**
	 * @return
	 * @uml.property name="totalRecords"
	 */
	public long getTotalRecords()
	{
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 * @uml.property name="totalRecords"
	 */
	public void setTotalRecords(long totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	/**
	 * @return
	 * @uml.property name="records"
	 */
	public List<T> getRecords()
	{
		return records;
	}

	/**
	 * @param records
	 * @uml.property name="records"
	 */
	public void setRecords(List<T> records)
	{
		this.records = records;
	}

	public String toString()
	{
		StringBuilder sbd = new StringBuilder();
		sbd.append("ESPageResult@");
		sbd.append(this.hashCode());
		sbd.append("[PSize=" + this.pageSize + "; ");
		sbd.append("TPSize=" + this.totalPages + "; ");
		sbd.append("TRSize=" + this.totalRecords + "; ");
		sbd.append("CPage=" + this.curPage + "; ");
		if (this.records.size() > 0)
		{
			sbd.append("Class=" + this.records.get(0).getClass());
		}
		sbd.append("]");
		return sbd.toString();
	}
}
