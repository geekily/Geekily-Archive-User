package com.geekily.geekilyArchiveUser.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveUser.mapper.ArchiveMapper;
import com.geekily.geekilyArchiveUser.mapper.HomeMapper;


@Service
public class HomeService implements HomeMapper{
	
	@Autowired
	HomeMapper homeMapper;

	@Override
	public List<GeekilyMap> selectNewArticle() {
		return homeMapper.selectNewArticle();
	}

	@Override
	public List<GeekilyMap> selectRandomArchive() {
		return homeMapper.selectRandomArchive();
	}
}
