package com.geekily.geekilyArchiveUser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;

@Mapper
public interface ArchiveMapper {
	public String selectUserUid(GeekilyMap gMap);
	public String selectArchiveLogo(GeekilyMap gMap);
	public String selectArchiveUrlPath(GeekilyMap gMap);
	public String selectCategoryUid(GeekilyMap gMap);
	public Boolean checkArticleExistence(GeekilyMap gMap);
	public String selectCategoryName(GeekilyMap gMap);
	public String selectArticleUid(GeekilyMap gMap);
	public int selectArticleListCount(GeekilyMap gMap);
	public List<GeekilyMap> selectArticleList(GeekilyMap gMap);
	public GeekilyMap selectArticle(GeekilyMap gMap);
}
