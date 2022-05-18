<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<%-- <fmt:message bundle="${loc}" key="local.locbutton.log.out" var="log_out" /> --%>
<fmt:message bundle="${loc}" key="local.locbutton.name.myProfile" var="my_profile" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page" var="main_Page" />


</head>
<body>
	<div class="buttonLogIn">
	
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="AFTER_AUTHORIZATION" /> <input
					type="submit" value="${main_Page}" />
			</form>
	
	</div>

	<div class="buttonLogIn">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="ru" /> <input type="hidden"
				name="command" value="CHANGE_LOCAL" /> <input type="submit"
				value="${ru_button}" /><br />
		</form>
	</div>

	<div class="buttonLogIn">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="en" /> <input type="hidden"
				name="command" value="CHANGE_LOCAL" /> <input type="submit"
				value="${en_button}" /><br />
		</form>
	</div>

	<c:if test="${user.role == 'guest'}">
		<div class="buttonLogIn">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_AUTHORIZATION_PAGE" />
				<input type="submit" style="background-color: palevioletred"
					size="11" value="${log_in}" />
			</form>
		</div>
		<div class="buttonSignUp">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="GO_TO_REGISTRATION_PAGE" />
				<input type="submit" style="background-color: palevioletred"
					size="11" value="${sign_up}" />
			</form>
		</div>
	</c:if>

	<c:if test="${user.role != 'guest'}">
		<div class="buttonLogOut">
			<form action="Controller" method="post" value="exit">
				<input type="hidden" name="command" value="EXIT" /> <input
					type="submit" style="background-color: /* palevioletred" */ size="11"
					value="${exit_button}" />
			</form>
		</div>
		<div class="buttonLogOut">
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="open_profile" /> <input
					type="submit" style="background-color: /* palevioletred" */ size="11"
					value="${my_profile}" />
			</form>
		</div>

	</c:if>

</body>
</html>