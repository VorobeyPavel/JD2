<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Check news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

</head>
<body>

<jsp:include page="/WEB-INF/jsp/commands.jsp" />

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="VIEW_OFFERED_NEWS" /> <input
			type="submit" value="Предложенные новости" />
	</form>


	Title
	<br>
	<h1>
		<c:out value="${news.title}" />
	</h1>
	<br> Brief
	<br>
	<h2>
		<c:out value="${news.brief}" />
	</h2>
	<br> Content
	<br>
	<h2>
		<c:out value="${news.content}" />
	</h2>

	<div class="buttonSignUp">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${news.id}" /> <input
				type="hidden" name="command" value="GO_TO_PUBLISH" /> <input
				type="submit" value="go_to_publish" />
		</form>
	</div>
	<div class="buttonSignUp">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${news.id}" /> <input
				type="hidden" name="command" value="do_not_publish" /> <input
				type="submit" value="do not publish" />
		</form>
	</div>



</body>
</html>