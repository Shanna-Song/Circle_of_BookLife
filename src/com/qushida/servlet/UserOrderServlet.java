package com.qushida.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qushida.po.OrderCriteria;
import com.qushida.po.ShoppingCar;
import com.qushida.po.User;
import com.qushida.service.OrderService;
import com.qushida.service.UserService;
import com.qushida.serviceImpl.OrderServiceImpl;
import com.qushida.serviceImpl.UserServiceImpl;
import com.qushida.vo.OrderInfo;
import com.sun.beans.editors.IntegerEditor;

public class UserOrderServlet extends HttpServlet {
	private OrderService orderService=new OrderServiceImpl();
	private UserService userService=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//获取动作
		String action=request.getParameter("action");
		switch(action){
			case "addOrder":
				addOrder(request, response);
				break;
			case "getUserOrderByCriteria":
				getUserOrderByCriteria(request, response);
				break;
			default:
				break;
		}
	}
	public void getUserOrderByCriteria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取查询信息
		User user=(User)request.getSession().getAttribute("user");
		if(user!=null) {
			int userId=user.getId();
			String menuName=request.getParameter("menuname");
			String date=request.getParameter("date");
			int delivery=-1;
			try {
				delivery=Integer.parseInt(request.getParameter("delivery"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//构建Criteria对象
			OrderCriteria criteria=new OrderCriteria(userId,menuName,date,delivery);
			//调用OrderService->getOrderByPage(Page)
			List<OrderInfo> list=userService.getUserOrderByCriteria(criteria);
			request.setAttribute("list", list);
			//请求转发到admin/order.jsp
			request.getRequestDispatcher("/qiantai/order.jsp").forward(request, response);
		}else {
			response.getWriter().write("<script>window.parent.location.href='qiantai/login.jsp'</script>");
		}
		
		
	}
	public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		//获取session
		HttpSession session=request.getSession();
		//用户是否登录
		User user=(User)session.getAttribute("user");
		if(user==null){//未登录
			response.getWriter().write("<script>alert('请登录后下单');window.location.href='qiantai/login.jsp'</script>");
		}else{//已登录
			//1.从session中获取购物车列表
			List<ShoppingCar> carList= (List<ShoppingCar>)session.getAttribute("carList");
			//2.获取当前登录的id
			int userId=user.getId();			
			boolean result=orderService.addOrder(carList, String.valueOf(userId));
			if(result){
				session.removeAttribute("carList");
				out.write("<script>alert('下单成功');window.location.href='qiantai/index.jsp'</script>");
			}else{
				out.write("<script>alert('下单失败');window.location.href='qiantai/index.jsp'</script>");
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
