<%@ page isELIgnored="false"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>张梦然的主页</title>
</head>
<body>
	${time}
	<br /> ${name}
	<br /> ${pageContext['request'].contextPath}
	<table>
		<tr>
			<th>用ID</th>
			<th>用户名</th>
			<th>密码</th>
			<th>邮箱</th>
		</tr>
		<c:forEach items="${persons}" var="task">
			<tr>
				<td>${task.user_id }</td>
				<td>${task.user_name }</td>
				<td>${task.pwd }</td>
				<td>${task.email }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>