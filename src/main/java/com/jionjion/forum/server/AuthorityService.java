package com.jionjion.forum.server;

import com.jionjion.forum.bean.Authority;

/**
 * 	权限类的服务接口
 * @author JionJion
 */
public interface AuthorityService {

	/**
	 * 	根据Id查询权限
	 * @param id 	权限ID
	 * @return		权限信息对象
	 */
	Authority getAuthorityById(Long id);
}