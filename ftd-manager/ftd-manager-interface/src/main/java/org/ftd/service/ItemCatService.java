package org.ftd.service;

import java.util.List;

import org.ftd.common.pojo.EasyUITreeNode;

public interface ItemCatService {
	/*
	 * 查询商品分类的列表
	 */
	List<EasyUITreeNode> selectItemCatByList(long parentId);

}
