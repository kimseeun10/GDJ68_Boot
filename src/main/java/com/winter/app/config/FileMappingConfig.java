package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration // ***-context.xml과 같은 역할
@Slf4j
public class FileMappingConfig implements WebMvcConfigurer{
	
	//local file 위치
	@Value("${app.upload.mapping}")
	private String filePath;
	
	//요청 URL 주소
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		log.info("=====================================");
//		log.info("filePath {} ", filePath );
//		log.info("urlPath {} ", urlPath);
//		log.info("=====================================");
		
		//<resources mapping="/resources/**" location="/resources/" />
		registry.addResourceHandler(urlPath) //요청 URL 주소
				.addResourceLocations(filePath); // Local file 위치
	}
	
}
