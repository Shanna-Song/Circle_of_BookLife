package com.qushida.servlet;

import com.qushida.po.Notice;
import com.qushida.service.MenusService;
import com.qushida.service.NoticeService;
import com.qushida.service.OrderService;
import com.qushida.serviceImpl.MenusServiceImpl;
import com.qushida.serviceImpl.NoticeServiceImpl;
import com.qushida.serviceImpl.OrderServiceImpl;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {
	private MenusService menusService=new MenusServiceImpl();
	private NoticeService noticeService=new NoticeServiceImpl();
	private OrderService orderService=new OrderServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应类型
		response.setContentType("text/html;charset=utf-8");
		//分页查询所有菜品(调用menusService中getMenuByPage(Page page)),构建Page参数（两个curPage,pageNum）
		String curPage=request.getParameter("pageIndex");
		if(curPage==null||"".equals(curPage)){
			curPage="1";
		}
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null||"".equals(pageNumber)){
			pageNumber="4";
		}
		Page page=new Page();
		page.setCurPage(Integer.parseInt(curPage));
		page.setPageNumber(Integer.parseInt(pageNumber));
		page=menusService.getMenusByPage(page);
		//查询所有公告(getAllNotice())
		List<Notice> notices=noticeService.getAllNotice();
		//查询销售排行
		List<OrderInfo> orderInfos=orderService.getSaleRank();
		//将以上数据放到request中
		request.setAttribute("saleList",orderInfos);
		request.setAttribute("notices", notices);
		request.setAttribute("page", page);
		//请求转发到qiantai/index.jsp
		request.getRequestDispatcher("/qiantai/index.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
