package com.sist.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.sist.web.security.LoginFailHandler;
import com.sist.web.security.LoginSuccessHandler;

import lombok.RequiredArgsConstructor;

/*
 * 	 보안 설정
 * 
 * 	 요청 = Security FilterChain = 인증 = 인가 = Controller = View
 * 				   |			   |	|				   |
 * 									  권한 체크			  접근권한 => 메뉴
 * 								 로그인된 사용자 인지
 * 				  설정 (인증 / 인가)
 *   인증
 *    = 로그인 / 아이디 , 비밀번호
 *    	 |
 *    	success => Authentication 객체 생성 => 주요정보를 저장
 *    						 | 인가
 *    							hasRole() , authenticated()
 *    							permitAll
 *    	fail
 *    
 *    Client -- Security Filter -- Authentication - Authorization - Controller
 *    			 사용자 정의								|
 *    												Session/Cookie
 *    												   |	  |
 *    												  일반보안  JWT
 *    				| 일반 로그인 / 카카오 로그인
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final LoginSuccessHandler loginSuccessHandler;
	private final LoginFailHandler loginFailHandler;
	private final DataSource dataSource;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/member/**").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
			)
			.formLogin(form -> form
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login_process")
				.usernameParameter("userid")
				.passwordParameter("userpwd")
				.defaultSuccessUrl("/main", false)
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailHandler)
				.permitAll()
			)
			.rememberMe(remember -> remember
				.key("my-secret-key")
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(60 * 60 * 24)
//				.tokenRepository(tokenRepository(dataSource))
//				.userDetailsService(userDetailsService)
			)
			.logout(logout -> logout
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/main")
				.invalidateHttpSession(true)
				.deleteCookies("remember-me", "JSESSIONID")
			);
		
		return http.build();
	}
	
	// 인증 관리자
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder
			.userDetailsService(jdbcUserDetailsService())
			.passwordEncoder(passwordEncoder);
		
		return builder.build();
	}
	
	@Bean
	JdbcUserDetailsManager jdbcUserDetailsService() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("SELECT userid username, userpwd password, enabled "
									  + "FROM project_member_3 WHERE userid = ?");
		manager.setAuthoritiesByUsernameQuery("SELECT userid username, authority "
											+ "FROM authority_3 WHERE userid = ?");
		
		return manager;
	}
	
	/*@Bean
	PersistentTokenRepository tokenRepository(DataSource dataSource) {
	    JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
	    repo.setDataSource(dataSource);
	    return repo;
	}*/
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
