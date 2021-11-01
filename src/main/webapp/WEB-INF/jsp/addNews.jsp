<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>Adding News</title>

<fmt:setLocale value="${sessionScope.local}" />

<fmt:setBundle basename="localization.local" var="loc" />
<%-- <fmt:setBundle basename="resources.localization.local" var="loc" /> --%>


<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

<fmt:message bundle="${loc}" key="local.addNews.adding.news"
	var="add_news" />
<fmt:message bundle="${loc}" key="local.enter.title" var="enter_title" />
<fmt:message bundle="${loc}" key="local.enter.brief" var="enter_brief" />
<fmt:message bundle="${loc}" key="local.enter.content"
	var="enter_content" />
<fmt:message bundle="${loc}" key="local.enter.date" var="enter_date" />
<fmt:message bundle="${loc}" key="local.locbutton.send"
	var="button_send" />
</head>
<body>

	<form action="Controller" method="post">
		<input type="hidden" name="local" value="ru" /> <input type="hidden"
			name="command" value="CHANGE_LOCAL" /> <input type="submit"
			value="${ru_button}" /><br />
	</form>

	<form action="Controller" method="post">
		<input type="hidden" name="local" value="en" /> <input type="hidden"
			name="command" value="CHANGE_LOCAL" /> <input type="submit"
			value="${en_button}" /><br />
	</form>

	<h1 style="text-align: left;">


		<font color="mediumvioletred" size="15"> <c:out
				value="${add_news}" />
		</font>


	</h1>

	<form action="Controller" method="post">

		<c:if test="${user.role == 'admin'}">
			<input type="hidden" name="command" value="add_news" />
		</c:if>

		<c:if test="${user.role == 'user'}">
			<input type="hidden" name="command" value="offer_news" />
		</c:if>

		<br />
		<c:out value="${enter_title}" />
		<input type="text" name="title" value="" required 
		pattern="^(.|\n){2,30}$" 
		title="Название должно содержать от 2 до 30  символов"/>
		<br /> <br />
		
		<c:out value="${enter_brief}" />
		<input type="text" name="brief" value="" required
		pattern="^(.|\n){2,100}$" 
		title="Краткое описание должно содержать от 2 до 100 символов"/>
		<br /> <br />
		
		<c:out value="${enter_content}" />
		<input type="text" name="content" value="" required
		pattern="^(.|\n){2,1000}$" 
		title="Содержание должно быть от 2 до 1000 символов"/>
		<br /> <br /> 
		
		<input type="submit" value="${button_send}" /><br />
	</form>



</body>
</html>