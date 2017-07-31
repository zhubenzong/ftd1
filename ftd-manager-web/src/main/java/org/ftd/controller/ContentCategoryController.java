package org.ftd.controller;

import java.util.List;

import org.ftd.common.pojo.EasyUITreeNode;
import org.ftd.content.service.ContentCategoryService;
import org.ftd.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/content/category")
public class ContentCategoryController {
	@Autowired
	ContentCategoryService contentCategoryService;
	
	/*
	 * 查询内容分类列表
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryListByParentId(parentId);
		return list;
	}
	
	/*
	 * 新增内容分类节点
	 */
	@RequestMapping(value="/create")
	@ResponseBody
	public E3Result createContentCategory(Long parentId,String name){
		E3Result result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
	
	/*
	 * 编辑节点
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public E3Result updateContentCategory(Long id,String name){
		E3Result result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
	
	/*
	 * 删除节点
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public E3Result deleteContentCategory(Long id){
		E3Result result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
}
