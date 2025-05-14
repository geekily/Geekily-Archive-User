package com.geekily.geekilyArchiveUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;

@Mapper
public interface SignMapper {
	public Boolean checkEmailExistence(GeekilyMap gMap);
	public Boolean checkArchiveNameExistence(GeekilyMap gMap);
	public GeekilyMap selectUser(GeekilyMap gMap);
	public void insertUser(GeekilyMap gMap);
	public void insertCategory(GeekilyMap gMap);
}
