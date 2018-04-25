package com.cn.hnust.model;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.entity.User;
import com.cn.hnust.util.BusinessException;
import com.cn.hnust.util.Md5Utils;

@Component(value="userModel")
public class UserModel {
	@Resource
	private IUserDao userDao;
	
	public Integer insert(String username,String password){
		User user=new User();
		user.setPassword(Md5Utils.getMD5_32bits(password));
		user.setUsername(username);
		User user2=userDao.getByUser(user);
		if(user2 != null){
			throw new BusinessException("用户已存在");
		}
		Integer count=userDao.insert(user);
		if(count != 1){
			throw new BusinessException("注册失败");
		}
		return count;
	}
	
	public User insert(User user){
		userDao.insert(user);
		return user;
	}

	public User getByUser(String username,String password){
		User user=new User();
		user.setPassword(Md5Utils.getMD5_32bits(password));
		user.setUsername(username);
		user=userDao.getByUser(user);
		if(user == null){
			throw new BusinessException("用户不存在");
		}
		return user;
	}
}
