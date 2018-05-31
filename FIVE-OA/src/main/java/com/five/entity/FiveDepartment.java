package com.five.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 这个类用于封装部门
 * @author JinnanXuan
 *
 */
public class FiveDepartment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String departementName;
	private String remark;
	private Integer deleteFlag;
	private Date createdTime;
	private Date updatedTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartementName() {
		return departementName;
	}
	public void setDepartementName(String departementName) {
		this.departementName = departementName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Override
	public String toString() {
		return "FiveDepartment [id=" + id + ", departementName=" + departementName + ", remark=" + remark
				+ ", deleteFlag=" + deleteFlag + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}
	
}
