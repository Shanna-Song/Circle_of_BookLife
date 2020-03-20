package com.qushida.dao;

import java.sql.Date;
import java.util.List;

import com.qushida.po.Order;
import com.qushida.po.OrderCriteria;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;

//订单表操作的接口
public interface OrderDao {
	//添加一条记录
	public int addOrder(Order order) throws Exception;
	//分页查询订单
	public Page getOrderByPage(Page page,OrderCriteria criteria);
	//据id删除订单
	public int deleteOrderById(int orderId);
	//根据订单id修改订单
	public int updateOrderById(int orderId);
	//获取最近时间的（菜品名称，订购数量，单价）
	public List<OrderInfo> getSumOrderByDay(String date);
	//获取菜单名，数量
	public List<OrderInfo> getSaleRank();
}
