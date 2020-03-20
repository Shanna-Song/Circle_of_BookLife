<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="js/verify.js"></script>
<script type="text/javascript">
	function verifyInfo() {
		//菜品名称
		if(!verifyNotNull(document.form1.name.value)) {
			alert("书籍名称不能为空!");
			return false;
		}
		//原料
		if(!verifyNotNull(document.form1.burden.value)) {
			alert("磨损程度不能为空");
			return false;
		}
		//市场价格
		/* debugger; */
		if(!verifyPositiveInt(document.form1.price.value)) {
			alert("ISBN必须为正整数");
			return false;
		}
		//会员价格
		if(!verifyPositiveInt(document.form1.price1.value)) {
			alert("书籍价格必须为正整数");
			return false;
		}
		//说明
		if(!verifyNotNull(document.form1.brief.value)) {
			alert("目录梗概不能为空");
			return false;
		}
	}
</script>
</head>
<body>

	<table width="100%" height="1" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="top" bgcolor="#F7F8F9">
				<p>修改书籍</p>
				<div align="center">
					<form action="${pageContext.request.contextPath}/MenusServlet?action=updateMenus&menuId=${menus.id}" method="post"
						name="form1" onSubmit="return verifyInfo()">
						<table id="table2" class="line_table"
							style="width: 100%; margin: 0; padding: 0" cellSpacing="0"
							cellPadding="0">

							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">书籍名称：</span></td>
								<td class="line_table" height="25" width="70%"><input
									type="text" name="name" size="45" value="${menus.name}"></td>
							</tr>

							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">磨损程度：</span></td>
								<td class="line_table" height="25" width="80%"><input
									type="text" name="burden" size="45"
									value="${menus.burden}"></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">ISBN：</span></td>
								<td height="25" width="80%"><input type="text" name="price"
									size="45" value="${menus.price}"></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">会员价格：</span></td>
								<td height="25" width="80%"><input type="text"
									name="price1" size="45" value="${menus.price1}"></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">目录梗概：</span></td>
								<td class="line_table" height="25" width="80%"><textarea
										rows="12" name="brief" cols="100">${menus.brief}</textarea></td>
							</tr>
							<tr>
								<td class="line_table" height="25" align="right" width="20%"><span
									class="left_bt2">书籍类别：</span></td>
								<td class="line_table" height="25" width="80%"><select
										name="typeid">
									<c:if test="${typelist!=null}">
									<c:forEach items="${typelist}" var="type">
											<option value="${type.id}">${type.name}</option>
									</c:forEach>
									</c:if>
								</select></td>
							</tr>
							<tr>
								<td class="line_table" align="right" width="20%"><span
									class="left_bt2">展示图片</span>：</td>
								<td class="line_table" width="80%" align="left"><img
									src="${menus.imgpath}"><input type="hidden" name="id" value="1"/></td>
							</tr>

							<tr>
								
								<td class="line_table" height="25" align="center" colspan="2">
									<input type="submit" value="修改">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</td>

		</tr>
	</table>


</body>
</html>
