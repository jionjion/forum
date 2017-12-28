package com.jionjion.forum.server;
/**
 * 	用户服务接口
 * @author JionJion
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jionjion.forum.bean.User;

public interface UserService {

	/**
	 * 	新增/修改用户信息
	 * @param user 	用户信息
	 * @return 	修改的用户信息
	 */
	User saveOrUpdateUser(User user);
	
	
	/**
	 * 	注册新用户
	 * @param user 	用户信息
	 * @return 	新注册的用户信息
	 */
	User registerUser(User user);
	
	/**	
	 * 	根据用户ID删除用户
	 * @param id	用户ID
	 */
	void removeUser(Long id);
	
	User getUserById(Long id);
	
	/**
	 * 	根据用户名模糊查询
	 * @param username	用户名
	 * @param pageable	分页组件
	 * @return	分页后的用户信息列表
	 */
	Page<User> listUsersByUsernameLike(String username,Pageable pageable);


	Iterable<User> findAll();


	User findOne(Long id);


	User save(User user);


	void delete(Long id);
	
}
