package com.qushida.serviceImpl;

import com.qushida.dao.AdminDao;
import com.qushida.dao.impl.AdminDaoImpl;
import com.qushida.po.Admin;
import com.qushida.service.AdminService;
//import com.qushida.service;
public class AdminServiceImpl implements AdminService{
	//创建admindao对象调用login
	AdminDao adminDao=new AdminDaoImpl();
	@Override
	public Admin login(String name,String pwd){
		Admin admin =adminDao.login(name,pwd);
		return admin;
	}

	@Override
	public int updateAdmin(Admin admin) {
		return adminDao.updateAdmin(admin);
	}

	@Override
	public Admin getAdminById(int id) {
		return adminDao.getAdminById(id);
	}
}
