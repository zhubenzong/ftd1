package org.ftd.content.service;

import java.util.List;

import org.ftd.pojo.TbContent;
import org.ftd.utils.E3Result;

public interface ContentService {
	/*
	 * 根据内容分类ID查询内容列表
	 */
	public List<TbContent> getContentListByCategoryId(long categoryId);
	
	/*
	 * 新增内容
	 */
	public E3Result addContent(TbContent tbContent);
	
	/*
	 * 删除内容
	 */
	public E3Result deleteContent(String ids);
	
	/*
	 * 编辑内容
	 */
	public E3Result updateContent(TbContent tbContent);
}
