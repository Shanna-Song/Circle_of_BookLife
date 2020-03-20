package com.qushida.servlet;

import com.qushida.po.OrderCriteria;
import com.qushida.service.OrderService;
import com.qushida.serviceImpl.OrderServiceImpl;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminOrderServlet extends HttpServlet {
	//注入属性
	private OrderService orderService=new OrderServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		//调用名称是action的方法且参数是HttpServletRequest，HttpServletResponse的方法
		try {
			Method method=getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sumOrderByDay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = simpleDateFormat.format(date);		
		List<OrderInfo> list =orderService.getSumOrderByDay(time);
		//把查询到的类别列表放到request域里面
		request.setAttribute("list", list);
		//转发到显示类别的页面type.jsp
		request.getRequestDispatcher("admin/order_statistic.jsp").forward(request, response);
	}
	//修改订单
	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId=request.getParameter("orderId");
		boolean updateRes=orderService.updateOrderById(Integer.parseInt(orderId));
		String updateMsg="<script>alert('配送失败')</script>";
		if(updateRes){//配送成功
			updateMsg="<script>alert('配送成功')</script>";			
		}
		//设置到request域中
		request.setAttribute("updateMsg", updateMsg);
		getAllOrderByPage(request, response);
	}
	//删除订单
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId=request.getParameter("orderId");
		boolean deleteRes=orderService.deleteOrderById(Integer.parseInt(orderId));
		String deleteMsg="<script>alert('取消订单失败')</script>";
		if(deleteRes){//配送成功
			deleteMsg="<script>alert('取消订单成功')</script>";			
		}
		//设置到request域中
		request.setAttribute("deleteMsg", deleteMsg);
		getAllOrderByPage(request, response);
		
	}
	//分页查询所有订单
	public void getAllOrderByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//构建page(curPage,pageNumber)
		String curPage=request.getParameter("curPage");
		if(curPage==null||"".equals("curPage")){
			curPage="1";
		}
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null||"".equals("pageNumber")){
			pageNumber="3";
		}
		Page page =new Page();
		page.setCurPage(Integer.parseInt(curPage));
		page.setPageNumber(Integer.parseInt(pageNumber));
		//调用OrderService->getOrderByPage(Page)
		Page pageCurrent=orderService.getOrderByPage(page,null);
		request.setAttribute("pageCurrent", pageCurrent);
		//请求转发到admin/order.jsp
		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}
	
	public void getOrderByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取查询信息
		String userId=request.getParameter("userid");
		String menuName=request.getParameter("menuname");
		String date=request.getParameter("date");
		//构建Criteria对象
		OrderCriteria criteria=new OrderCriteria();
		if(userId!=null&&!"".equals(userId.trim())){
			criteria.setUserId(Integer.parseInt(userId));
		}
		criteria.setMenuName(menuName);
		criteria.setDate(date);
		//构建page(curPage,pageNumber)
		String curPage=request.getParameter("curPage");
		if(curPage==null||"".equals("curPage")){
			curPage="1";
		}
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null||"".equals("pageNumber")){
			pageNumber="3";
		}
		Page page =new Page();
		page.setCurPage(Integer.parseInt(curPage));
		page.setPageNumber(Integer.parseInt(pageNumber));
		//调用OrderService->getOrderByPage(Page)
		Page pageCurrent=orderService.getOrderByPage(page,criteria);
		request.setAttribute("pageCurrent", pageCurrent);
		request.setAttribute("criteria", criteria);
		//请求转发到admin/order.jsp
		request.getRequestDispatcher("/admin/order_search.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
