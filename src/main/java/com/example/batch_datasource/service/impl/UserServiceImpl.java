package com.example.batch_datasource.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.batch_datasource.entity.db1.UserEntity1;
import com.example.batch_datasource.entity.db2.UserEntity2;
import com.example.batch_datasource.repository.testdb1.UserRepository1;
import com.example.batch_datasource.repository.testdb2.UserRepository2;
import com.example.batch_datasource.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository1 userRepository1;
	
	@Autowired
	private UserRepository2 userRepository2;
	/**
	 * 分别向两个数据库中插入一条数据
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addUsers() {
		userRepository1.save(new UserEntity1("小李", 25));
		int i = 1/0;
		userRepository2.save(new UserEntity2("小名", 26));
	}
}
