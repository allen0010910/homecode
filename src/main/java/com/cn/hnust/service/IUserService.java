package com.cn.hnust.service;

import com.cn.hnust.entity.User;

public interface IUserService {

	public Integer insert(String username,String password);
	
	public User getByUser(String username,String password);
	
	public User insert(User user);
}
