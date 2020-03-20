package com.qushida.dao;

import java.util.List;

import com.qushida.po.OrderCriteria;
import com.qushida.po.User;
import com.qushida.vo.OrderInfo;

public interface UserDao {
	public User login(String name,String pwd);
	public User getUserById(int id);
	//根据criteria获取用户订单
	public List<OrderInfo> getUserOrderByCriteria(OrderCriteria criteria);
	public int register(User user);
	public int updateUser(User user);
}
