package org.ftd.controller;

import java.util.List;

import org.ftd.common.pojo.EasyUITreeNode;
import org.ftd.pojo.TbItemCat;
import org.ftd.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping(value="/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id",defaultValue="0")Long parentId){
		List<EasyUITreeNode> list = itemCatService.selectItemCatByList(parentId);
		return list;
	}
}
