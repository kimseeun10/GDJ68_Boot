package com.winter.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer{

	@Bean
	public LocaleResolver localeResolver() {
		//1. session 이용
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2. cookie 이용
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		cookieLocaleResolver.setCookieName("lang");
		
		return resolver; // return cookieLocaleResolver;
	}
	
	// Message Interceptor 객체 생성
	
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		
		changeInterceptor.setParamName("lang");
		//parameter를 받아서 언어 구분
		//url?lang=ko
		
		return changeInterceptor;
	}
	
}
