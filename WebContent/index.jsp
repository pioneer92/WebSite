<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/WebSite/ErrorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<a></a>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>home</title>
</head>
<body>
	<a href="/WebSite/Login">Login</a>
	<form action="/WebSite/Login" method="get">
		用户名：<input type="text" name="text"><br> 密码：<input
			type="password" name="password"><br> <input
			type="submit" value="submit">
	</form>
</body>

</html>