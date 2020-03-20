package com.qushida.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.qushida.po.Menus;
import com.qushida.po.Type;
import com.qushida.service.MenusService;
import com.qushida.service.TypeService;
import com.qushida.serviceImpl.MenusServiceImpl;
import com.qushida.serviceImpl.TypeServiceImpl;
import com.qushida.util.Page;

public class MenusServlet extends HttpServlet {
	//创建service对象
	private MenusService menusService=new MenusServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应编码格式
		response.setContentType("text/html;charset=utf-8");
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//获取动作
		String action=request.getParameter("action");
		switch(action){
		case "getMenusByPage":
			getMenusByPage(request, response);
			break;	
		case "updateMenus":
			updateMenus(request, response);
			break;	
		case "addMenu":
			addMenu(request, response);
			break;
		case "deleteMenus":
			deleteMenus(request, response);
			break;
		case "selectById":
			selectById(request, response);
			break;
		}
	}
	public void  selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		TypeService typeService=new TypeServiceImpl();
		//获取待修改的Id
		String id=request.getParameter("id");
		int id1=Integer.parseInt(id);//类型转换
		//调用service曾的查询
		Menus menus=menusService.selectById(id1);
		List<Type> list =typeService.getAllType();
		//把查询到的类别列表放到request域里面
		request.setAttribute("typelist", list);
		//把Type放到request域
		request.setAttribute("menus", menus);
		//转发到更新页面
		request.getRequestDispatcher("admin/menus_update.jsp").forward(request, response);
		
	}
	public void  deleteMenus(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		int id1=Integer.parseInt(id);
		int i=menusService.deleteMenu(id1);
		if(i>0){
			String deletemsg="<script>alert('删除成功')</script>";
			request.setAttribute("deletemsg", deletemsg); 
			try {
				getMenusByPage(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			String deletemsg="<script>alert('删除失败')</script>";
			request.setAttribute("deletemsg", deletemsg); 
			try {
				getMenusByPage(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void  addMenu(HttpServletRequest request, HttpServletResponse response){
		//借助SmartUpload工具类完成图片上传
		//1创建SmartUpload对象
		SmartUpload smartUpload=new SmartUpload();
		//2使用初始化方法
		try {
			smartUpload.initialize(getServletConfig(),request,response);
			//3smartUpload上传准备
			smartUpload.upload();
			//4得到文件并获取上传文件名
			SmartFile file=smartUpload.getFiles().getFile(0);
			String fileName=file.getFileName();
			String imgpath="img/"+fileName;
			//获取其他信息
			String name=smartUpload.getRequest().getParameter("name");
			String typeid=smartUpload.getRequest().getParameter("typeid");
			String burden=smartUpload.getRequest().getParameter("burden");
			String brief=smartUpload.getRequest().getParameter("brief");
			String price=smartUpload.getRequest().getParameter("price");
			String price1=smartUpload.getRequest().getParameter("price1");
			//封装到对象里面
			Menus menus=new Menus(0, name, typeid, burden, brief, price, null, price1, null, imgpath);
			//调用service层方法
			int i=menusService.addMenu(menus);			
			if(i>0){
				//将文件保存到指定的目录下
				smartUpload.save("/img");
				String addmsg="<script>alert('添加成功')</script>";
				request.setAttribute("addmsg", addmsg); 
				getMenusByPage(request, response);
			}else{
				response.getWriter().write("<script>alert('添加失败');windows.location.href='TypeServlet?action=forMenuAdd'</script>");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void  updateMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Menus menus=new Menus();
		String id=request.getParameter("menuId");
		int id1=Integer.parseInt(id);
		menus.setId(id1);
		menus.setTypeid(request.getParameter("typeid"));
		menus.setName(request.getParameter("name"));
		menus.setBurden(request.getParameter("burden"));
		menus.setBrief(request.getParameter("brief"));
		menus.setPrice(request.getParameter("price"));
		menus.setPrice1(request.getParameter("price1"));
		menus.setSums("0");
		menus.setSums1("0");
		menus.setImgpath(null);
		int i=menusService.update(menus);
		if(i>0){
			String updatemsg="<script>alert('修改成功')</script>";
			request.setAttribute("updatemsg", updatemsg); 
			getMenusByPage(request, response);
		}else{
			String updatemsg="<script>alert('修改失败')</script>";
			request.setAttribute("updatemsg", updatemsg); 
			getMenusByPage(request, response);
		}
	}
	public void  getMenusByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//声明分页所用的变量
		int curPage1=0;
		int pageNumber1=0;
		//获取分页信息
		String curPage=request.getParameter("curPage");//当前页
		String pageNumber=request.getParameter("pageNumber");//每页容量
		//curPage为Null为第一页，反之为传过来的
		if(curPage==null){
			curPage1=1;
		}else{
			curPage1=Integer.parseInt(curPage);
		}
		if(pageNumber==null){
			pageNumber1=3;
		}else{
			pageNumber1=Integer.parseInt(pageNumber);
		}
		//将当前页和每页条数封装到page对象
		Page page=new Page();
		page.setCurPage(curPage1);
		page.setPageNumber(pageNumber1);
		
		//调用方法
		Page pageCurrent=menusService.getMenusByPage(page);
		//吧从数据库查到的数据放到域中
		request.setAttribute("pageCurrent", pageCurrent);
		//转发到menus.jsp
		request.getRequestDispatcher("admin/menus.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
