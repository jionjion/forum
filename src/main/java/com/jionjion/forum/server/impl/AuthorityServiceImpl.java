package com.jionjion.forum.server.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jionjion.forum.bean.Authority;
import com.jionjion.forum.repository.AuthorityRepository;
import com.jionjion.forum.server.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
