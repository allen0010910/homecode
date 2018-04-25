package com.cn.hnust.service;

import com.cn.hnust.entity.md5Pwd;

public interface IMd5Service {

	public md5Pwd list(String content);
	
	public Integer insert(md5Pwd md5);
}
