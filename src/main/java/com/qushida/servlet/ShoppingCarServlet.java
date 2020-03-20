package com.qushida.servlet;

import com.qushida.po.Menus;
import com.qushida.po.ShoppingCar;
import com.qushida.service.MenusService;
import com.qushida.serviceImpl.MenusServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCarServlet extends HttpServlet {
	private MenusService menusService=new MenusServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取action的值
		String action=request.getParameter("action");
		switch(action){
		case "add":
			add(request, response);
			break;
		case "removeAll":
			removeAll(request, response);
			break;
		case "removeOne":
			removeOne(request, response);
			break;
		default:
			break;
		}
	
	}
	//添加购物车
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//数据存到session中（数据是什庅格式）
		HttpSession session=request.getSession();
		List<ShoppingCar> carList=(List<ShoppingCar>)session.getAttribute("carList");
		//设计类
		
		//得到单击的菜品信息->构建成shopingCar的对象（menuId名称，价格，数量）
		int menuId=Integer.parseInt(request.getParameter("menuId"));
		boolean flag=true;
		if(carList==null){
			carList=new ArrayList<>();
			
		}else{
			//遍历购物车，得到购物车的每个条目，如果有id相等，数量加一
			for(ShoppingCar caritem:carList){
				if(caritem.getMenuId()==menuId){
					caritem.setSums(caritem.getSums()+1);
					flag=false;
					break;
				}
			}
			
		}
		if(flag){
			Menus menus=menusService.selectById(menuId);
			String name=menus.getName();
			String price=menus.getPrice1();
			ShoppingCar shoppingCar=new ShoppingCar(menuId,name,Float.parseFloat(price),1);
			//从session中获取购物车信息
			carList.add(shoppingCar);
			}
		session.setAttribute("carList", carList);
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);
		}
	//删除一条信息
	public void removeOne(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//获取来源
		String from=request.getParameter("from");
		String path=request.getContextPath()+"/qiantai/index.jsp";//重定向的路径
		if(from!=null){
			path=request.getContextPath()+"/qiantai/shoppingcar.jsp";
		}
		//得到要删除的商品的id
		int id=Integer.parseInt(request.getParameter("id"));
		//获取session
		HttpSession session=request.getSession();
		//session中获取购物车信息
		List<ShoppingCar> carList=(List<ShoppingCar>)session.getAttribute("carList");
		//遍历购物车信息，找到需删除的条目（id），删除
		for(ShoppingCar carItem:carList){
			//获取carItem的id
			if(id==carItem.getMenuId()){//就是删这一条
				carList.remove(carItem);
				break;
			}
		}
		
		response.sendRedirect(path);
		//将新的购物车设置到session域中(不用)
	}
	//清空购物车
	public void removeAll(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//移除session中的shoppingCar
		HttpSession session=request.getSession();
		session.removeAttribute("carList");
		//重定向到前台下index.jsp页面
		response.sendRedirect(request.getContextPath()+"/qiantai/index.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
