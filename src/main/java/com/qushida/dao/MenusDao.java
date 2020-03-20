package com.qushida.dao;

import com.qushida.po.Menus;
import com.qushida.util.Page;

public interface MenusDao {
	//分页查询菜单,当前页，每页数据量，一共多少页，一共得数据
	//当前页要显示的数据
	public Page getMenusByPage(Page page);
	public int addMenu(Menus menus);
	//根据id获取菜单
	public Menus selectById(int id);
	public int update(Menus menus);
	public int deleteMenu(int id);
}
