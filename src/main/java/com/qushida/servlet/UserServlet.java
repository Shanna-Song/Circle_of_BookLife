package com.qushida.servlet;

import com.qushida.po.User;
import com.qushida.service.UserService;
import com.qushida.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {
	private UserService userService =new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		switch(action){
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		case "register":
			register(request, response);
			break;	
		case "getUserInfo":
			getUserInfo(request, response);
			break;
		case "updateUserInfo":
			updateUserInfo(request, response);
			break;
		default:
			break;
		}
	}
	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//User user1=(User)request.getSession().getAttribute("user");
		//int id=user1.getId();
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String card=request.getParameter("card");
		String realname=request.getParameter("realname");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String code=request.getParameter("code");
		User user=new User(id,name,pwd,null,realname,sex,age,card,address,phone,email,code,null);
		int i=userService.updateUser(user);
		if(i>0){
			response.getWriter().write("<script>alert('修改成功');window.location.href='qiantai/login.jsp'</script>");			
		}else{	
			response.getWriter().write("<script>alert('修改失败');window.location.href='qiantai/index.jsp'</script>");
		}
		
	}
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user1=(User)request.getSession().getAttribute("user");
		//int id=user1.getId();
		//String id1=request.getParameter("id");
		if(user1!=null) {
			//int id=Integer.parseInt(id1);
			//User user=userService.getUserById(id);
			//request.setAttribute("userInfo",user);
			request.getRequestDispatcher("qiantai/center.jsp").forward(request, response);
		}else {
			response.getWriter().write("<script>window.parent.location.href='qiantai/login.jsp'</script>");
		}
	}
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String card=request.getParameter("card");
		String realname=request.getParameter("realname");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String code=request.getParameter("code");
		User user=new User(0,name,pwd,null,realname,sex,age,card,address,phone,email,code,null);
		int i=userService.register(user);
		if(i>0){
			response.getWriter().write("<script>alert('注册成功');window.location.href='qiantai/login.jsp'</script>");			
		}else{			
			response.getWriter().write("<script>alert('注册失败');window.location.href='qiantai/reg.jsp'</script>");
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//移除session域中存放的当前用户的信息
		//获取session
		request.getSession().removeAttribute("user");//链式编程
		//跳转到登陆页
		response.getWriter().write("<script>window.parent.location.href='qiantai/login.jsp'</script>");
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//设置编码格式
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取用户名密码
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		//调用业务
		UserService userService =new UserServiceImpl();
		User user=userService.login(name, pwd);
		//对返回值进行判断,若不空，存在该管理员，登陆成功
		if(user!=null){
			//把当前登陆的管理员放到session中
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			response.getWriter().write("<script>alert('登陆成功');window.location.href='qiantai/index.jsp'</script>");
			
		}else{
			
			//登录失败
			response.getWriter().write("<script>alert('登陆失败');window.location.href='qiantai/login.jsp'</script>");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
