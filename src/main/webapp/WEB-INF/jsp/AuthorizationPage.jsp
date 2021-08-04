<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<!-- <link rel="stylesheet" href="resources/css/NewSst.css"> -->

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.name.site" var="name_site"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.login" var="Login_button" />
<fmt:message bundle="${loc}" key="local.text.created" var="created"/>

<fmt:message bundle="${loc}" key="local.user.email" var="email"/>
<fmt:message bundle="${loc}" key="local.user.enter_password" var="enter_password"/>
<fmt:message bundle="${loc}" key="local.text.password_hint" var="password_hint"/>
<fmt:message bundle="${loc}" key="local.text.incorrect_data" var="incorrect_data_message"/>
<fmt:message bundle="${loc}" key="local.text.registration_message" var="registration_message"/>
<fmt:message bundle="${loc}" key="local.text.user_not_found" var="user_not_found"/>

<!-- <style>
.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}
.form p {
	color: grey;
	margin: 0px 0px 10px 0px;
	font-size: 12px;
}
</style> -->

</head>
<body>

	<div class="heading">

		<h1 class=headline><c:out value="${name_site}" /></h1>
		
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
			</div>	
		</div>
	</div>
	
	<div class=form>
		
		<!-- Вывод надписи при некорректных данных -->
		
		<font color="red" size="3">
			<c:out value="${param.incorrect_data_message}"/>
			<c:out value="${param.user_not_found}"/>
		</font>
			
		<font color="green" size="3">
			<c:out value='${param.registration_message}'/>
		</font>
	
		<!-- Поля для ввода Email и пороля -->
	
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="Authorization_user" /> 
			<input type="text" name="email" placeholder="${email}" value="" /> 
			<input type="password" name="enter_password" placeholder="${enter_password}" value="" />
			<p><c:out value="${password_hint}"/></p>
			<input style="/* background: #324AB2; */ width: 30%;" type="submit"
				class="button" value="${Login_button}" />
		</form>
	</div>
	
	<%-- <p class="text" style="font-size: 20px"><c:out value="${created}"/></p> --%>
</body>
</html>