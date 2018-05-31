package com.five.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.five.common.vo.UserRole;
import com.five.entity.FiveUser;
import com.github.pagehelper.PageInfo;
/**
 * 用户服务层接口
 * @author 000
 *
 */

import net.sf.ehcache.util.FindBugsSuppressWarnings;
public interface FiveUserService {
	
	
	/**
	 * 查询所有的用户
	 * @return 用户集合
	 */
	PageInfo<FiveUser> findObjects( Integer pageCurrent);

	/**
	 * 
	 * 基于id查询用户
	 * 返回UserRole
	 * @param id
	 * @return
	 */
	 UserRole findObjectById(Integer id);



	/**
	 * 
	 * 添加用户
	 * @param user 添加的用户信息
	 * @return
	 */
	int  saveObject( FiveUser user,String roleIds);

	/**
	 * 基于id修改用户状态
	 * 
	 */
	int updateTypeById(Integer id);


	/**
	 * 
	 * 基于id修改用户
	 */
	
	int updateObject(FiveUser user,String roleIds);
	
	/**
	 * 基于id删除用户
	 * @param id
	 * @return
	 */
	int deleteObjectById(Integer id);

	/**
	 * 
	 * 基于username查询用户
	 * @param username
	 * @return
	 */
	PageInfo<FiveUser> findObjectByName(String username);
	/**
	 * 查询所有的用户
	 * @return 用户集合
	 */
	List<FiveUser> findObjects();
	
	/**
	 * 
	 * 分页查询用户记录
	 * @param currentPage 开始下标
	 * @param pageSize 页数
	 * @return
	 */
	PageInfo<FiveUser> findObjectsByPage(Integer currentPage,Integer pageSize);
	
	
	/**
	 * 
	 * 添加用户
	 * @param user 添加的用户信息
	 * @return
	 */
	 int  insertObject( FiveUser user);
	
	 FiveUser login(String username,String password, String kaptcha, HttpServletRequest request);
}
