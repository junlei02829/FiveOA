package com.five.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FiveUserRoleDao {
	
	
	
	int insertObject(
			@Param("userId")Integer userId,
			@Param("roleIds")String[] roleIds);
	
	
	
	List<Integer> findRoleIdsByUserId(Integer userId);
	
	
	int deleteObject(@Param("userId")Integer userId);
	
	int insertObjectFromRole(@Param("userId")Integer userId,@Param("roleId")Integer roleId);
	

}
