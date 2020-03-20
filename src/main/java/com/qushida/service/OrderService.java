package com.qushida.service;

import com.qushida.po.OrderCriteria;
import com.qushida.po.ShoppingCar;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;

import java.util.List;

public interface OrderService {
	public boolean addOrder(List<ShoppingCar> carList,String userId);
	//public OrderInfo getAllOrders(Order order);
	//分页查询订单
	public Page getOrderByPage(Page page,OrderCriteria criteria);
	//据id删除订单
	public boolean deleteOrderById(int orderId);
	//根据订单id更新订单
	public boolean updateOrderById(int orderId);
	public List<OrderInfo> getSumOrderByDay(String date);
	//获取菜单名，数量
	public List<OrderInfo> getSaleRank();
}
