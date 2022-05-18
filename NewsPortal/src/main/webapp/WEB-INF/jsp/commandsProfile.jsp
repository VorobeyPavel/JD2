<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="main.css" />
<meta charset="utf-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

<fmt:message bundle="${loc}" key="local.locbutton.log.in" var="log_in" />
<fmt:message bundle="${loc}" key="local.locbutton.sign.up" var="sign_up" />
<fmt:message bundle="${loc}" key="local.locbutton.name.exit" var="exit_button" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page" var="main_Page" />


</head>
<body>

<div class="buttonLogIn">
		<c:if test="${user.role != 'guest'}">
			<form style="display:inline;" action="Controller" method="post">
				<input type="hidden" name="command" value="AFTER_AUTHORIZATION" /> 
				<input type="submit" value="${main_Page}" />
			</form>
		</c:if>
	</div>

	<div class="buttonLogIn">
		<form style="display:inline;" action="Controller" method="post">
			<input type="hidden" name="local" value="ru" /> 
			<input type="hidden" name="command" value="CHANGE_LOCAL" /> 
			<input type="submit" value="${ru_button}" /><br />
		</form>
	</div>

	<div class="buttonLogIn">
		<form style="display:inline;" action="Controller" method="post">
			<input type="hidden" name="local" value="en" /> <input type="hidden"
				name="command" value="CHANGE_LOCAL" /> <input type="submit"
				value="${en_button}" /><br />
		</form>
	</div>

	<c:if test="${user.role == 'guest'}">
		<div class="buttonLogIn">
			<form style="display:inline;" action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_AUTHORIZATION_PAGE" />
				<input type="submit" style="background-color: palevioletred"
					size="27" value="${log_in}" />
			</form>
		</div>
		<div class="buttonSignUp">
			<form style="display:inline;" action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_REGISTRATION_PAGE" />
				<input type="submit" style="background-color: palevioletred"
					size="27" value="${sign_up}" />
			</form>
		</div>
	</c:if>

	<c:if test="${user.role != 'guest'}">
		<div class="buttonLogOut">
			<form style="display:inline;" action="Controller" method="post">
				<input type="hidden" name="command" value="EXIT" /> <input
					type="submit" style="background-color: /* palevioletred" */ size="27"
					value="${exit_button}" />
			</form>
		</div>
	</c:if>

</body>
</html>