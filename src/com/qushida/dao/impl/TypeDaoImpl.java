package com.qushida.dao.impl;

import java.util.List;

import com.qushida.dao.TypeDao;
import com.qushida.po.Type;
import com.qushida.util.DBUtil;

public class TypeDaoImpl implements TypeDao {
	//创建工具类的对象
	private DBUtil dbutil=new DBUtil();
	//获取所有类别
	@Override
	public List<Type> getAllType() {
		List list=null;
		//1.写sql语句
		String sql="select * from types";		
		try {//调用获取List的方法
			list=dbutil.getQueryList(Type.class, sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int update(Type type){
		int i=0;
		String sql="update types set name=? where id=?";	
		Object[] params={type.getName(),type.getId()};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	//添加类别
	@Override
	public int addType(String name){
		int i=0;
		String sql="insert into types(name) values(?)";
		Object[] params={name};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public Type selectById(int id) {
		Type type=null;
		String sql="select * from types where id=?";
		Object[] params={id};
		try {
			type=(Type)dbutil.getObject(Type.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}
	@Override
	public int deleteType(int id){
		int i=0;
		String sql="delete from types where id=?";
		Object[] params={id};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
