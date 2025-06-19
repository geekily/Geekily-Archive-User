package com.geekive.geekiveArchiveUser.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveUser.common.CryptoUtil;
import com.geekive.geekiveArchiveUser.common.Util;
import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveConnector;
import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveUser.mapper.SignMapper;


@Service
public class SignService implements SignMapper{
	
	@Autowired
    private CryptoUtil cryptoUtil;
	
	@Autowired
	SignMapper signMapper;

	@Override
	public Boolean checkEmailExistence(GeekiveMap gMap) {
		Boolean result = false;
		try {
			gMap.put("email", cryptoUtil.encryptData(gMap.getString("email")));
			result = signMapper.checkEmailExistence(gMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean checkArchiveNameExistence(GeekiveMap gMap) {
		return signMapper.checkArchiveNameExistence(gMap);
	}

	@Override
	public GeekiveMap selectUser(GeekiveMap gMap) {
		GeekiveMap userMap = null;
		try {
			gMap.put("email", cryptoUtil.encryptData(gMap.getString("email")));
		    userMap = signMapper.selectUser(gMap);
		    if(Util.isEmpty(userMap) || !cryptoUtil.checkPassword(gMap.getString("password"), userMap.getString("password"))) {
		        return null;
		    }else {
		    	userMap.remove("password");
		    	userMap.put("userName", cryptoUtil.decryptData(userMap.getString("userName")));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return userMap;
	}
	
	@Override
	public void insertUser(GeekiveMap gMap) {
		try {
			gMap.put("email", cryptoUtil.encryptData(gMap.getString("email")));
			gMap.put("userName", cryptoUtil.encryptData(gMap.getString("userName")));
			gMap.put("password", cryptoUtil.encryptPassword(gMap.getString("password")));
			signMapper.insertUser(gMap);
			
			String parentCategoryUid = Util.generateUID("CTG");
			gMap.put("categoryUid"	, parentCategoryUid);
			gMap.put("categoryName"	, "Basic Category");
			gMap.put("depth"		, "1");
			signMapper.insertCategory(gMap);
			
			gMap.put("categoryUid"		, Util.generateUID("CTG"));
			gMap.put("parentCategoryUid", parentCategoryUid);
			gMap.put("categoryName"		, "My Archive");
			gMap.put("urlPath"			, "my-archive");
			gMap.put("depth"			, "2");
			signMapper.insertCategory(gMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertCategory(GeekiveMap gMap) {
		signMapper.insertCategory(gMap);
	}
}
