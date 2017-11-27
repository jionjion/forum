package com.jionjion.forum.repository;

import org.springframework.data.repository.CrudRepository;

import com.jionjion.forum.bean.User;

/**
 * @author JionJion
 *	用户的资源管理类
 */
public interface UserRepository extends CrudRepository<User, Long>{

}
