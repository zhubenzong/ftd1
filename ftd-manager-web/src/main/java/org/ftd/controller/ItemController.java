package org.ftd.controller;

import org.ftd.common.pojo.EasyUIDataGridResult;
import org.ftd.pojo.TbItem;
import org.ftd.service.ItemService;
import org.ftd.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value="/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	/*
	 * 列表查询所有商品
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result = itemService.findItemByList(page,rows);
		return result;
	}
	
	/*
	 * 新增商品
	 */
	@RequestMapping(value="/item/save")
	@ResponseBody
	public E3Result addItem(TbItem item,String desc){
		E3Result result = itemService.addItem(item,desc);
		return result;
	}
	
	/*
	 * 删除商品
	 */
	@RequestMapping(value="/item/delete")
	@ResponseBody
	public E3Result deleteItem(String ids){
		E3Result result = itemService.deleteItem(ids);
		return result;
	}
	/*
	 * 编辑商品
	 */
	@RequestMapping(value="/item/update")
	@ResponseBody
	public E3Result updateItem(TbItem item){
		E3Result result = itemService.updateItem(item);
		return result;
	}
	
	/*
	 * 商品上架
	 */
	@RequestMapping(value="/item/reshelf")
	@ResponseBody
	public E3Result itemReshelf(String ids){
		E3Result result = itemService.itemReshelf(ids);
		return result;
	}
	
	/*
	 * 商品下架
	 */
	@RequestMapping(value="/item/instock")
	@ResponseBody
	public E3Result itemInstock(String ids){
		E3Result result = itemService.itemInstock(ids);
		return result;
	}
}
