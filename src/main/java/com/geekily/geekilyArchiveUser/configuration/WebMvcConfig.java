package com.geekily.geekilyArchiveUser.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.geekily.geekilyArchiveUser.common.Constants;
import com.geekily.geekilyArchiveUser.common.PropertyUtil;
import com.geekily.geekilyArchiveUser.interceptor.CommonDataInterceptor;
import com.geekily.geekilyArchiveUser.interceptor.LogInterceptor;
import com.geekily.geekilyArchiveUser.interceptor.LoginInterceptor;
import com.geekily.geekilyArchiveUser.mapper.service.ArchiveService;
import com.geekily.geekilyArchiveUser.mapper.service.LogService;

@Component
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private ArchiveService archiveService;
	
	@Autowired
	private LogService logService;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			.order(1)
			.addPathPatterns("/sign/signin", "/sign/signup");
		
		CommonDataInterceptor commonDataInterceptor = new CommonDataInterceptor(archiveService);
		registry.addInterceptor(commonDataInterceptor)
			.order(2)
			.addPathPatterns("/**")
			.excludePathPatterns(Constants.EXCLUDE_PATH);

		registry.addInterceptor(new LogInterceptor(logService))
			.order(3)
			.addPathPatterns("/**")
			.excludePathPatterns(Constants.EXCLUDE_PATH);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/upload/**")
			.addResourceLocations("file:///" + PropertyUtil.getProperty("upload.path"));
	}
}
