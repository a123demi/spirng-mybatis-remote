package com.lm.core.mapper;

import java.util.List;

import com.lm.core.entity.User;

public interface UserMapper {
	List<User> getUserByAcount(String name,String password);
}
