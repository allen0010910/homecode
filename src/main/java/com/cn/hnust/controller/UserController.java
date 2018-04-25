package com.cn.hnust.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cn.hnust.entity.User;
import com.cn.hnust.service.IUserService;
import com.cn.hnust.util.Md5Utils;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/login",method={RequestMethod.GET})
	public String loginIndex(HttpServletRequest request,Map<String, Object> dataMap){
		return "login";
	}
	
	@RequestMapping(value="/register",method={RequestMethod.GET})
	public String registerIndex(HttpServletRequest request,Map<String, Object> dataMap){
		return "register";
	}
	
	@RequestMapping(value="/doRegister",method={RequestMethod.POST})
	public @ResponseBody User doRegister(User user,HttpServletRequest request){
//		
		String password=request.getParameter("password");
		password=Md5Utils.getMD5_32bits(password);
		user.setPassword(password);
		User u=userService.insert(user);
		return u;
	}
	
	@RequestMapping(value="/doLogin",method={RequestMethod.POST})
	public @ResponseBody String doLogin(HttpServletRequest request,Map<String, Object> dataMap){
		String username=request.getParameter("username");
		String password=Md5Utils.getMD5_32bits(request.getParameter("password"));
		Subject subject=SecurityUtils.getSubject();
		
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
		}catch(AuthenticationException e){
			System.out.println("登录失败："+e.getMessage());
			return "0";
		}
		
		return "1";
	}
	
	@RequestMapping(value="/unauthorized",method={RequestMethod.GET})
	public String unauthorized(HttpServletRequest request,Map<String, Object> dataMap){
		String code=request.getParameter("code");
		if(code != null && code.equals("1")){
			dataMap.put("loginFalse", "登录失败");
		}
		return "testftl";
	}
}
