package com.winter.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityPasswordEncoder {

	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); //암호화 시켜주는 클래스 (객체필요 - Bean 사용)
	}
	
}
