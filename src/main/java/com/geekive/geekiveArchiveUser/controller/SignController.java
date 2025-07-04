package com.geekive.geekiveArchiveUser.controller;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekive.geekiveArchiveUser.common.Constants;
import com.geekive.geekiveArchiveUser.common.JwtUtil;
import com.geekive.geekiveArchiveUser.common.MessageUtil;
import com.geekive.geekiveArchiveUser.common.Util;
import com.geekive.geekiveArchiveUser.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveUser.mapper.service.SignService;

@Controller
@RequestMapping("/sign")
public class SignController {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Resource
	SignService signService;

	@GetMapping(value = "/signup")
	public String signupGet() throws Exception{
		return "home/sign/signup";
	}
	
	@ResponseBody
	@PostMapping(value = "/signup")
	public GeekiveMap signupPost(@RequestBody GeekiveMap gMap) throws Exception{
		try {
			gMap.put("userUid", Util.generateUID("USR"));
			signService.insertUser(gMap);
			gMap.setResultMessage(MessageUtil.getMessage("home.signup.complete"));
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@GetMapping(value = "/signin")
	public String signinGet() throws Exception{
		return "home/sign/signin";
	}
	
	@ResponseBody
	@PostMapping(value = "/signin")
	public GeekiveMap signinPost(HttpServletResponse response, HttpSession session, @RequestBody GeekiveMap gMap) throws Exception{
		GeekiveMap userMap = null;
		try {
			userMap = signService.selectUser(gMap);	
			if(Util.isEmpty(userMap)) {
				throw new Exception(MessageUtil.getMessage("home.signin.message.fail"));
			}else {
				/* cookie */
				ResponseCookie cookie = jwtUtil.generateToken(userMap.getString("userUid"));
				response.addHeader("Set-Cookie", cookie.toString());
				
				/* session */
				session.setAttribute("isSignedIn"	, true);
				session.setAttribute("userMap"		, userMap);
			}
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}	
		return gMap;
	}
	
	@GetMapping(value = "/signout")
	public String signout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		session.invalidate();
		jwtUtil.invalidateToken(request, response);
		return "redirect:/";
	}

	@ResponseBody
	@PostMapping(value = "/checkEmailExistence")
	public GeekiveMap checkEmailExistence(HttpServletRequest request, @RequestBody GeekiveMap gMap) throws Exception{
		try {
			if(signService.checkEmailExistence(gMap)) {
				throw new Exception(MessageUtil.getMessage("home.signup.message.email.exist"));
			}
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@ResponseBody
	@PostMapping(value = "/checkArchiveNameExistence")
	public GeekiveMap checkArchiveNameExistence(HttpServletRequest request, @RequestBody GeekiveMap gMap) throws Exception{
		try {
			if(signService.checkArchiveNameExistence(gMap)) {
				throw new Exception(MessageUtil.getMessage("home.signup.message.archive.exist"));
			}
			if(Arrays.asList(Constants.BANNED_ARCHIVE_NAME).contains(gMap.getString("archiveName"))) {
				throw new Exception(MessageUtil.getMessage("home.signup.message.archive.exist"));
			}
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
}
