package com.homewin.bean;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private Integer state;
	private String code;
	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public User(Integer id, String username, String password, String email, Integer state, String code) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.state = state;
		this.code = code;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
