package com.qushida.dao;

import com.qushida.po.Notice;

//import java.util.Date;
import java.util.List;

public interface NoticeDao {
	public List<Notice> getAllNotice();
	public int add(String name,String content);
	public int deleteNotice(int id);
	public Notice selectNoticeById(int id);
}
