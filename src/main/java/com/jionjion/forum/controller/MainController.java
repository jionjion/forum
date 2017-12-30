package com.jionjion.forum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jionjion.forum.bean.Authority;
import com.jionjion.forum.bean.User;
import com.jionjion.forum.server.AuthorityService;
import com.jionjion.forum.server.UserService;

/**
 * @author JionJion
 *	主页控制器
 */
@Controller
public class MainController {

	/** 用户权限ID*/
	private static final Long ROLE_USER_AUTHORITY_ID = 2L;

	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 	跳转主页
	 * @return 重定向到主页
	 */
	@GetMapping("/")
	public String root() {
		
		return "redirect:/index";
	}
	
	
	/**
	 * 	进入主页
	 * @return	主页文件位置
	 */
	@GetMapping("/index")
	public String index() {
		
		return "index";
	}
	
	/**
	 * 	进入登录页面
	 * @return 登录页面文件位置
	 */
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	/**
	 * 	发生错误时,跳转到登录页面
	 * @param model 模型
	 * @return 登录页面
	 */
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMessage","登录失败,用户名或者密码错误");
		return "login";
	}
	
	/**
	 * 	注册页面
	 * @return 返回注册页面 
	 */
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}	
	
	/**
	 * 	注册用户链接
	 * @param user 	用户信息
	 * @return		跳转登录页面
	 */
	public String registerUser(User user) {
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);
		userService.registerUser(user);
		return "redirect:/login";
	}
}
