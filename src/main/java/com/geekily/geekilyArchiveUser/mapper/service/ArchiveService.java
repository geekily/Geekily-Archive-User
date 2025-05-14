package com.geekily.geekilyArchiveUser.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveUser.mapper.ArchiveMapper;


@Service
public class ArchiveService implements ArchiveMapper{
	
	@Autowired
	ArchiveMapper archiveMapper;

	@Override
	public String selectUserUid(GeekilyMap gMap) {
		return archiveMapper.selectUserUid(gMap);
	}

	@Override
	public String selectArchiveLogo(GeekilyMap gMap) {
		return archiveMapper.selectArchiveLogo(gMap);
	}

	@Override
	public String selectArchiveUrlPath(GeekilyMap gMap) {
		return archiveMapper.selectArchiveUrlPath(gMap);
	}

	@Override
	public String selectCategoryUid(GeekilyMap gMap) {
		return archiveMapper.selectCategoryUid(gMap);
	}
	
	@Override
	public Boolean checkArticleExistence(GeekilyMap gMap) {
		return archiveMapper.checkArticleExistence(gMap);
	}

	@Override
	public String selectCategoryName(GeekilyMap gMap) {
		return archiveMapper.selectCategoryName(gMap);
	}

	@Override
	public String selectArticleUid(GeekilyMap gMap) {
		return archiveMapper.selectArticleUid(gMap);
	}

	@Override
	public int selectArticleListCount(GeekilyMap gMap) {
		return archiveMapper.selectArticleListCount(gMap);
	}

	@Override
	public List<GeekilyMap> selectArticleList(GeekilyMap gMap) {
		return archiveMapper.selectArticleList(gMap);
	}

	@Override
	public GeekilyMap selectArticle(GeekilyMap gMap) {
		return archiveMapper.selectArticle(gMap);
	}
}
