package com.cn.hnust.util;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.entity.User;

public class ShiroRealm extends AuthorizingRealm{
	@Resource
	private IUserDao userDao;

	String pass;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Object principal = principalCollection.getPrimaryPrincipal();
		if("admin".equals(principal)){
			info.addRole("admin");
		}
		if("user".equals(principal)){
			info.addRole("list");
		}
		info.addRole("user");
		return info;
	}
	
	/**
	 * 用户验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1. token 中获取登录的 username! 注意不需要获取password.
        Object principal = token.getPrincipal();
                
        //2. 利用 username 查询数据库得到用户的信息. 
        User user=userDao.getByUserName((String)principal);
        if(user!=null){
            pass=user.getPassword();
        }
        
        /*String source = "";
        ByteSource credentialsSalt = new Md5Hash(source);*/
        
        //当前 Realm 的name
        String realmName = getName();
        //返回值实例化
        SimpleAuthenticationInfo info = 
                new SimpleAuthenticationInfo(principal, pass, realmName);
        
        return info;
	}

}
