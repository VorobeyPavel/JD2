<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Registration form</h1>
	<br />
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="REGISTRATION_NEW_USER" /> 
		Enter login:<br />
		<input type="text" name="login" value="" /><br /> 
		Enter password:<br />
		<input type="password" name="password" value="" /><br /> 		
		<input type="submit" value="Отправить" /><br />
	</form>
</body>
</html>