package com.geekive.geekiveArchiveUser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;

@Mapper
public interface LogMapper {
	public void insertLogArchiveVisit(GeekiveMap gMap);
	public void insertLogArticleView(GeekiveMap gMap);
}
