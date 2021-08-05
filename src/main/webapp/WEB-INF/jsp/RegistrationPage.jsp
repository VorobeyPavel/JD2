<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>


<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.name.site" var="name_site"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.register" var="Register_button" />
<fmt:message bundle="${loc}" key="local.text.created" var="created"/>

<fmt:message bundle="${loc}" key="local.user.name" var="name"/>
<fmt:message bundle="${loc}" key="local.user.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.user.email" var="email"/>
<fmt:message bundle="${loc}" key="local.user.enter_password" var="enter_password"/>
<fmt:message bundle="${loc}" key="local.user.repeat_password" var="repeat_password"/>


<fmt:message bundle="${loc}" key="local.text.password_hint" var="password_hint"/>
<fmt:message bundle="${loc}" key="local.text.incorrect_data" var="incorrect_data_message"/>
<fmt:message bundle="${loc}" key="local.text.already_exist" var="already_exist"/>


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
				<input type="hidden" name="local" value="en" /> 
				<input type="hidden" name="command" value="CHANGE_LOCAL" /> 
				<input type="submit" class="button_local" value="${en_button}" /><br />
			</form>
		</div>
		
			
		</div>
	</div>
	<div class=form>
	
	<!-- Вывод надписи при некорректных данных -->
	
		<font color="red" size="3">
			<c:out value="${param.incorrect_data_message}"/>
			<c:out value="${param.email_is_busy}"/>
		</font>
		
		<!-- Поля для ввода имени, почты и пороля -->
		
		
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="Registration_new_user" />


			<input type="text" name="name" placeholder="${name}" value="" /> 
			<input type="text" name="surname" placeholder="${surname}" value="" />
			<input type="text" name="email" placeholder="${email}" value="" /> 
			<input type="password" name="enter_password" placeholder="${enter_password}" value="" />
			<input type="password" name="repeat_password" placeholder="${repeat_password}" value="" />
			
			<p><c:out value="${password_hint}"/></p>

			<input style="/* background: #324AB2; */ width: 30%;" type="submit"
				class="button" value="${Register_button}" />
		</form>
	</div>

</body>
</html>