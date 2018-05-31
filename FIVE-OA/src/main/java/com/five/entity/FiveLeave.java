package com.five.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 请假表的实体类
 * 
 * @author lizhichao
 *
 */
public class FiveLeave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4269432760232296999L;

	public static final int first = 0;// 驳回
	public static final int second = 1;// 默认状态表示刚提交申请
	public static final int third = 2;// 一级审核通过
	public static final int fourth = 3;// 二级审核 审核成功

	private Integer id;// 主键id
	private Integer leaveId;// 请假字典表id;
	private Integer readAuthority;// 阅读权限
	private String leaveReason;// 请假内容
	private String leaveAnnexName;// 请假附件名字
	private String leaveAnnexPath;// 请假附件下载路径
	private String username;// 申请人姓名
	private String remark;// 备注
	private Integer deleteFlag;// 删除标识
	private Date leaveStartTime;// 请假开始时间
	private Date leaveEndTime;// 请假结束时间
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间
	private Integer userId;//用户id

	public FiveLeave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public Integer getReadAuthority() {
		return readAuthority;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public String getLeaveAnnexName() {
		return leaveAnnexName;
	}

	public String getLeaveAnnexPath() {
		return leaveAnnexPath;
	}

	public String getUsername() {
		return username;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public Date getLeaveStartTime() {
		return leaveStartTime;
	}

	public Date getLeaveEndTime() {
		return leaveEndTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public void setReadAuthority(Integer readAuthority) {
		this.readAuthority = readAuthority;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public void setLeaveAnnexName(String leaveAnnexName) {
		this.leaveAnnexName = leaveAnnexName;
	}

	public void setLeaveAnnexPath(String leaveAnnexPath) {
		this.leaveAnnexPath = leaveAnnexPath;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setLeaveStartTime(Date leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public void setLeaveEndTime(Date leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "FiveLeave [id=" + id + ", leaveId=" + leaveId + ", readAuthority=" + readAuthority + ", leaveReason="
				+ leaveReason + ", leaveAnnexName=" + leaveAnnexName + ", leaveAnnexPath=" + leaveAnnexPath
				+ ", username=" + username + ", remark=" + remark + ", deleteFlag=" + deleteFlag + ", leaveStartTime="
				+ leaveStartTime + ", leaveEndTime=" + leaveEndTime + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", userId=" + userId + "]";
	}

}
