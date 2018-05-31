package com.five.entity;

import java.io.Serializable;

public class FivePhone implements Serializable{

	private static final long serialVersionUID = 2515425381712703450L;
	private String username;
	private String department;
	private String area;
	private String phone;
	private String email;
	private String role;
	public FivePhone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public String getDepartment() {
		return department;
	}
	public String getArea() {
		return area;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "FivePhone [username=" + username + ", department=" + department + ", area=" + area + ", phone=" + phone
				+ ", email=" + email + ", role=" + role + "]";
	}
	
	
}
