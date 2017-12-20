package com.jionjion.forum.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jionjion.forum.bean.Menu;

/**
 *	管理员访问控制器
 * @author JionJion
 */
@RestController
@RequestMapping("/admins")
public class AdminController {

	/**
	 * 	返回用户管理
	 * @param model	模型
	 * @return	 	模型页面
	 */
	@GetMapping
	public ModelAndView listUsers(Model model) {
		List<Menu> list = new ArrayList<>();
		list.add(new Menu("用户管理", "/users"));
		model.addAttribute("list", list);
		return new ModelAndView("admin/index","model",model);
	}
}
