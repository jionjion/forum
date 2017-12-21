package com.jionjion.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jionjion.forum.bean.Authority;

/**
 * 	权限类的仓库
 * @author JionJion
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{

}
