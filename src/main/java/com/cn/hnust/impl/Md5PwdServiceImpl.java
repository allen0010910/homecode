package com.cn.hnust.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cn.hnust.entity.md5Pwd;
import com.cn.hnust.model.Md5PwdModel;
import com.cn.hnust.service.IMd5Service;
import com.cn.hnust.util.BusinessException;

@Service("md5PwdService")
public class Md5PwdServiceImpl implements IMd5Service{
	private Logger logger=Logger.getLogger(Md5PwdServiceImpl.class);
	
	@Resource
	private Md5PwdModel md5PwdModel;

	@Override
	public md5Pwd list(String content) {
		md5Pwd md5=null;
		try{
			md5=md5PwdModel.listByContent(content);
		}catch(BusinessException be){
			logger.error("错误代码："+be.getCode()+","+be.getMsg(),be);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return md5;
	}

	@Override
	public Integer insert(md5Pwd md5) {
		return md5PwdModel.insert(md5);
	}

}
