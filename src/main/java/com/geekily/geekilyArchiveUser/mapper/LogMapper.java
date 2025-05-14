package com.geekily.geekilyArchiveUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;

@Mapper
public interface LogMapper {
	public void insertLogArchiveVisit(GeekilyMap gMap);
	public void insertLogArticleView(GeekilyMap gMap);
}
