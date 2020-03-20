<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="admin/images/skin.css" rel="stylesheet" type="text/css" />
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
								<td class="line_table" align="center" colspan="8" height="20">
									<span class="left_bt2">本日销售额统计</span>
								</td>
							</tr>
							<tr>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">书籍名称</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">订购数量</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">单价</span></td>
								<td class="line_table" align="center" width="25%"><span
									class="left_bt2">合计</span></td>
							</tr>
							<c:if test="${list!=null}">
								<c:forEach items="${list}" var="orderInfo">
								<tr>
									<td class="line_table" align="center" width="25%"><span
										class="left_txt">${orderInfo.menuName}</span></td>
									<td class="line_table" align="center" width="25%"><span
										class="left_txt">${orderInfo.menuSum}</span></td>
									<td class="line_table" align="center" width="25%"><span
										class="left_txt">${orderInfo.price1}</span></td>
									<td class="line_table" align="center" width="25%"><span
										class="left_txt">${orderInfo.menuSum*orderInfo.price1}元</span></td>
								</tr>
								<c:set var="totalMoney" value="${orderInfo.menuSum*orderInfo.price1+totalMoney}"></c:set>	
								</c:forEach>
							</c:if>						
							<tr>
								<td class="line_table" align="center" colspan="8"><span
									class="left_bt2">本日销售总额：${totalMoney}元
								</span></td>
							</tr>
							
					</table>
				</div>

			</td>

		</tr>
	</table>
</body>
</html>
