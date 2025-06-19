package com.geekive.geekiveArchiveUser.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveUser.mapper.LogMapper;


@Service
public class LogService implements LogMapper{
	
	@Autowired
	LogMapper logMapper;

	@Override
	public void insertLogArchiveVisit(GeekiveMap gMap) {
		logMapper.insertLogArchiveVisit(gMap);
	}

	@Override
	public void insertLogArticleView(GeekiveMap gMap) {
		logMapper.insertLogArticleView(gMap);
	}
}
