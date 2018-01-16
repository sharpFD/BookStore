package com.wyg.mapper;

import com.wyg.pojo.User;

public interface UserMapper {
	void insertUser(User user);
	User isExist(User user);
	User Login(User user);
	User findUserByName(String name);
	User findUserById(long id);
	void updateUser(User user);
}
