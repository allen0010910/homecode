package com.cn.hnust.model;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cn.hnust.dao.IMd5PwdDao;
import com.cn.hnust.entity.md5Pwd;
import com.cn.hnust.util.BusinessException;
import com.cn.hnust.util.ErrorCode;
import com.cn.hnust.util.Md5Utils;

@Component
public class Md5PwdModel {

	@Resource
	private IMd5PwdDao md5PwdDao;
	
	public md5Pwd listByContent(String content){
		md5Pwd md5=null;
		if(content == null || content.equals("")){
			throw new BusinessException(ErrorCode.NULL_ERR);
		}
		md5=md5PwdDao.selectByContent(content);
		if(md5==null){
			md5Pwd newMd5=new md5Pwd();
			newMd5.setThirtytwoBits(Md5Utils.getMD5_32bits(content));
			newMd5.setSixteenBits(Md5Utils.getMD5_16bits(content));
			newMd5.setContent(content);
			Integer count=md5PwdDao.insertSelective(newMd5);
			if(count != 1){
				throw new BusinessException(ErrorCode.SYS_ERR);
			}
			md5=newMd5;
		}
		return md5;
	}
	
	public Integer insert(md5Pwd md5){
		return md5PwdDao.insertSelective(md5);
	}
	
}
