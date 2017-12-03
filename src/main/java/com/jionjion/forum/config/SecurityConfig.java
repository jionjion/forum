package com.jionjion.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author JionJion
 * 	安全认证配置类
 *
 */
@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll()	//都可以访问
			.antMatchers("/users/**").hasRole("ADMIN") 		//需要对应角色访问
			.and()
			.formLogin()	//基于表单的验证
			.loginPage("/login")	//登录表单
			.failureUrl("/login-error");	//登录失败页面
		
		//不启用csrf跨站点伪造请求验证
		http.csrf().disable();
	}

	/**
	 * 	访问/login ,进行登录验证
	 * @param auth	认证
	 * @throws Exception 登录错误
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()	//认证信息存于内存中
			.withUser("Jion").password("123456").roles("ADMIN");	//认证角色

	}
}
