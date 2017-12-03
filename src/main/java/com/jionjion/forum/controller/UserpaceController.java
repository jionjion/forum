package com.jionjion.forum.controller;


import java.util.Objects;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JionJion
 * 用户空间访问控制器
 */
@RestController
@RequestMapping("/user")
public class UserpaceController {

	/**
	 * 	跳转进入用户空间
	 * @param username	用户名
	 * @return			用户空间
	 */
	@RequestMapping("/{username}")
	public String userSpace(@PathParam(value="username") String username) {
		System.out.println("访问用户空间:" + username);
		return "/users/userspace";
	}
	
	/**
	 * @param username	用户名
	 * @param order		排序
	 * @param category	分类
	 * @param keyword	关键词
	 * @return			对个人留言进行排序
	 */
	@GetMapping("/{username}/forums")
	public String listForumsByOrder(@PathVariable(value="username") String username,
				@RequestParam(value="order",required=false,defaultValue="new") String order,
				@RequestParam(value="category",required=false) Long category,
				@RequestParam(value="keyword",required=false) String keyword) {
		
		//分类不为空
		if (Objects.nonNull(category)) {
			System.out.println("收到分类:" + category);
			return "/users/userspace";
		}
		
		//字符为空,长度不为0
		if (Objects.nonNull(keyword) && !keyword.isEmpty()) {
			System.out.println("收到关键词:" + keyword);
			return "/users/userspace";
		}
		
		//其他情况
		System.out.println("收到排序:" + order);
		return "/users/userspace";
	}
	
	/**
	 * @param id	留言ID
	 * @return		留言页面
	 */
	@GetMapping(value="/{username}/forums/{id}")
	public String listForumsByOrder(@PathVariable(value="id") Long id) {
		System.out.println("获得留言ID" + id);
		return "/users/forums";
	}
	
	public String editForum() {
		
		return "/users/forumsedit";
	}
}
