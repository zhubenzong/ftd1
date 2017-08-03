package org.ftd.item.controller;

import org.ftd.pojo.TbItem;
import org.ftd.pojo.TbItemDesc;
import org.ftd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value="/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId,Model model){
		TbItem tbItem = itemService.getItemById(itemId);
		TbItemDesc tbItemDesc = itemService.getItemDescById(itemId);
		model.addAttribute("item", tbItem);
		model.addAttribute("itemDesc", tbItemDesc);
		return "item";
	}
}
