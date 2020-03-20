package com.qushida.service;

import com.qushida.po.Menus;
import com.qushida.util.Page;

public interface MenusService {

	public Page getMenusByPage(Page page);
	public int addMenu(Menus menus);
	public Menus selectById(int id);
	public int update(Menus menus);
	public int deleteMenu(int id);
}
