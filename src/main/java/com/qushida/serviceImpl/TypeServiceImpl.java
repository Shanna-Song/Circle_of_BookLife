package com.qushida.serviceImpl;

import com.qushida.dao.TypeDao;
import com.qushida.dao.impl.TypeDaoImpl;
import com.qushida.po.Type;
import com.qushida.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {
	TypeDao typedao=new TypeDaoImpl();
	@Override
	public List<Type> getAllType() {
		List<Type> list=typedao.getAllType();
		return list;
	}
	//添加类别
	@Override
	public int addType(String name){
		//判断是否已经存在,避免重复添加?????????????
		return typedao.addType(name);
	}
	@Override
	public Type selectById(int id){
		return typedao.selectById(id);
	}
	@Override
	public int update(Type type){
		return typedao.update(type);
	}
	@Override
	public int deleteType(int id){
		return typedao.deleteType(id);
	}
}
