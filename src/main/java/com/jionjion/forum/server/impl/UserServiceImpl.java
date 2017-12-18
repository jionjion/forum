package com.jionjion.forum.server.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jionjion.forum.bean.User;
import com.jionjion.forum.repository.UserRepository;
import com.jionjion.forum.server.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public User saveOrUpdateUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User registerUser(User user) {
		
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void removeUser(Long id) {

		userRepository.delete(id);
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepository.findOne(id);
	}
	
	@Override
	public Page<User> listUsersByUsernameLike(String username, Pageable pageable) {
		
		//模糊查询
		username = '%' + username +'%';
		return userRepository.findByUsernameLike(username, pageable);
	}

}
