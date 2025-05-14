package com.geekily.geekilyArchiveUser.mapper.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveUser.mapper.CommonMapper;

@Service
public class CommonService implements CommonMapper{
	
	@Autowired
	CommonMapper commonMapper;

	@Override
	public List<GeekilyMap> selectSocialMedia(GeekilyMap gMap) {
		return commonMapper.selectSocialMedia(gMap);
	}

	@Override
	public List<GeekilyMap> selectAllCategory(GeekilyMap gMap) {
	    List<GeekilyMap> categoryAllList = commonMapper.selectAllCategory(gMap);

	    Map<Integer, List<GeekilyMap>> categoryMapByDepth = categoryAllList.stream()
	            .collect(Collectors.groupingBy(map -> map.getInt("depth")));

	    List<GeekilyMap> categoryDepth1List = categoryMapByDepth.getOrDefault(1, Collections.emptyList());
	    List<GeekilyMap> categoryDepth2List = categoryMapByDepth.getOrDefault(2, Collections.emptyList());

	    Map<String, List<GeekilyMap>> childMap = categoryDepth2List.stream()
	            .collect(Collectors.groupingBy(map -> map.getString("parentCategoryUid")));

	    categoryDepth1List.forEach(depth1Map ->
	            depth1Map.put("childList", childMap.getOrDefault(depth1Map.getString("categoryUid"), Collections.emptyList()))
	    );

	    return categoryDepth1List;
	}
}
