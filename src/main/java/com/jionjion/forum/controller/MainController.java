package com.jionjion.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author JionJion
 *	主页控制器
 */
@Controller
public class MainController {

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
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMessage","登录失败,用户名或者密码错误");
		return "login";
	}
}
