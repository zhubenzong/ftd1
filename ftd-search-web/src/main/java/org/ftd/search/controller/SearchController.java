package org.ftd.search.controller;

import org.ftd.common.pojo.SearchResult;
import org.ftd.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	@Autowired
	SearchService searchService;
	@Value("${PAGE_ROWS}")
	Integer PAGE_ROWS;
	
	@RequestMapping(value="/search")
	public String search(String keyword,@RequestParam(defaultValue="1")Integer page,Model model) throws Exception{
		//解决乱码
		keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
		SearchResult result = searchService.search(keyword, page, PAGE_ROWS);
		//把结果传给jsp页面
		model.addAttribute("query",keyword);
		model.addAttribute("totalPages", result.getTotalPages());
		model.addAttribute("recourdCount",result.getRecordCount());
		model.addAttribute("page",page);
		model.addAttribute("itemList",result.getItemList());
		//返回逻辑视图
		return "search";
	}
	
}
