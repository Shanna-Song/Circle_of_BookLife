package com.qushida.dao.impl;

import com.qushida.dao.UserDao;
import com.qushida.po.OrderCriteria;
import com.qushida.po.User;
import com.qushida.util.DBUtil;
import com.qushida.vo.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	//创建工具类DBUTil对象
	DBUtil dbutil=new DBUtil();
	@Override
	public User login(String name, String pwd) {
		User user= new User();
		String sql="select * from users where name=? and pwd=?";
		//2.组织参数
		Object[] params={name,pwd};
		
		try {
			//4.调用dbutil方法
			user= (User)dbutil.getObject(User.class,sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User getUserById(int id) {
		User user=new User();
		String sql="select * from users where id=?";
		Object[] params={id};
		try {
			user=(User)dbutil.getObject(User.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<OrderInfo> getUserOrderByCriteria(OrderCriteria criteria) {
		List<OrderInfo> list=new ArrayList<OrderInfo>();
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
			list=(List)dbutil.getQueryList(OrderInfo.class, sql.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int register(User user) {
		int i=0;
		String sql="insert into users(name,pwd,realname,sex,age,card,address,phone,email,code) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params={user.getName(),user.getPwd(),user.getRealname(),user.getSex(),user.getAge(),user.getCard(),user.getAddress(),user.getPhone(),user.getEmail(),user.getCode()};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int updateUser(User user) {
		int i=0;
		String sql="update users set name=?,pwd=?,realname=?,sex=?,age=?,card=?,address=?,phone=?,email=?,code=? where id=?";
		Object[] params={user.getName(),user.getPwd(),user.getRealname(),user.getSex(),user.getAge(),user.getCard(),user.getAddress(),user.getPhone(),user.getEmail(),user.getCode(),user.getId()};
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
