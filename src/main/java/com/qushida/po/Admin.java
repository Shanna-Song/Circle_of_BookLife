package com.qushida.po;

public class Admin {
	public Admin() {
		super();
	}
	public Admin(int id, String authority, String name, String pwd) {
		super();
		this.id = id;
		this.authority = authority;
		this.name = name;
		this.pwd = pwd;
	}
	private int id;
	private String authority;//权限
	private String name;
	private String pwd;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	//下面是重写注解@Override
	@Override
	public String toString() {
		return "Admin [id=" + id + ", authority=" + authority + ", name=" + name + ", pwd=" + pwd + "]";
	}

}
