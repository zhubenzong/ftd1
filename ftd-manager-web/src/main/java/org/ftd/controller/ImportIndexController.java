package org.ftd.controller;

import org.ftd.search.service.SearchItemService;
import org.ftd.utils.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImportIndexController {
	
	@Autowired
	SearchItemService searchItemService;
	
	@RequestMapping(value="/index/item/import")
	@ResponseBody
	public E3Result importIndex(){
		E3Result result = searchItemService.importAllItems();
		return result;
	}
}
