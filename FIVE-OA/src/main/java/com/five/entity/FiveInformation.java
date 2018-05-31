package com.five.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信实体
 * 
 * @author lizhichao
 *
 */
public class FiveInformation implements Serializable {

	public static final int first = 0;// (私件/通知)0/1
	public static final int second = 1;//(私件/通知)0/1
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1363163832113141446L;
	private Integer id;// 主键id
	private Integer type;// (私件/通知)0/1
	private String username;// 发送人
	private String content;// 内容
	private Integer deleteFlag;// 删除标识
	private String remark;// 备注
	private Integer receiptId;// 收件人id
	private Date createTime;
	private Date updateTime;

	public FiveInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public Integer getType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

	public String getContent() {
		return content;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "FiveInformation [id=" + id + ", type=" + type + ", username=" + username + ", content=" + content
				+ ", deleteFlag=" + deleteFlag + ", remark=" + remark + ", receiptId=" + receiptId + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

}
