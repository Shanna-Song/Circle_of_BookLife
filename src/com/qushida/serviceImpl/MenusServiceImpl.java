package com.qushida.serviceImpl;

import com.qushida.dao.MenusDao;
import com.qushida.dao.impl.MenusDaoImpl;
import com.qushida.po.Menus;
import com.qushida.service.MenusService;
import com.qushida.util.Page;

public class MenusServiceImpl implements MenusService {
	MenusDao menusdao=new MenusDaoImpl();
	@Override
	public Page getMenusByPage(Page page) {
		
		Page rePage=menusdao.getMenusByPage(page);
		return rePage;
	}
	//添加菜单
	@Override
	public int addMenu(Menus menus) {
		return menusdao.addMenu(menus);
	}
	//根据id查询菜单
	@Override
	public Menus selectById(int id) {
		return menusdao.selectById(id);
	}
	@Override
	public int update(Menus menus) {		
		return menusdao.update(menus);
	}
	@Override
	public int deleteMenu(int id) {

		return menusdao.deleteMenu(id);
	}

}
