package com.qushida.service;

import com.qushida.po.Admin;

public interface AdminService {

	public Admin login(String name,String pwd);
	public int updateAdmin(Admin admin);
	public Admin getAdminById(int id);
}
