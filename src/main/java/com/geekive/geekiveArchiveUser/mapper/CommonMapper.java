package com.geekive.geekiveArchiveUser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;

@Mapper
public interface CommonMapper {
	public List<GeekiveMap> selectSocialMedia(GeekiveMap gMap);
	public List<GeekiveMap> selectAllCategory(GeekiveMap gMap);
}
