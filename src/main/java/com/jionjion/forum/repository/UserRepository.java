package com.jionjion.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jionjion.forum.bean.User;

/**
 * @author JionJion
 *	用户的资源管理类
 */
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * 	根据用户名模糊查询并分页
	 * @param username 	用户名
	 * @param pageable	分页组件
	 * @return	分页后的对象
	 */
	Page<User> findByUsernameLike(String username , Pageable pageable);
	
	/**
	 * 	根据用户名查询
	 * @param usename
	 * @return
	 */
	User findByUsername(String username);
}
