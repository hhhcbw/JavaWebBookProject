<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

<%-- 静态包含 base标签，css样式，jQuery文件 --%>
<%@ include file="/pages/common/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

			<%-- 静态包含登录成功之后的菜单 --%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${sessionScope.myOrders}" var="myOrder">
				<tr>
					<td>${myOrder.createTime}</td>
					<td>${myOrder.price}</td>
					<c:choose>
						<c:when test="${myOrder.status==0}">
							<td>未发货</td>
						</c:when>
						<c:when test="${myOrder.status==1}">
							<td><a href="orderServlet?action=receiverOrder&orderId=${myOrder.orderId}&createTime=${myOrder.createTime}
							&price=${myOrder.price}&status=${myOrder.status}&userId=${myOrder.userId}">已发货(点击签收)</a></td>
						</c:when>
						<c:when test="${myOrder.status==2}">
							<td>已签收</td>
						</c:when>
					</c:choose>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
		
	
	</div>

	<%-- include包含脚部信息 --%>
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>