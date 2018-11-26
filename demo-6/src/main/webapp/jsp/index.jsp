<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="/getList">
	<table border="5px solid" style="width:200px;">
		<tr>
			<td>用户名</td>
			<td>年龄</td>
			<td>电话</td>
			<td>头像</td>
		</tr>
		<c:forEach items="${userList}" var="x">
			<tr>
				<td>${x.name }</td>
				<td>${x.age }</td>
				<td>${x.phoneNumber }</td>
				<td>${x.url }</td>
				<td><a href="/delUser?age=${x.age}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<a href="addS1">添加用户</a>
</body>
</html>