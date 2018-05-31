package com.five.common.vo;

/**
 * 查询用户的角色名
 * @author lizhichao
 *
 */
public class FiveLeaveRoles {

	private Integer id;
	private String roleName;

	public FiveLeaveRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "FiveLeaveRoles [id=" + id + ", roleName=" + roleName + "]";
	}

}
