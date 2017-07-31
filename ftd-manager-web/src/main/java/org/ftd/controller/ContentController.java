package org.ftd.controller;

import java.util.List;

import org.ftd.common.pojo.EasyUIDataGridResult;
import org.ftd.content.service.ContentService;
import org.ftd.pojo.TbContent;
import org.ftd.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
	@Autowired
	ContentService contentService;

	/*
	 * 根据内容分类id查询内容
	 */
	@RequestMapping(value = "/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentListByCategoryId(Long categoryId) {
		List<TbContent> list = contentService.getContentListByCategoryId(categoryId);
		// 封裝EasyUIDataGridResult
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(list.size());
		result.setRows(list);
		return result;
	}

	/*
	 * 新增内容
	 */
	@RequestMapping(value = "/content/save")
	@ResponseBody
	public E3Result addContent(TbContent tbContent) {
		E3Result result = contentService.addContent(tbContent);
		return result;
	}

	/*
	 * 删除内容
	 */
	@RequestMapping(value = "/content/delete")
	@ResponseBody
	public E3Result deleteContent(String ids) {
		E3Result result = contentService.deleteContent(ids);
		return result;
	}

	/*
	 * 编辑内容
	 */
	@RequestMapping(value = "/content/edit")
	@ResponseBody
	public E3Result editContent(TbContent tbContent) {
		E3Result result = contentService.updateContent(tbContent);
		return result;
	}
}
