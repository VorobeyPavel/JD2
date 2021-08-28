<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="resources/Newss.css"> -->

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.exit" var="exit_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.login" var="Login_button" />
<fmt:message bundle="${loc}" key="local.text.hello" var="hello" />

</head>
<body>
	<div class="heading">
		<h1 class=headline>
			<c:out value="${name_site}" />
		</h1>
		<div class=heading-1>

			<!-- Кнопки RU EN -->

			<div class="heading-2">
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="ru" />
					<input type="hidden" name="command" value="CHANGE_LOCAL"/>
					<input type="submit" class="button_local" value="${ru_button}" /><br />
				</form>
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="en" /> 
					<input type="hidden" name="command" value="CHANGE_LOCAL"/>
					<input type="submit" class="button_local" value="${en_button}" /><br />
				</form>
				<form action="Controller" method="post">
					<input type="hidden" name="local" value="exit" /> 
					<input type="hidden" name="command" value="EXIT"/>
					<input type="submit" class="button_local" value="${exit_button}" /><br />
				</form>
				
			</div>
		</div>
	</div>

	<c:set var="mer" value="${sessionScope.user}" />
	<p class="text"><c:out value="${mer.getName()} ${hello}" /></p>
	
	<HR WIDTH="90%" ALIGN="center" SIZE="2">
	
	<table class="text" align="center">
   		 <c:forEach  items="${newses}" var="clip" >
   		    <tr><td> <c:out value="${clip.getTitle()}"/> </td></tr>
    	    <tr><td> <c:out value="${clip.getBrief()}"/>
     	   <HR WIDTH="90%" ALIGN="center" SIZE="2"> </td></tr> 
 	   	</c:forEach>
	</table>
	
</body>
</html>