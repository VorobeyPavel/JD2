<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<!-- <link rel="stylesheet" href="resources/css/NewSst.css"> -->

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.login" var="Login_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.register" var="Register_button" />
<fmt:message bundle="${loc}" key="local.text.welcom" var="welcom"/>	
<fmt:message bundle="${loc}" key="local.text.created" var="created"/>	

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
	<!-- <HR WIDTH="90%" ALIGN="center" SIZE="2"> -->

	<!-- Здесь начинается вывод новостей -->

	<%-- <table class="text" align="center">
   		 <c:forEach  items="${newses}" var="clip" >
   		    <tr><td> <c:out value="${clip.getTitle()}"/> </td></tr>
    	    <tr><td> <c:out value="${clip.getBrief()}"/>
     	   <HR WIDTH="90%" ALIGN="center" SIZE="2"> </td></tr> 
 	   </c:forEach>
</table> --%>
	

	
</body>
</html>