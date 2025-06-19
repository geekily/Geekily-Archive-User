package com.geekive.geekiveArchiveUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;

@Mapper
public interface SignMapper {
	public Boolean checkEmailExistence(GeekiveMap gMap);
	public Boolean checkArchiveNameExistence(GeekiveMap gMap);
	public GeekiveMap selectUser(GeekiveMap gMap);
	public void insertUser(GeekiveMap gMap);
	public void insertCategory(GeekiveMap gMap);
}
