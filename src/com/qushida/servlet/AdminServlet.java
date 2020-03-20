package com.qushida.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qushida.po.Admin;
import com.qushida.service.AdminService;
import com.qushida.serviceImpl.AdminServiceImpl;


/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private AdminService adminService=new AdminServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取前台请求类型
		String action=request.getParameter("action");
		switch(action){
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "updateAdmin":
			updateAdmin(request, response);
			break;	
		case "getAdminById":
			getAdminById(request, response);
			break;
		default:
			break;
		}
	
	}
	public void getAdminById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin=new Admin();
		admin=(Admin)request.getSession().getAttribute("admin");
		int id=admin.getId();
		Admin admin1=adminService.getAdminById(id);
		request.setAttribute("admin", admin1);
		//转发到更新页面
		request.getRequestDispatcher("admin/admin_update.jsp").forward(request, response);		
	}
	public void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		int id1=Integer.parseInt(id);
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		//把获取的数据放到对象里面
		Admin admin=new Admin(id1,"0",name,pwd);
		int i=adminService.updateAdmin(admin);
		if(i>0){
			String updatemsg="<script>alert('修改成功')</script>";
			request.setAttribute("updatemsg", updatemsg); 
			response.getWriter().write("<script>window.parent.location.href='admin/index.jsp'</script>");
		}else{
			String updatemsg="<script>alert('修改失败')</script>";
			request.setAttribute("updatemsg", updatemsg);
			response.sendRedirect("admin/admin_update.jsp");
		}
		
	}
	//退出
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//移除session域中存放的当前用户的信息
		//获取session
		request.getSession().removeAttribute("admin");//链式编程
		//跳转到登陆页
		response.getWriter().write("<script>window.parent.location.href='admin/index.jsp'</script>");
	}
	//登陆
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//获取用户名密码
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		//调用业务
		AdminService adminService =new AdminServiceImpl();
		Admin admin=adminService.login(name, pwd);
		//对返回值进行判断,若不空，存在该管理员，登陆成功
		if(admin!=null){
			
			
			//把当前登陆的管理员放到session中
			HttpSession session=request.getSession();
			session.setAttribute("admin", admin);
			//重定向到登陆成功后的主页面
			try {
				//request.getRequestDispatcher("admin/admin_left.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/admin/main.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			
			//登录失败
			response.getWriter().write("<script>alert('用户名或密码错误');window.location.href='admin/index.jsp'</script>");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应编码格式
		response.setContentType("text/html;charset=utf-8");
		doGet(request, response);
	}

}
