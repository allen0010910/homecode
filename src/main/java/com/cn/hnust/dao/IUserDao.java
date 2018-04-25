package com.cn.hnust.dao;

import org.springframework.stereotype.Repository;

import com.cn.hnust.entity.User;

@Repository
public interface IUserDao {
	Integer insert(User user);
	
	User getById(Integer id);
	
	User getByUser(User user);
	
	User getByUserName(String userName);
}
