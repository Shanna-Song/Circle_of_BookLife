package com.qushida.dao.impl;

import com.qushida.dao.AdminDao;
import com.qushida.po.Admin;
import org.junit.Test;

public class AdminDaoImplTest {
	//测试登陆
	@Test
	public void testLogin(){
		AdminDao adminDao =new AdminDaoImpl();
		Admin admin =adminDao.login("sa", "123");
		System.out.println("admin"+admin);
	}
}
