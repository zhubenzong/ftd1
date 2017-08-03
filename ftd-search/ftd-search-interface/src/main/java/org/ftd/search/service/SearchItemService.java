package org.ftd.search.service;

import org.ftd.utils.E3Result;

public interface SearchItemService {
	/*
	 * 从数据库查询数据写入搜索索引库
	 */
	E3Result importAllItems();
}
