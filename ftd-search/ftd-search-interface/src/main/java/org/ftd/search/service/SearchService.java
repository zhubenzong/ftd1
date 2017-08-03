package org.ftd.search.service;

import org.ftd.common.pojo.SearchResult;

public interface SearchService {
	/*
	 * 从索引库查询
	 */
	SearchResult search(String keyword,int page,int rows)throws Exception;
}
