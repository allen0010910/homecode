package com.cn.hnust.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.entity.md5Pwd;
import com.cn.hnust.service.IMd5Service;

@Controller
@RequestMapping(value="/md5")
public class Md5Controller {
	
	@Resource
	private IMd5Service md5Service;
	
	@RequestMapping(value="/list", method ={RequestMethod.POST})
	public @ResponseBody md5Pwd list(HttpServletRequest request,Map<String,Object> model){
		String content=request.getParameter("content");
		md5Pwd md5=md5Service.list(content);
		return md5;
	}
	
	@RequestMapping(value="/index", method ={RequestMethod.GET})
	public String toIndex(HttpServletRequest request,Map<String,Object> model){
		return "demo";
	}
	
}
