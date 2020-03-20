package com.qushida.service;

import java.util.List;

import com.qushida.po.Type;

public interface TypeService {
	public List<Type> getAllType();
	public int addType(String name);
	public Type selectById(int id);
	public int update(Type type);
	public int deleteType(int id);
}
