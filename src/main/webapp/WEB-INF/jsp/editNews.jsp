<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>



<meta charset="utf-8">
<title>Edit news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization/local" var="loc" />

<fmt:message bundle="${loc}" key="local.editNews.editting.news"
	var="edit_news" />
<fmt:message bundle="${loc}" key="local.enter.title" var="enter_title" />
<fmt:message bundle="${loc}" key="local.enter.brief" var="enter_brief" />
<fmt:message bundle="${loc}" key="local.enter.content"
	var="enter_content" />
<fmt:message bundle="${loc}" key="local.editNews.error.empty"
	var="errorEditNewsEmptyFields" />
<fmt:message bundle="${loc}" key="local.locbutton.send"
	var="button_send" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page"
	var="mainPage" />

</head>
<body>

	<div class="button_right"><jsp:include
			page="/WEB-INF/jsp/commands.jsp" /></div>

	<br>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="go_to_main_page" />
		<button class="button">${mainPage}</button>
	</form>
	<br>
	<br>
	<div class="header1">
		${edit_news} <img src="recources/pictures/leaf.png" width="70"
			height="90">
	</div>
	<div class="answer">
		<c:if test="${not empty param.messageErrorEmpty}">
			<c:out value="${errorEditNewsEmptyFields}" />
		</c:if>
	</div>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="edit_news" /> <br /> <input
			type="hidden" name="idNews" value="${requestScope.idNews}" />



		<h2>
			<c:out value="${enter_title}" />
		</h2>
		<br /> <input type="text" name="title" value="${requestScope.title}" /><br />
		<br />
		<h2>
			<c:out value="${enter_brief}" />
		</h2>
		<br /> <input type="text" name="brief" value="${requestScope.brief}" /><br />
		<br />
		<h2>
			<c:out value="${enter_content}" />
		</h2>
		<br />
		<textarea name="content" style="height: 200px">${requestScope.content}</textarea>
		<br /> <br />
		<button class="button">${button_send}</button>
		<br /> <br />
	</form>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>