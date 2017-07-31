package org.ftd.portal.controller;

import java.util.List;

import org.ftd.content.service.ContentService;
import org.ftd.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@Value("${CONTENT_LUNBO_ID}")
	private Long CONTENT_LUNBO_ID;
	
	@Autowired
	ContentService contentService;
	
	@RequestMapping(value="/index")
	public String showIndex(Model model){
		List<TbContent> list = contentService.getContentListByCategoryId(CONTENT_LUNBO_ID);
		model.addAttribute("ad1List", list);
		return "index";
	}
	
}
