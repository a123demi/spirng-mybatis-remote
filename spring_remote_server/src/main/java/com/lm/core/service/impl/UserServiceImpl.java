package com.lm.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lm.core.entity.User;
import com.lm.core.mapper.UserMapper;
import com.lm.core.service.UserService;

public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> getUserByAcount(String name, String password) {
		System.err.println("获取用户信息:" + name + password);
		return new ArrayList<User>();
	}
	@Override
	public void insert(User user) {
		System.err.println("开始插入用户信息:" + user.toString());
	}
}
