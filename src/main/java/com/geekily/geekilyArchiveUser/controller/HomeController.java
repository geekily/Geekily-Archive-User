package com.geekily.geekilyArchiveUser.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.geekily.geekilyArchiveUser.common.Util;
import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveUser.mapper.service.HomeService;

@Controller
public class HomeController {
	
	@Resource
	HomeService homeService;

	@GetMapping(value = {"", "/index", "/home"})
	public String index(ModelMap modelMap) throws Exception{
		List<GeekilyMap> newArticleList 	= null;
		List<GeekilyMap> randomArchiveList 	= null;
		
		try {
			newArticleList 		= homeService.selectNewArticle();
			randomArchiveList 	= homeService.selectRandomArchive();
		} catch (Exception e) {
			e.getStackTrace();
		}
		modelMap.addAttribute("newArticleList"		, newArticleList);
		modelMap.addAttribute("randomArchiveList"	, randomArchiveList);
		return "home/home";
	}
	
	@GetMapping(value = "/admin")
	public String admin() throws Exception{
		return "redirect:" + Util.getAdminUrl();
	}
}
