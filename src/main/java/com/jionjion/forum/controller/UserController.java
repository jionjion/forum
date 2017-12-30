package com.jionjion.forum.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jionjion.forum.bean.Authority;
import com.jionjion.forum.bean.User;
import com.jionjion.forum.exception.ConstraintViolationExceptionHandler;
import com.jionjion.forum.server.AuthorityService;
import com.jionjion.forum.server.UserService;
import com.jionjion.forum.vo.Response;


/**
 *	userModel 为数据模板的名字,需要在前台显示指定数据模板
 * @author JionJion
 */
@RestController
@RequestMapping("/users")
public class UserController {

	/**用户对象服务*/
	@Autowired
	private UserService userService;
	
	/**权限对象服务*/
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * 从用户存储库 获取用户列表
	 * @return
	 */
	private Iterable<User> getUserlist() {
 		return userService.findAll();
	}	
	
	/**	查询所有用户,默认显示
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView list(Model model) {
		model.addAttribute("userList",getUserlist());
		model.addAttribute("title","用户管理");
		return new ModelAndView("users/list", "userModel",model);
	}
	
	/**	根据ID查询用户
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Long id , Model model) {
		User user = userService.findOne(id);
		model.addAttribute("user",user);
		model.addAttribute("title","查看用户");
		return new ModelAndView("users/view", "userModel",model);
	}
	
	/**	获取创建用户表单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model) {
		model.addAttribute("user",new User());
		model.addAttribute("title","创建用户");
		return new ModelAndView("users/form", "userModel",model);
	}
	
	/**	
	 * 保存或者更新用户
	 * 返回信息为JSON字符对象,不能作为页面显示.
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response> create(User user,Long authorityId) {
		//权限列表的加入
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authorityService.getAuthorityById(authorityId));
		user.setAuthorities(authorities);
		try {
			user = userService.save(user);
		} catch (ConstraintViolationException e) {
			return ResponseEntity.ok().body(new Response(false,ConstraintViolationExceptionHandler.getMessage(e),null));
		}
		return ResponseEntity.ok().body(new Response(true,"保存成功",user));
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id, Model model) {
		userService.delete(id);
		model.addAttribute("userList", getUserlist());
		model.addAttribute("title", "删除用户");
		return new ModelAndView("users/list", "userModel", model);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
		User user = userService.findOne(id);
 
		model.addAttribute("user", user);
		model.addAttribute("title", "修改用户");
		return new ModelAndView("users/form", "userModel", model);
	}
}
