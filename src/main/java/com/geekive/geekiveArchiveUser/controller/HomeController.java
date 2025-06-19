package com.geekive.geekiveArchiveUser.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.geekive.geekiveArchiveUser.common.Util;
import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveUser.mapper.service.HomeService;

@Controller
public class HomeController {
	
	@Resource
	HomeService homeService;

	@GetMapping(value = {"", "/index", "/home"})
	public String index(ModelMap modelMap) throws Exception{
		List<GeekiveMap> newArticleList 	= null;
		List<GeekiveMap> randomArchiveList 	= null;
		
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
