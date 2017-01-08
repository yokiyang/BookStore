package com.tz.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * 本来用来演示 用户实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:50:34
 */
@Entity
@Table(name="ONLINE_USER")
public class User {
	private Long userId;
	private String username;
	private String password;
	private String tel;
	private String email;
	private String address;
	private String comparate;
	
	@Id
	@GeneratedValue
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(length=50,nullable=false,unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=20,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(length=12)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(length=200)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=200)
	public String getComparate() {
		return comparate;
	}
	public void setComparate(String comparate) {
		this.comparate = comparate;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", tel=" + tel + ", email="
				+ email + ", address=" + address + ", comparate=" + comparate
				+ "]";
	}
	
}

