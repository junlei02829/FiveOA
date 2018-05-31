package com.five.common.vo;

import java.io.Serializable;
import java.util.List;

import com.five.entity.FiveUser;

/**
 * 用于封装用户信息
 * 和用户角色关系
 * @author 000
 *
 */
public class UserRole implements Serializable {

	
	private static final long serialVersionUID = -6031768088128417838L;
	
	private FiveUser fiveUser;
	private List<Integer> roleIds;
	
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiveUser getFiveUser() {
		return fiveUser;
	}

	public void setFiveUser(FiveUser fiveUser) {
		this.fiveUser = fiveUser;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "UserRole [fiveUser=" + fiveUser + ", roleIds=" + roleIds + "]";
	}
	
	
	
	
}
