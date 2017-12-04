package com.jionjion.forum.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author JionJion
 *	管理员访问控制器
 */
@RestController
public class AdminController {

	/**
	 * 	返回用户管理
	 * @param model	模型
	 * @return	 	模型页面
	 */
	public ModelAndView listUsers(Model model) {
		
		return new ModelAndView("admin/index","menuLiust",model);
	}
}
