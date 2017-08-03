package org.ftd.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.ftd.common.pojo.SearchResult;
import org.ftd.search.dao.SearchDao;
import org.ftd.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	SearchDao searchDao;
	
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		//封装query对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(keyword);
		//设置分页条件
		if(page<=1)	page=1;
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//设置默认搜索域
		query.set("df","item_title");
		//开启高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		//调用dao执行查询
		SearchResult result = searchDao.search(query);
		//补全总页数属性
		long recordCount = result.getRecordCount();
		int totalPage = (int)Math.ceil(recordCount/rows);
		result.setTotalPages(totalPage);
		return result;
	}

}
