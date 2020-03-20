package com.qushida.servlet;

import java.io.IOException;
//import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.qushida.po.Notice;
import com.qushida.po.Type;
import com.qushida.service.NoticeService;
import com.qushida.service.TypeService;
import com.qushida.serviceImpl.NoticeServiceImpl;
import com.qushida.serviceImpl.TypeServiceImpl;

public class NoticeServlet extends HttpServlet {
	//调用业务层方法
	private NoticeService noticeService=new NoticeServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应编码格式
		response.setContentType("text/html;charset=utf-8");
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//获取动作
		String action=request.getParameter("action");
		switch(action){
			case "getAllNotice":
				getAllNotice(request, response);
				break;
			case "add":
				add(request, response);
				break;
			case "deleteNotice":
				deleteNotice(request, response);
				break;
			case "selectNoticeById":
				selectNoticeById(request, response);
				break;	
			default:
				break;
		}
	}
	public void  selectNoticeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取待修改的Id
		String id=request.getParameter("noticeId");
		int id1=Integer.parseInt(id);//类型转换
		//调用service曾的查询
		Notice notice=noticeService.selectNoticeById(id1);
		//把Type放到request域
		request.setAttribute("notice", notice);
		//转发到更新页面
		request.getRequestDispatcher("qiantai/notice.jsp").forward(request, response);
	}
	public void  deleteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		int id1=Integer.parseInt(id);
		int i=noticeService.deleteNotice(id1);
		if(i>0){
			String deletemsg="<script>alert('删除成功')</script>";
			request.setAttribute("deletemsg", deletemsg); 
			getAllNotice(request, response);
		}else{
			String deletemsg="<script>alert('删除失败')</script>";
			request.setAttribute("deletemsg", deletemsg); 
			getAllNotice(request, response);
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取页面的类别名称
		String name=request.getParameter("name");
		String content=request.getParameter("content");
		//Date times=new Date();
		int i=noticeService.add(name,content);
		if(i>0){//添加成功
			String addmsg="<script>alert('添加成功')</script>";
			request.setAttribute("addmsg", addmsg);
			getAllNotice(request, response);
		}else{
			response.getWriter().write("<script>alert('添加失败，请重新添加');window.location.href='admin/notice_add.jsp'</script>");
		}
	}
	public void getAllNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		List<Notice> list =noticeService.getAllNotice();
		request.setAttribute("noticelist", list);
		request.getRequestDispatcher("admin/notice.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
