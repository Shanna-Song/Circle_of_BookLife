<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>我的订单</title>
<meta content="" name=keywords />
<meta content="" name=description />
<link href="${pageContext.request.contextPath}/qiantai/css/skin.css" rel="stylesheet" type="text/css" /> 
<script src="js/date.js" type="text/javascript"></script>
</head>
<body style='background: transparent'>
	<table width="900" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="left" valign="top"><jsp:include flush="fasle"
					page="top.jsp" /></td>
		</tr>
		<tr>
			<td height="50px"></td>

		</tr>

		<tr>
			<td align="center" valign="top" height="420px">

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="left" valign="top">

							<div align="center" width="120">
								<form action="${pageContext.request.contextPath}/UserOrderServlet?action=getUserOrderByCriteria&userId=${user.id}" name="form1" method="post">
									<table id="table1" class="line_table"
										style="width: 500px; margin: 0; padding: 0" cellSpacing="0"
										cellPadding="0">
										<tbody style="margin: 0; padding: 0">
											<tr>
												<td class="line_table" align="right" width="40%"><span
													class="left_bt2">按书籍名称查询</span></td>
												<td class="line_table" align="left" width="60%"><input
													type="text" name="menuname" size="20">
											<tr>
												<td class="line_table" align="right" width="40%"><span
													class="left_bt2">按购买日期查询</span></td>
												<td class="line_table" align="left" width="60%"><input
													type="text" name="date" size="20" 
													onClick="setDay(this);">
											</tr>
											<tr>
												<td class="line_table" align="right" width="40%"><span
													class="left_bt2">是否已派送(0,1)</span></td>
												<td class="line_table" align="left" width="60%"><input
													type="text" name="delivery" size="20">
											</tr>
											<tr>
												<td colspan="2" align="center"><input type="submit" value="查询"></td>
											</tr>
											
									</table>
								</form>
							</div>
						</td>
					</tr>
					<tr>
						<td align="left" valign="top" height="20px"></td>
					</tr>
					<tr>
						<td align="left" valign="top">


							<div align="center">
								<table id="table2" class="line_table"
									style="width: 900px; margin: 0; padding: 0" cellSpacing="0"
									cellPadding="0">
									<tbody style="margin: 0; padding: 0">
										<tr>
											<td class="line_table" align="center" colspan="9"><span
												class="left_bt2">订单查询结果</span></td>
										</tr>
										
										<tr>
											<td class="line_table" align="center"><span
												class="left_bt2">书籍名称</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">姓名</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">订购电话</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">地址</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">订购数量</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">单价(元)</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">合计(元)</span></td>
											<td class="line_table" align="center"><span
												class="left_bt2">订购时间</span></td>
											
										</tr>
										<c:if test="${list!=null}">
										<c:forEach items="${list}" var="orderInfo">
										<tr>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.menuName}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.realName}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.phone}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.address}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.menuSum}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.price1}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.menuSum*orderInfo.price1}</span></td>
											<td class="line_table" align="center"><span
												class="left_txt">${orderInfo.times}</span></td>
											
										</tr>
										</c:forEach>
										</c:if>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="10px">&nbsp;</td>
		</tr>
		<tr>
			<td height="50px" align="center" valign="middle"><jsp:include
					flush="fasle" page="copyright.jsp" /></td>
		</tr>
	</table>
</body>
</html>
