package com.five.dao;

import java.util.List;

import com.five.entity.FiveEntityForZTree;
import com.five.entity.FiveUser;
/**
 * 
 * 用户Dao接口
 * @author 000
 *
 */
public interface FiveUserDao {


	/*****************下面是玄今男写的***************************/

	/**
	 * 根据用户名字查找用户权限标识信息
	 * 例如：sys:role:view,sys:role:add
	 * @param userName
	 * @return
	 */
	List<String> findFiveUserPermissions(Integer id);
	
	/**
	 * 根据用户名，查用户所有记录
	 * @param username 用户名
	 * @return
	 */
	FiveUser findFiveUserByUserName(String username);
	
	
	/*****************下面是石军磊写的主要是石军磊写***************************/
	/**
	 * 
	 * 查询所有用户记录
	 * @return 用户集合
	 */
	List<FiveUser> findObjects();


	/**
	 * 
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	FiveUser findObjectById(Integer id);

	/**
	 * 
	 * 添加用户
	 * @param user 实体类封装用户信息
	 * @return 影响的记录数
	 */
	int  insertObject(FiveUser user);
	
	/**
	 * 基于id修改用户状态
	 * 
	 */
	int updateTypeById(Integer id);
	
	
	/**
	 * 
	 * 修改用户
	 */
	
	int updateObject(FiveUser user);
	
	/**
	 * 
	 * 基于username查询用户
	 */
	List<FiveUser> findObjectByName(String username);
	
	
	/**
	 * 
	 * 基于id删除用户
	 */
	
	int deleteObjectById(Integer id);
	
	/**
	 * 
	 * 基于ID查询用户角色
	 
	
	List<Integer> findRoleIdsByUserId(Integer userId);
	*/
	
	List<FiveEntityForZTree> findAllUsers();

	FiveUser findIdByName(String username);
	
}
