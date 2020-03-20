package com.qushida.dao.impl;

import com.qushida.dao.NoticeDao;
import com.qushida.po.Notice;
import com.qushida.util.DBUtil;

import java.util.List;

public class NoticeDaoImpl implements NoticeDao {
	//创建工具类的对象
	private DBUtil dbutil=new DBUtil();
	@Override
	public int add(String name, String content) {
		String sql="insert into notice(name,content,times) values(?,?,SYSDATE())";
		Object[] params={name,content};
		int i=0;
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public List<Notice> getAllNotice() {
		List list=null;
		String sql="select * from notice";
		try {
			list=dbutil.getQueryList(Notice.class, sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int deleteNotice(int id) {
		String sql="delete from notice where id=?";
		Object[] params={id};
		int i=0;
		try {
			i=dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public Notice selectNoticeById(int id) {
		Notice notice=null;
		String sql="select * from notice where id=?";
		Object[] params={id};
		try {
			notice=(Notice)dbutil.getObject(Notice.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notice;
	}

}
