package com.geekive.geekiveArchiveUser.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.geekive.geekiveArchiveUser.common.Constants;
import com.geekive.geekiveArchiveUser.common.Util;
import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveUser.geekiveCustom.GeekivePaginator;
import com.geekive.geekiveArchiveUser.mapper.service.ArchiveService;

@Controller
public class ArchiveController {
	
	@Resource
	ArchiveService archiveService;
	
	@GetMapping(value = {"/{archiveName:^(?!.*\\..+).+}", "/{archiveName:^(?!.*\\..+).+}/{categoryName:^(?!.*\\..+).+}"})
	public String archive(HttpServletRequest request, ModelMap modelMap) throws Exception{
		int articleTotalCount		 		= 0;
		String categoryName					= null;
		String currentArchiveUserUid 		= null;
		String currentCategoryUid 	= null;
		try {
			currentArchiveUserUid 			= request.getAttribute("currentArchiveUserUid").toString();
			currentCategoryUid 				= request.getAttribute("currentCategoryUid").toString();

			GeekiveMap gMap = new GeekiveMap();
			gMap.put("currentArchiveUserUid"	, currentArchiveUserUid);	
			gMap.put("currentCategoryUid"		, currentCategoryUid);
			gMap.put("searchValue"				, request.getParameter("searchValue"));
			
			if(Util.isEmpty(gMap.getString("currentArchiveUserUid"))) {
				return "redirect:/";
			}
			
			articleTotalCount 	= archiveService.selectArticleListCount(gMap);
			categoryName		= archiveService.selectCategoryName(gMap);
		} catch (Exception e) {
			e.getStackTrace();
		}
		modelMap.addAttribute("articleTotalCount"			, articleTotalCount);
		modelMap.addAttribute("categoryName"				, categoryName);
		modelMap.addAttribute("currentArchiveUserUid"		, currentArchiveUserUid);
		modelMap.addAttribute("currentCategoryUid"			, currentCategoryUid);
		return "archive/wrapper";
	}
	
	@GetMapping(value = "/{archiveName}/{categoryName}/{articleIndex:[0-9]+}")
	public String archiveView(HttpServletRequest request, ModelMap modelMap) throws Exception {
		GeekiveMap articleMap = null;
		String currentArchiveUserUid	= request.getAttribute("currentArchiveUserUid").toString();
		String currentCategoryUid 		= request.getAttribute("currentCategoryUid").toString();
		String currentArticleNumber 	= request.getAttribute("currentArticleNumber").toString();
		try {
			GeekiveMap gMap = new GeekiveMap();
			gMap.put("currentArchiveUserUid"	, currentArchiveUserUid);	
			gMap.put("currentCategoryUid"		, currentCategoryUid);
			gMap.put("currentArticleNumber"		, currentArticleNumber);
			
			if(Util.isEmpty(gMap.getString("currentArchiveUserUid"))) {
				return "redirect:/";
			}
			
			articleMap = archiveService.selectArticle(gMap);
		} catch (Exception e) {
			e.getStackTrace();
		}
		modelMap.addAttribute("articleMap", articleMap);
		return "archive/view";
	}
	
	@PostMapping(value = "/list")
	public String archiveList(HttpServletRequest request, ModelMap modelMap) throws Exception {
		List<GeekiveMap> articleList 	= null;
		int articleTotalCount		 	= 0;
		try {
			GeekiveMap gMap = new GeekiveMap();
			gMap.put("currentArchiveUserUid"	, request.getParameter("currentArchiveUserUid"));	
			gMap.put("currentCategoryUid"		, request.getParameter("currentCategoryUid"));
			gMap.put("searchValue"				, request.getParameter("searchValue"));
			
			int currentPageNumber = GeekivePaginator.parseCurrentPageNumber(request.getParameter("currentPageNumber"));			
			gMap.put("dataCountPerPage"	, Constants.dataCountPerPage);
			gMap.put("dataPagingIndex"	, (currentPageNumber * Constants.dataCountPerPage) - Constants.dataCountPerPage);
			
			articleTotalCount 	= archiveService.selectArticleListCount(gMap);
			articleList 		= archiveService.selectArticleList(gMap);
			
			modelMap.addAttribute("articleList"		, articleList);
			modelMap.addAttribute("isLastArticle"	, (currentPageNumber * Constants.dataCountPerPage) >= articleTotalCount);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "archive/list";
	}
}
