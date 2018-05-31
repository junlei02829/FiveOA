package com.five.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.five.common.util.DateFormatConverter;
/**
 * 
 * 用户实体类
 * 封装用户信息
 * @author junlei
 *
 */
public class FiveUser implements Serializable {
	
	private static final long serialVersionUID = -6222747367684095462L;
	
	private Integer id; //用户id 自增主键
	private String username; //用户姓名
	private String password; //用户密码
	private String salt; //加密的盐值
	private String phone; //用户手机号
	private String email; //用户邮箱
	private Integer type; //用户状态(0:禁用 1:启用)
	private Integer delete_flag ; //删除标识
	private Date create_time; //创建时间
	private Date update_time; //修改时间
	private String create_name; //创建人
	private String update_name; //修改人
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	
	
	@JsonSerialize(using=DateFormatConverter.class)
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	@JsonSerialize(using=DateFormatConverter.class)
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getCreate_name() {
		return create_name;
	}
	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}
	public String getUpdate_name() {
		return update_name;
	}
	public void setUpdate_name(String update_name) {
		this.update_name = update_name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", phone="
				+ phone + ", email=" + email + ", type=" + type + ", delete_flag=" + delete_flag + ", create_time="
				+ create_time + ", update_time=" + update_time + ", create_name=" + create_name + ", update_name="
				+ update_name + "]";
	}
	

}
