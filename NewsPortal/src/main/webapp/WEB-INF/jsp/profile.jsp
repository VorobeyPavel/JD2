<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My profile</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.profile.favorite.news" var="favorite_news" />

<fmt:message bundle="${loc}" key="local.locbutton.offer.news" var="offer_news" />

<fmt:message bundle="${loc}" key="local.locbutton.add.news" var="add_news" />

<fmt:message bundle="${loc}" key="local.profile.my.offered.news" var="my_offered_news" />

<fmt:message bundle="${loc}" key="local.profile.offered.news" var="offered_news" />

<fmt:message bundle="${loc}" key="local.profile.change.password" var="change_Password" />

<fmt:message bundle="${loc}" key="local.locbutton.main.page" var="mainPage" />
	
 
 <jsp:include page="/WEB-INF/jsp/commandsProfile.jsp" />
	
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="view_favorite_news" /> <input
			type="submit" value="${favorite_news}" />
	</form>

	<c:if test="${user.role == 'user'}">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="go_to_offer_news_page" />
			<input type="submit" value="${offer_news}" />
		</form>
	</c:if>

	<c:if test="${user.role == 'admin'}">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="GO_TO_ADD_NEWS_PAGE" /> <input
				type="submit" value="${add_news}" />
		</form>
	</c:if>

	<c:if test="${user.role == 'user'}">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="VIEW_MY_OFFERED_NEWS" />
			<input type="submit" value="${my_offered_news}" />
		</form>
	</c:if>

	<c:if test="${user.role == 'admin'}">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="VIEW_OFFERED_NEWS" /> <input
				type="submit" value="${offered_news}" />
		</form>
	</c:if>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="change_password" /> <input
			type="submit" value="${change_Password}" />
	</form>


</body>
</html>