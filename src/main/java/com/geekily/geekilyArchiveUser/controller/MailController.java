package com.geekily.geekilyArchiveUser.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.geekily.geekilyArchiveUser.common.Constants;
import com.geekily.geekilyArchiveUser.common.Util;
import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyConnector;
import com.geekily.geekilyArchiveUser.geekilyCustom.GeekilyMap;

@Controller
@RequestMapping(value = "/email")
public class MailController {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@ResponseBody
	@PostMapping(value = {"", "/"})
	public GeekilyMap email(HttpServletRequest request, @RequestBody GeekilyMap gMap) throws Exception{
		String type 	= gMap.getString("purpose");
		String email 	= gMap.getString("email");
		try {
			if(Util.isEmpty(email)) {
				throw new Exception("Error. Server hasn't received the email address.");
			}
			
			MimeMessage message = javaMailSender.createMimeMessage();
			
			switch (type) {
			case Constants.EMAIL_PURPOSE_SIGNUP :
				String code = Util.verificationCodeGenerator();
				
				GeekilyConnector gConnector = new GeekilyConnector.Builder(Util.getBaseURL(request) + "/email/code").build();
				gConnector.sendPost();
					
				String html = gConnector.getResponseData();
				html = html.replace("{{message}}"	, "Thank you for registering with Geekily Archive. Please use the following code to verify your email:");
				html = html.replace("{{code}}"		, code);
				
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
		        helper.setTo(email);
		        helper.setSubject("Geekily Archive: Your Email Verification Code");
		        helper.setText(html, true);
		        javaMailSender.send(message);
		        
		        gMap.put("code", code);
		        gMap.setResultMessage("Code has been sent to your email. please check your email.");
				break;
			default:
				throw new Exception("Error. Please contact the administrator.<br/>Possible cause : Unable to determine the purpose of the email transmission.");
			}
		} catch (Exception e) {
			gMap.put("resultCode"	, 0);
			gMap.put("resultMessage", e.getMessage());
		}
		return gMap;
	}
	
	@PostMapping(value = "/code")
	public String code(HttpServletRequest request, ModelMap modelMap) throws Exception{
		return "common/email/code";
	}
}
