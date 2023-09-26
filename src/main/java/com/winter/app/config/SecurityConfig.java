package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecuritySuccessHandler handler;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder(); //암호화 시켜주는 클래스 (객체필요 - Bean 사용)
	}
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		
		return web -> web
				.ignoring()
				.antMatchers("/css/**")
				.antMatchers("/img/**")
				.antMatchers("/js/**")
				.antMatchers("/vendor/**")
				;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors()
			.and()
			.csrf()
			.disable()
			.authorizeHttpRequests()
				.antMatchers("/notice/add").hasRole("ADMIN")//ROLE_ADMIN에서 ROLE_를 제외
				.antMatchers("/manager/*").hasAnyRole("ADMIN","MANAGER") //ADMIN, MANAGER 둘중 하나만 가지고 있으면 통과
				.antMatchers("/").permitAll()
				.and()
			//form 관련 설정
			.formLogin()
				.loginPage("/member/login") //실제 로그인을 처리하는 주소를 의미
				.defaultSuccessUrl("/")
				//.successHandler(handler)
				//.failureUrl("/member/login?message=LoginFail")
				.failureHandler(getFailHandler())
				.permitAll() //위 경로로 가는 것을 누구나 허용하겠다는 뜻
				.and()
			.logout()
				.logoutUrl("/member/logout")
				//.logoutSuccessUrl("/")
				.addLogoutHandler(getLogoutAdd())
				.logoutSuccessHandler(getLogoutHandler())
				.invalidateHttpSession(true)
				.deleteCookies("JESSIONID")
				.and()
			.sessionManagement()
			;
		return httpSecurity.build();
	}
	
	private SecurityLogoutHandler getLogoutHandler() {
		return new SecurityLogoutHandler();
	}
	
	private SecurityLogoutAdd getLogoutAdd() {
		return new SecurityLogoutAdd();
	}
	
	private SecurityFailHandler getFailHandler() {
		return new SecurityFailHandler();
	}
	
}
