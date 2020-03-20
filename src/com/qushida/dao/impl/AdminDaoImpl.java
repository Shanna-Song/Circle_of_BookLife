package com.qushida.dao.impl;

import com.qushida.dao.AdminDao;
import com.qushida.po.Admin;
import com.qushida.po.Type;
import com.qushida.util.DBUtil;

public class AdminDaoImpl implements AdminDao{
	//创建工具类DBUTil对象
	DBUtil dbutil=new DBUtil();
	//登录
	@Override
	public Admin login(String name,String pwd){
		
		Admin admin= new Admin();
		String sql="select * from admin where name=? and pwd=?";
		//2.组织参数
		Object[] params={name,pwd};
		try {
			//4.调用dbutil方法
			admin= (Admin)dbutil.getObject(Admin.class,sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return admin;
	}

	@Override
	public int updateAdmin(Admin admin) {
		int i=0;
		String sql="update admin set name=?,pwd=? where id=?";	
		Object[] params={admin.getName(),admin.getPwd(),admin.getId()};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Admin getAdminById(int id) {
		Admin admin=null;
		String sql="select * from admin where id=?";
		Object[] params={id};
		try {
			admin=(Admin)dbutil.getObject(Admin.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
}
