package com.org.UserManage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user_info")
public class User { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id; 
	@Column(name="user_name") 
	//@NotNull(message="user name should not be null")
	private String name; 
	@Column(name="user_email")
	//@Email(message = "email should be proper formated")
	private String email; 
	@Column(name="user_pwd") 
	//@NotNull
	private String password;
	@Column(name="user_mob")
	//@Pattern(regexp="^[789]\\d{9}$")
	private long mobile;
	@Column(name="user_sal") 
	//@NotNull
	private double salary; 
	@Column(name="user_gender")
	//@NotNull
	private String gender;
	public User(int id, String name, String email, String password, long mobile, double salary,String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.salary = salary;
		this.gender=gender;
	}
public User() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void SetGender(String gender) { 
		this.gender=gender;
		
	}
	public String getGender() {
		return gender;
	}
	
	
	

}
