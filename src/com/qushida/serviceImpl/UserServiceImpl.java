package com.qushida.serviceImpl;

import java.util.List;

import com.qushida.dao.UserDao;
import com.qushida.dao.impl.UserDaoImpl;
import com.qushida.po.OrderCriteria;
import com.qushida.po.User;
import com.qushida.service.UserService;
import com.qushida.vo.OrderInfo;

public class UserServiceImpl implements UserService {
	UserDao userDao=new UserDaoImpl();
	@Override
	public User login(String name, String pwd) {
		User user =userDao.login(name,pwd);
		return user;
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<OrderInfo> getUserOrderByCriteria(OrderCriteria criteria) {
		return userDao.getUserOrderByCriteria(criteria);
	}
	@Override
	public int register(User user) {
		return userDao.register(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
}
