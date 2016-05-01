package com.lm.core.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.lm.core.entity.User;

@WebService
public interface UserWsService {
	List<User> getUserByAcount(@WebParam(name="name")String name,@WebParam(name="password")String password);
	
	void insert(User user);
}
