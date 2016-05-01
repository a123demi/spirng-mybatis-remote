package com.lm.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lm.core.entity.User;
import com.lm.core.mapper.UserMapper;
import com.lm.core.service.UserHttpService;
import com.lm.core.service.UserRmiService;

public class UserHttpServiceImpl implements UserHttpService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> getUserByAcount(String name, String password) {
		System.err.println("httpInvoker获取用户信息:" + name + password);
		return new ArrayList<User>();
	}
	@Override
	public void insert(User user) {
		System.err.println("httpInvoker开始插入用户信息:" + user.toString());
	}

}
