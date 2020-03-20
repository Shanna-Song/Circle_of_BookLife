package com.qushida.dao.impl;

import com.qushida.dao.OrderDao;
import com.qushida.po.Order;
import com.qushida.po.OrderCriteria;
import com.qushida.util.DBUtil;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
	private DBUtil dbutil=new DBUtil();
	@Override
	public int addOrder(Order order) throws Exception {
		String sql="insert into orders(userid,menuid,menusum,times,delivery) values(?,?,?,sysdate(),'0')";
		Object[] params={order.getUserid(),order.getMenuid(),order.getMenusum()};
		int rows = dbutil.execute(DBUtil.getConnection(), sql, params);
		return rows;
	}
	@Override
	public List<OrderInfo> getSumOrderByDay(String date) {
		List list=null;
		String sql="select m.name menuName,m.price1,o.menusum menuSum from orders o,menus m where o.times like ? and o.menuid=m.id";
		Object[] params={date+"%"};
		try {
			list=dbutil.getQueryList(OrderInfo.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Page getOrderByPage(Page page,OrderCriteria criteria) {
		StringBuffer sql=new StringBuffer();
		sql.append("select o.id orderId,u.id  userId,u.realname realName,u.phone,u.address,m.name menuName,o.menusum menuSum,m.price1,o.times,o.delivery")
		.append(" from orders o").append(" INNER JOIN users u on o.userid =u.id")
		.append(" INNER JOIN menus m on o.menuid=m.id where 1=1");
		List<Object> params=new ArrayList<Object>();
		if(criteria!=null){
			//有查询条件
			int userId=criteria.getUserId();
			if(userId>0){
				sql.append(" and u.id=?");
				params.add(userId);
			}
			String menuName=criteria.getMenuName();
			if(menuName!=null&&!"".equals(menuName.trim())){
				sql.append(" and m.name like ?");
				params.add("%"+menuName+"%");
			}
			String date=criteria.getDate();
			if(date!=null&&!"".equals(date.trim())){
				sql.append(" and o.times like ?");
				params.add("%"+date+"%");
			}
			int delivery=criteria.getDelivery();
			if(delivery!=-1){
				sql.append(" and o.delivery=?");
				params.add(delivery);
			}
			//根据查询条件的内容动态生成sql						
		}
		sql.append(" order by o.times desc");
		try {
			page=dbutil.getQueryPage(OrderInfo.class, sql.toString(), params.toArray(), page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@Override
	public int deleteOrderById(int orderId) {
		int i=0;
		String sql="delete from orders where id=?";
		Object[] params={orderId};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int updateOrderById(int orderId) {
		int i=0;
		String sql="update orders set delivery='1' where id=?";
		Object[] params={orderId};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public List<OrderInfo> getSaleRank() {
		List list=null;
		String sql="select m.name menuName,ROUND(Sum(menusum)) menuSum from orders o,menus m where o.menuid=m.id group by o.menuid order by menuSum desc";
		try {
			list=dbutil.getQueryList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

}
