package com.geekily.geekilyArchiveUser.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveUser.mapper.LogMapper;


@Service
public class LogService implements LogMapper{
	
	@Autowired
	LogMapper logMapper;

	@Override
	public void insertLogArchiveVisit(GeekilyMap gMap) {
		logMapper.insertLogArchiveVisit(gMap);
	}

	@Override
	public void insertLogArticleView(GeekilyMap gMap) {
		logMapper.insertLogArticleView(gMap);
	}
}
