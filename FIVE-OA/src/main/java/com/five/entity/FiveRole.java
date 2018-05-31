package com.five.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.five.common.util.DateFormatConverter;
/**
 * 这个类用于封装用户的角色
 * @author Xuanjinnan
 *
 */
public class FiveRole implements Serializable{
	
	public FiveRole() {
	}
	
	/**
	 * serialVersionUID:版本号
	 * id:角色数据库编号
	 * roleName:角色名
	 * createdTime:创建时间
	 * updateTime：更新时间
	 * createdName:创建人
	 * updateName：最后更改人
	 * deleteFlag：删除标识
	 * remark:备注
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String roleName;
	private Date createdTime;
	private Date updateTime;
	private String createdName;
	private String updateName;
	private Integer deleteFlag;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	@JsonSerialize(using= DateFormatConverter.class)
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	@JsonSerialize(using= DateFormatConverter.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "FiveRole [id=" + id + ", roleName=" + roleName + ", createdTime=" + createdTime + ", updateTime="
				+ updateTime + ", createdName=" + createdName + ", updateName=" + updateName + ", deleteFlag="
				+ deleteFlag + ", remark=" + remark + "]";
	}
	
}
