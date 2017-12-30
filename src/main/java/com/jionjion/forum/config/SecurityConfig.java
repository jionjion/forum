package com.jionjion.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author JionJion
 * 	安全认证配置类
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=false)	//启用安全认证的方法
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** 记住我关键词*/
	private static final String KEY = "form.jionjion.top";
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	/**
	 * 	密码加密对象,返回密码的密文
	 * @return
	 */
	@Bean	//标识该返回值作为javaBean装入容器
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 	// 使用 BCrypt 加密
	}	
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	/**
	 * 	认证信息验证,传入用户名,验证密码是否一致
	 * @return 代理对象
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new  DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}	
	
	/**
	 * 	自定义Spring Security的认证方式,采用表单认证
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/css/**","/js/**","/fonts/**","/index","/feather/**","/h2-console/**")
			.permitAll()	//都可以访问
			.antMatchers("/users/**").hasRole("ADMIN") 		//需要对应角色访问
		.and()
			.headers().frameOptions().sameOrigin()			//允许同域名下加载页面,图标
		.and()
			.formLogin()	//基于表单的验证
			.loginPage("/login")	//登录表单
			.failureUrl("/login-error")	//登录失败页面
		.and()
			.rememberMe().key(KEY)
		.and()
			.exceptionHandling().accessDeniedPage("/403")	//异常重定向到403
//		.and()
//			.csrf().		//不启用csrf跨站点伪造请求验证
		;
	}

	/**
	 * 	认证信息管理
	 * @param auth	认证
	 * @throws Exception 登录错误
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()	//认证信息存于内存中
//			.withUser("Jion").password("123456").roles("ADMIN");	//认证角色
		// 传入接口,自动装配接口的实现类
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider);
	}

}
