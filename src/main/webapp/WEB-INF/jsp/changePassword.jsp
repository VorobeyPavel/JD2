<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

<!-- <style>
body {
	background-color: lavender;
}

input {
	padding: 10px 12px 10px 6px;
	border-radius: 4px;
	cursor: pointer;
	width: 25%;
	margin-top: 6px;
	box-sizing: border-box;
	border: 1px solid #555;
	outline: none;
	font-size: 16px;
}

input:focus {
	background-color: lightblue;
}

.header1 {
	display: inline;
	font: 50px Arial, sans-serif;
}

.button {
	font-family: Arial, Helvetica, sans-serif;
	border-color: lightslategray;
	background-color: white;
	color: lightslategray;
	text-align: center;
	padding: 6px 6px 6px 6px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 12px;
	border-right: none;
}

.button_in_line {
	display: inline-block;
}

.button_right {
	position: absolute;
	right: 0px;
	padding: 3px;
}

.button_locale {
	border-radius: 12px;
	margin: 4px 2px;
	display: inline-block;
}

.answer {
	font: 22px Arial, Helvetica, sans-serif;
	
}
</style> -->


<meta charset="utf-8">
<title>Changing password</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.changePassword.change.password" var="change_password" />

<fmt:message bundle="${loc}" key="local.locbutton.send" var="button_send" />
<fmt:message bundle="${loc}" key="local.changePassword.error.validation" var="errorValidationPassword" />

<fmt:message bundle="${loc}" key="local.changePassword.error.invalid.DB" var="errorInvalidPasswordDB" />

<fmt:message bundle="${loc}" key="local.changePassword.old.password" var="old_password" />
<fmt:message bundle="${loc}" key="local.changePassword.new.password" var="new_password" />

<fmt:message bundle="${loc}" key="local.changePassword.repeat.new.password" var="repeat_new_password" />

<fmt:message bundle="${loc}" key="local.changePassword.passwords.mismatch" var="errorPasswordsMismatch" />

<fmt:message bundle="${loc}" key="local.changePassword.old.password.matchses.new" var="errorOldPasswordMatchesNewPassword" />

<fmt:message bundle="${loc}"
	key="local.changePassword.error.empty.fields" var="errorEmptyPasswords" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />


</head>
<body>


	<div class="button_right">
	<jsp:include page="/WEB-INF/jsp/commands.jsp" /></div>
	
	<br>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" />
		<button class="button">${mainPage}</button>
	</form>
	<br>
	<br>
	<div class="header1">
		${change_password}<img src="recources/pictures/leaf.png" width="70"
			height="90">
	</div>

	<br />
	<br />


	<div class="answer">
		<c:if test="${not empty param.errorEmptyFields}">
			<c:out value="${errorEmptyPasswords}" />
		</c:if>

		<c:if test="${not empty param.newPasswordsErrorMismatch}">
			<c:out value="${errorPasswordsMismatch}" />
		</c:if>

		<c:if test="${not empty param.oldPasswordMatchesNewPassword}">
			<c:out value="${errorOldPasswordMatchesNewPassword}" />
		</c:if>

		<c:if test="${not empty param.messageErrorValidationPassword}">
			<c:out value="${errorValidationPassword}" />
		</c:if>

		<c:if test="${not empty param.messageErrorInvalidPasswordDB}">
			<c:out value="${errorInvalidPasswordDB}" />
		</c:if>
	</div>
	<br />
	<br />

	<form action="Controller" method="post">
			<input type="hidden" name="command" value="change_password" /> 
			<input type="password" name="oldPassword" 
			placeholder="${old_password}" value="" required
			title="Пароль содержит хотя бы одно число; хотя бы один спецсимвол [!@#$%^&*]; хотя бы одну латинскую букву в нижнем регистре; хотя бы одну латинскую букву в верхнем регистре; состоит не менее, чем из 8 вышеупомянутых символов. " />

		<br /> <br /> 
			<input type="password" name="newPassword1"
			placeholder="${new_password}" value="" required
			title="Пароль содержит хотя бы одно число; хотя бы один спецсимвол [!@#$%^&*]; хотя бы одну латинскую букву в нижнем регистре; хотя бы одну латинскую букву в верхнем регистре; состоит не менее, чем из 8 вышеупомянутых символов. " />



		<br /> <br /> 
			<input type="password" name="newPassword2"
			placeholder="${repeat_new_password}" value="" required
			title="Пароль содержит хотя бы одно число; хотя бы один спецсимвол [!@#$%^&*]; хотя бы одну латинскую букву в нижнем регистре; хотя бы одну латинскую букву в верхнем регистре; состоит не менее, чем из 8 вышеупомянутых символов. " />


		<br /> <br />
		<button class="button">${button_send}</button>


	</form>


	<br>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>