package com.five.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.five.dao.FiveUserDao;
import com.five.entity.FiveUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm {

	@Autowired
	FiveUserDao fiveUserDao;
	
	@Override//登录授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		FiveUser fiveUser= (FiveUser)principals.getPrimaryPrincipal();
		List<String> list = fiveUserDao.findFiveUserPermissions(fiveUser.getId());
		System.out.println("================"+list);
		
		Set<String> set = new HashSet<>();
		for(String permission:list){
			set.add(permission);
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		return info;
	}

	@Override//登录认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		
		String username = upToken.getUsername();
		System.out.println(username);
		FiveUser fiveUser = fiveUserDao.findFiveUserByUserName(username);
		
		ByteSource bs=new SimpleByteSource(fiveUser.getSalt().getBytes());
		
		SimpleAuthenticationInfo info =  new SimpleAuthenticationInfo(
				
		fiveUser,fiveUser.getPassword(),bs,this.getName());
		
		return info;
	}

}
