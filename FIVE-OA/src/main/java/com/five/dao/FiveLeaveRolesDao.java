package com.five.dao;

import java.util.List;

import com.five.common.vo.FiveLeaveRoles;

/**
 * 查询用户角色名
 * @author lizhichao
 *
 */
public interface FiveLeaveRolesDao {

	/**
	 * 查询用户的角色名
	 * @param id
	 * @return
	 */
	List<FiveLeaveRoles> findRoleName(Integer id);
}
