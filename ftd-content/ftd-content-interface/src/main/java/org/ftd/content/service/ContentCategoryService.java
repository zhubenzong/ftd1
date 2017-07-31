package org.ftd.content.service;

import java.util.List;

import org.ftd.common.pojo.EasyUITreeNode;
import org.ftd.utils.E3Result;

public interface ContentCategoryService {
	/*
	 * 根据父节点查询下属所有分类
	 */
	public List<EasyUITreeNode> getContentCategoryListByParentId(long parentId);
	
	/*
	 * 新增节点
	 */
	public E3Result addContentCategory(long parentId,String name);
	
	/*
	 * 编辑节点
	 */
	public E3Result updateContentCategory(long id,String name);
	
	/*
	 * 删除节点
	 */
	public E3Result deleteContentCategory(long id);
}
