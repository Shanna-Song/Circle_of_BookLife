package com.qushida.serviceImpl;

//import java.util.Date;

import com.qushida.dao.NoticeDao;
import com.qushida.dao.impl.NoticeDaoImpl;
import com.qushida.po.Notice;
import com.qushida.service.NoticeService;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {
	NoticeDao noticedao=new NoticeDaoImpl();
	@Override
	public int add(String name, String content) {
		return noticedao.add(name,content);
	}
	@Override
	public List<Notice> getAllNotice() {
		return noticedao.getAllNotice();
	}
	@Override
	public int deleteNotice(int id) {		
		return noticedao.deleteNotice(id);
	}
	@Override
	public Notice selectNoticeById(int id) {
		return noticedao.selectNoticeById(id);
	}

}
