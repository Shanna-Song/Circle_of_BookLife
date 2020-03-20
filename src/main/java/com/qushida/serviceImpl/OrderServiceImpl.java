package com.qushida.serviceImpl;

import com.qushida.dao.OrderDao;
import com.qushida.dao.impl.OrderDaoImpl;
import com.qushida.po.Order;
import com.qushida.po.OrderCriteria;
import com.qushida.po.ShoppingCar;
import com.qushida.service.OrderService;
import com.qushida.util.DBUtil;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao=new OrderDaoImpl();
	@Override
	public boolean addOrder(List<ShoppingCar> carList, String userId) {
		boolean result=false;
		//循环调用OrderDao中的addOrder
		//使用事物，保证同一链接Connection
		try {
			DBUtil.beginTranscation();
			//遍历购物车信息，得到所有记录
			for(ShoppingCar carItem:carList){
				Order order=new Order();
				int menuId=carItem.getMenuId();
				int sums=carItem.getSums();
				order.setMenuid(String.valueOf(menuId));
				order.setMenusum(String.valueOf(sums));
				order.setUserid(userId);
				orderDao.addOrder(order);
			}
			DBUtil.endTranscation();
			result=true;
		} catch (Exception e) {
			try {
				DBUtil.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			//关闭链接
			try {
				DBUtil.closeConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public Page getOrderByPage(Page page,OrderCriteria criteria) {		
		return orderDao.getOrderByPage(page,criteria);
	}
	@Override
	public boolean deleteOrderById(int orderId) {
		return orderDao.deleteOrderById(orderId)>0;
	}
	@Override
	public boolean updateOrderById(int orderId) {
		return orderDao.updateOrderById(orderId)>0;
	}
	@Override
	public List<OrderInfo> getSumOrderByDay(String date) {
		return orderDao.getSumOrderByDay(date);
	}
	@Override
	public List<OrderInfo> getSaleRank() {
		return orderDao.getSaleRank();
	}

}
