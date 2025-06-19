package com.geekive.geekiveArchiveUser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;

@Mapper
public interface HomeMapper {
	public List<GeekiveMap> selectNewArticle();
	public List<GeekiveMap> selectRandomArchive();
}
