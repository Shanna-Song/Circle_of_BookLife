package com.qushida.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qushida.po.Type;
import com.qushida.service.TypeService;
import com.qushida.serviceImpl.TypeServiceImpl;

public class TypeServlet extends HttpServlet {
	
	//调用业务层方法、
	private TypeService typeService=new TypeServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应编码格式
		response.setContentType("text/html;charset=utf-8");
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		
		//获取动作
		String action=request.getParameter("action");
		switch(action){
		case "getAllType":
			getAllType(request, response);
			break;
		case "addType":
			addType(request, response);
			break;
		case "selectById":
			selectById(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "deleteType":
			deleteType(request, response);
			break;
		case "forMenusAdd":
		forMenusAdd(request, response);
		break;	
		default:
			break;
		}		
	}
	//查询所有类别供添加菜单使用
	public void  forMenusAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		List<Type> typelist=typeService.getAllType();
		//转发到菜单添加页面
		request.setAttribute("typelist", typelist); 
		request.getRequestDispatcher("admin/menus_add.jsp").forward(request,response);
	}
	public void  deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		int id1=Integer.parseInt(id);
		int i=typeService.deleteType(id1);
		if(i>0){
			String deletemsg="<script>alert('删除成功')</script>";
			request.setAttribute("deletemsg", deletemsg); 
			getAllType(request, response);
		}else{
			String deletemsg="<script>alert('删除失败')</script>";
			request.setAttribute("deletemsg", deletemsg); 
			getAllType(request, response);
		}
	}
	public void  update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		int id1=Integer.parseInt(id);
		String name=request.getParameter("name");
		//把获取的数据放到对象里面
		Type type=new Type(id1,name);
		int i=typeService.update(type);
		if(i>0){
			String updatemsg="<script>alert('修改成功')</script>";
			request.setAttribute("updatemsg", updatemsg); 
			getAllType(request, response);
		}else{
			String updatemsg="<script>alert('修改失败')</script>";
			request.setAttribute("updatemsg", updatemsg); 
			getAllType(request, response);
		}
	}
	public void  selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//获取待修改的Id
		String id=request.getParameter("id");
		int id1=Integer.parseInt(id);//类型转换
		//调用service曾的查询
		Type type=typeService.selectById(id1);
		//把Type放到request域
		request.setAttribute("type", type);
		//转发到更新页面
		request.getRequestDispatcher("admin/type_update.jsp").forward(request, response);
	}
	
	public void  getAllType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		List<Type> list =typeService.getAllType();
		//把查询到的类别列表放到request域里面
		request.setAttribute("typelist", list);
		//转发到显示类别的页面type.jsp
		request.getRequestDispatcher("admin/type.jsp").forward(request, response);
	}
	public void  addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//获取页面的类别名称
		String name=request.getParameter("name");
		System.out.println("name="+name);
		int i=typeService.addType(name);
		if(i>0){//添加成功
			String addmsg="<script>alert('添加成功')</script>";
			request.setAttribute("addmsg", addmsg);
			getAllType(request, response);
		}else{
			response.getWriter().write("<script>alert('添加失败，请重新添加');window.location.href='admin/notice_add.jsp'</script>");
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
