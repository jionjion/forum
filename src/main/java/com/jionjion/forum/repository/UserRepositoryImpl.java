package com.jionjion.forum.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.jionjion.forum.bean.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	//计数器,用于生成唯一ID
	private static AtomicLong counter = new AtomicLong();
	
	//容器,临时将用户信息保存在内存中
	private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<Long, User>();
	
	@Override
	public User saveOrUpdateUser(User user) {
		Long id = user.getId();
		//新增
		if (Objects.isNull(id)) {
			//生成新的ID
			id = counter.incrementAndGet();
			user.setId(id);
		}
		this.userMap.put(id, user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		this.userMap.remove(id);
	}

	@Override
	public User getUserById(Long id) {
		return this.userMap.get(id);
	}

	@Override
	public List<User> listUsers() {
		return new ArrayList<>(this.userMap.values());
	}

}
