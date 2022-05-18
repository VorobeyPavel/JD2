<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="main.css" />
<meta charset="utf-8">
<title>My favorite news</title>
</head>
<body>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.myProfile" var="my_profile" />


<jsp:include page="/WEB-INF/jsp/commandsProfile.jsp" />

	<h2></h2>


	<c:if test="${user.role != 'guest'}">	
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="open_profile" /> 
					<input type="submit" style="background-color: /* palevioletred */" size="27"
							class="button_local" value="${my_profile}" />
				</form>
	</c:if>


	<h2>Favorite news</h2>

	<c:choose>
		<c:when test="${not empty favoriteNews}">
			<table>
				<tr>
					<th>id</th>
					<th>title</th>
					<th>brief</th>
					<th>action</th>
				</tr>

				<c:forEach var="oneNews" items="${favoriteNews}">
					<tr>
						<td>${oneNews.id}</td>
						<td>${oneNews.title}</td>
						<td>${oneNews.brief}</td>
						<td>
							<div class="buttonSignUp">
								<form action="Controller" method="post">
									<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
										type="hidden" name="title" value="${oneNews.title}" /> <input
										type="hidden" name="command" value="read_news" /> <input
										type="submit" value="read" />
								</form>
							</div>
							<%-- <div class="buttonSignUp">
								<form action="Controller" method="post">
									<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
										type="hidden" name="command" value="GO_TO_EDIT_NEWS_PAGE" />
									<input type="submit" value="edit" />
								</form>
							</div> --%>
							
							<c:if test="${user.role == 'admin'}">
							<div class="buttonSignUp">
								<form action="Controller" method="post">
									<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
										type="hidden" name="command" value="delete_from_favorites" /> <input
										type="submit" value="delete" />
								</form>
							</div>
							</c:if>
							
						</td>

					</tr>
				</c:forEach>
			</table>

		</c:when>
		<c:otherwise>
			<c:out value="Нет избранных новостей" />
		</c:otherwise>
	</c:choose>





</body>
</html>