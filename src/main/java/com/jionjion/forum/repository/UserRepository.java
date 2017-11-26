package com.jionjion.forum.repository;

import java.util.List;

import com.jionjion.forum.bean.User;

/**
 * @author JionJion
 *	用户的资源管理类
 */
public interface UserRepository{

	/**	新增或者更新用户信息
	 * @param 	用户信息类
	 * @return 	保存后的用户信息类
	 */
	User saveOrUpdateUser(User user);
	
	/**	删除用户信息
	 * @param 删除用户信息
	 */
	void deleteUser(Long id);
	
	/**	通过ID获得用户信息
	 * @param 用户id
	 * @return	用户信息
	 */
	User getUserById(Long id);
	
	/**	获得用户信息列表
	 * @return	用户信息列表
	 */
	List<User> listUsers();
}
