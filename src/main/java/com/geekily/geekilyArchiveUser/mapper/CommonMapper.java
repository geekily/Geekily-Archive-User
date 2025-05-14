package com.geekily.geekilyArchiveUser.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;

@Mapper
public interface CommonMapper {
	public List<GeekilyMap> selectSocialMedia(GeekilyMap gMap);
	public List<GeekilyMap> selectAllCategory(GeekilyMap gMap);
}
