package com.geekily.geekilyArchiveUser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;

@Mapper
public interface HomeMapper {
	public List<GeekilyMap> selectNewArticle();
	public List<GeekilyMap> selectRandomArchive();
}
