package cn.nesc.toolkit.es.common;

import org.elasticsearch.search.SearchHit;

import java.sql.SQLException;

public interface ESCallback<T>
{
	public T wrapper(SearchHit searchHit) throws SQLException;
}
