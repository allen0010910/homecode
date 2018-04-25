package com.cn.hnust.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cn.hnust.entity.User;
import com.cn.hnust.model.UserModel;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.BusinessException;

@Service(value="userService")
public class UserServiceImpl implements IUserService{
	private Logger logger=Logger.getLogger(UserServiceImpl.class);

	@Resource(name="userModel")
	private UserModel userModel;

	@Override
	public Integer insert(String username, String password) {
		Integer count=0;
		try{
			count=userModel.insert(username, password);
		}catch(BusinessException be){
			logger.error(be.getMsg(), be);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return count;
	}

	@Override
	public User getByUser(String username, String password) {
		User user=null;
		try{
			user=userModel.getByUser(username, password);
		}catch(BusinessException be){
			logger.error(be.getMsg(), be);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return user;
	}

	@Override
	public User insert(User user) {
		User u=null;
		try{
			u=userModel.insert(user);
		}catch(BusinessException be){
			logger.error(be.getMsg(), be);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return u;
	}
}
