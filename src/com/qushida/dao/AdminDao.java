package com.qushida.dao;

import com.qushida.po.Admin;

public interface AdminDao {
	//登录
	public Admin login(String name,String pwd);
	//更改密码
	public int updateAdmin(Admin admin);
	public Admin getAdminById(int id);
}
