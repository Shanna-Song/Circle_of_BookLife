package com.qushida.dao.impl;

import com.qushida.po.Menus;
import com.qushida.util.DBUtil;
import com.qushida.util.Page;
import com.qushida.vo.MenuInfo;

public class MenusDaoImpl implements com.qushida.dao.MenusDao {
	private DBUtil dbutil=new DBUtil();
	@Override
	public Page getMenusByPage(Page page) {
		
		String sql="select m.id,m.name,t.name as typename,m.burden,m.brief,m.price,m.sums,m.price1,m.sums1,m.imgpath from menus m ,types t where t.id=m.typeid";
		Page rePage=dbutil.getQueryPage(MenuInfo.class, sql, null, page);
		return rePage;
	}

	@Override
	public int addMenu(Menus menus) {
		int i=0;
		String sql="insert into menus(name,typeid,burden,brief,price,price1,imgpath) values(?,?,?,?,?,?,?)";
		Object[] params={menus.getName(),menus.getTypeid(),menus.getBurden(),menus.getBrief(),menus.getPrice(),menus.getPrice1(),menus.getImgpath()};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Menus selectById(int id) {
		Menus menus=null;
		String sql="select * from menus where id=?";
		Object[] params={id};
		try {
			menus=(Menus)dbutil.getObject(Menus.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}

	@Override
	public int update(Menus menus) {
		int i=0;
		String sql="update menus set name=?,typeid=?,burden=?,brief=?,price=?,price1=? where id=?";	
		Object[] params={menus.getName(),menus.getTypeid(),menus.getBurden(),menus.getBrief(),menus.getPrice(),menus.getPrice1(),menus.getId()};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int deleteMenu(int id) {
		int i=0;
		String sql="delete from menus where id=?";
		Object[] params={id};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

}
