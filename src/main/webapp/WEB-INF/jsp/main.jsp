<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/news.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.login" var="Login_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.register" var="Register_button" />
<fmt:message bundle="${loc}" key="local.text.welcom" var="welcom"/>	
<fmt:message bundle="${loc}" key="local.text.after_aut" var="after_aut"/>	

</head>
<body>
	<div class="heading">

		<h1 class=headline><c:out value="${name_site}" /></h1>
		
		<div class=heading-1>
		
		<!-- Кнопки RU EN -->
		
			<div class="heading-2">
			<form action="Controller" method="post">
				<input type="hidden" name="local" value="ru" />
				<input type="hidden" name="command" value="CHANGE_LOCAL" /> 
				<input type="submit" class="button_local" value="${ru_button}" /><br />
			</form>
	
			<form action="Controller" method="post">
				<input type="hidden" name="local" value="en"/>
				<input type="hidden" name="command" value="CHANGE_LOCAL"/>
				<input type="submit" class="button_local" value="${en_button}" /><br />
			</form>
		
			</div>
			<div class="heading-2">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="AUTHORIZATION_PAGE" /> 
					<input type="submit" class="button" value="${Login_button}" />
				</form>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="REGISTRATION_PAGE" /> 
					<input type="submit" class="button" value="${Register_button}" />

				</form>
			</div>
		</div>
	</div>
	
	<p class="text"><c:out value="${welcom}"/></p>
	<p class="text"><c:out value="${after_aut}"/></p>
	<HR WIDTH="90%" ALIGN="center" SIZE="2">
	

	<!-- Здесь начинается вывод новостей -->

<%-- 	<table class="text" align="center">
   		 <c:forEach  items="${newses}" var="clip" >
   		    <tr><td> <c:out value="${clip.getTitle()}"/> </td></tr>
    	    <tr><td> <c:out value="${clip.getBrief()}"/>
     	   <HR WIDTH="90%" ALIGN="center" SIZE="2"> </td></tr> 
 	   	</c:forEach>
	</table> --%>
	
	<table>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>brief</th>
			<th>action</th>
		</tr>

		<c:forEach var="oneNews" items="${newses}">
			<tr>
				<td>${oneNews.id}</td>
				<td>${oneNews.title}</td>
				<td>${oneNews.brief}</td>
				<td>

					<form action="Controller" method="post">
						<input type="hidden" name="id" value="${oneNews.id}" /> <input
							type="hidden" name="title" value="${oneNews.title}" /> <input
							type="hidden" name="command" value="read_news" /> <input
							type="submit" value="read" />
					</form>

					<form action="Controller" method="post">
						<input type="hidden" name="id" value="${oneNews.id}" /> <input
							type="hidden" name="command" value="GO_TO_EDIT_NEWS_PAGE" /> <input
							type="submit" value="edit" />
					</form>

					<form action="Controller" method="post">
						<input type="hidden" name="id" value="${oneNews.id}" /> <input
							type="hidden" name="command" value="delete_news" /> <input
							type="submit" value="delete" />
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
		
</body>
</html>