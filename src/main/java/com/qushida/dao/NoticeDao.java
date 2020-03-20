package com.qushida.dao;

import com.qushida.po.Notice;

import java.util.List;

//import java.util.Date;

public interface NoticeDao {
	public List<Notice> getAllNotice();
	public int add(String name,String content);
	public int deleteNotice(int id);
	public Notice selectNoticeById(int id);
}
