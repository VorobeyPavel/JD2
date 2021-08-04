<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<br><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Authorization form</h1>
	<br />
	
	<%
	String mes = (String)request.getParameter("message");
	if(mes !=null){
		out.print(mes);
	}
	%>
	
	<form action="Controller" method="get">
		<input type="hidden" name="command" value="forward" /> 
		Enter login:<br />
		<input type="text" name="login" value="" /><br /> 
		Enter password:<br />
		<input type="password" name="password" value="" /><br /> 		
		<input type="submit" value="Отправить" />
	</form>
</body>
</html>