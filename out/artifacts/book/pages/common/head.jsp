<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/11/4
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String basePath = request.getScheme() //协议名称
            + "://"
            + request.getServerName() //服务器ip
            + ":"
            + request.getServerPort() //服务器端口
            + request.getContextPath() //工程路径
            + "/";
  pageContext.setAttribute("basePath", basePath);
%>
<!-- 写base标签，永远固定相对路径跳转的结果 -->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
