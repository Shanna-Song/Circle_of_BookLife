<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link href="images/common.css" rel="stylesheet" type="text/css" /> -->
<link href="${pageContext.request.contextPath}/admin/images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
<!--确认删除-->
<script type="text/javascript">
	function deleteMenu(id){
		if(confirm("确定要删除吗？")){
			window.location.href="MenusServlet?action=deleteMenus&id="+id;
		}
	}

</script>
</head>
<body>
	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">
				<div align="center">
					<table id="table2" class="line_table"
						style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
						cellPadding="0">
						<tbody style="margin: 0; padding: 0">
							<tr>
								<td class="line_table" align="center" colspan="11" height="20"><span
									class="left_bt2">书籍信息列表</span></td>
							</tr>
							<tr>
								<td class="line_table" align="center"><span
									class="left_bt2">书籍名称</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">展示图片</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">磨损程度</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">类型</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">目录梗概</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">ISBN</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">单价</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">&nbsp;</span></td>
								<td class="line_table" align="center"><span
									class="left_bt2">&nbsp;</span></td>
							</tr>
							
							<c:if test="${pageCurrent.data!=null}">
								<c:forEach items="${pageCurrent.data}" var="menusinfo">
							<tr>
								<td class="line_table" align="center"><a
									href="menus_update.jsp?">${menusinfo.name}</a></td>
								<td class="line_table" align="center"><a
									href="../img/m_tangcupaigu.gif"><img src="${pageContext.request.contextPath}/${menusinfo.imgpath}"
										width="30" height="30"></a></td>
								<td class="line_table" align="center"><span
									class="left_txt">${menusinfo.burden}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${menusinfo.typename}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${menusinfo.brief}</span></td>
								<td class="line_table" align="center"><span
									class="left_txt">${menusinfo.price}</span></td>
								<!--  <td class="line_table" align="center"><span
									class="left_txt">${menusinfo.sums}</span></td>-->
								<td class="line_table" align="center"><span
									class="left_txt">${menusinfo.price1}</span></td>
								<!--<td class="line_table" align="center"><span
									class="left_txt">${menusinfo.sums1}</span></td>-->
								<td class="line_table" align="center"><a
									href="${pageContext.request.contextPath}/MenusServlet?action=selectById&id=${menusinfo.id}">修改</a></td>
								<td class="line_table" align="center"><a
									href="#" onclick="deleteMenu(${menusinfo.id})">删除</a></td>
							</tr>
							</c:forEach>
							</c:if>	
							
							
							<tr>
								<td class="line_table" align="center" colspan="11" height="20">
								<span class="left_bt2">第${pageCurrent.curPage}页
										&nbsp;&nbsp;共${pageCurrent.totalPage}页
								</span>&nbsp;&nbsp; 
								    <a href="${pageContext.request.contextPath}/MenusServlet?action=getMenusByPage&curPage=1">[首页]</a>
								    <a href="${pageContext.request.contextPath}/MenusServlet?action=getMenusByPage&curPage=${pageCurrent.totalPage}">[尾页]</a>&nbsp;&nbsp; 
								    <!-- 判断，如果当前页为第一页，curPage=1；不是，curPage-1 -->
								    <c:if test="${pageCurrent.curPage!=1}">
								    	<a href="${pageContext.request.contextPath}/MenusServlet?action=getMenusByPage&curPage=${pageCurrent.curPage-1}">[上一页]</a>
									</c:if>
									<c:if test="${pageCurrent.curPage==1}">
								    	<a href="javascript:void(0)">[上一页]</a>
									</c:if>
									<c:if test="${pageCurrent.curPage!=pageCurrent.totalPage}">
									<a href="${pageContext.request.contextPath}/MenusServlet?action=getMenusByPage&curPage=${pageCurrent.curPage+1}">[下一页]</a>
									</c:if>
									<c:if test="${pageCurrent.curPage==pageCurrent.totalPage}">
									<a href="#">[下一页]</a>
									</c:if>
								</td>
							</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
${updatemsg}
${addmsg}
${deletemsg}
</html>
