package com.five.entity;
import java.io.Serializable;
import java.util.Date;
/***
 * 封装日志信息的实体类，封装以下字段:
 * error_message
 * 1)username
 * 3)method
 * 4)parameter
 * ip_address
 * delete_flag
 * remark
 * 5)create_time
 * update_time
 */
public class FiveLog implements Serializable{
	private static final long serialVersionUID = 8079413494640187382L;
	
	private Integer id;
	private String errorMessage;
	private String username;
	private String method;
	private String parameter;
	private String ipAddress;
	private Integer deleteFlag;
	private String remark;
	private Date createTime;
	private Date updateTime;
	
	public FiveLog() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "FiveLog [id=" + id + ", errorMessage=" + errorMessage + ", username=" + username + ", method=" + method
				+ ", parameter=" + parameter + ", ipAddress=" + ipAddress + ", deleteFlag=" + deleteFlag + ", remark="
				+ remark + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}



	
}
