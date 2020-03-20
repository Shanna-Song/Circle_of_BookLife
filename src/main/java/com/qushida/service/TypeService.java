package com.qushida.service;

import com.qushida.po.Type;

import java.util.List;

public interface TypeService {
	public List<Type> getAllType();
	public int addType(String name);
	public Type selectById(int id);
	public int update(Type type);
	public int deleteType(int id);
}
