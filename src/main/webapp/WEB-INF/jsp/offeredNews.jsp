<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="main.css" />
<meta charset="utf-8">
<title>Offered news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />


</head>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/commands.jsp" />

	<h2>Offered news</h2>


	<c:if test="${user.role == 'user'}">
		<c:choose>
			<c:when test="${not empty offeredNews}">
				<table>
					<tr>
						<th>title</th>
						<th>brief</th>
						<th>status</th>
					</tr>

					<c:forEach var="oneNews" items="${offeredNews}">
						<tr>
							<td>${oneNews.title}</td>
							<td>${oneNews.brief}</td>
							<td>${oneNews.status}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<c:out value="Нет предложенных новостей" />
			</c:otherwise>
		</c:choose>
	</c:if>


	<c:if test="${user.role == 'admin'}">
		<c:choose>

			<c:when test="${not empty offeredNews}">

				<table>
					<tr>
						<th>id</th>
						<th>title</th>
						<th>brief</th>
						<th>action</th>
					</tr>

					<c:forEach var="oneNews" items="${offeredNews}">
						<tr>
							<td>${oneNews.id}</td>
							<td>${oneNews.title}</td>
							<td>${oneNews.brief}</td>
							<td>
								<div class="buttonSignUp">
									<form action="Controller" method="post">
										<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
											type="hidden" name="title" value="${oneNews.title}" /> <input
											type="hidden" name="command"
											value="go_to_check_offered_news_page" /> <input
											type="submit" value="check" />
									</form>
								</div>
								<div class="buttonSignUp">
									<form action="Controller" method="post">
										<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
											type="hidden" name="command" value="GO_TO_PUBLISH" />
										<input type="submit" value="go_to_publish" />
									</form>
								</div>
								<div class="buttonSignUp">
									<form action="Controller" method="post">
										<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
											type="hidden" name="command" value="do_not_publish" /> <input
											type="submit" value="do not publish" />
									</form>
								</div>
							</td>

						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<c:out value="Нет предложенных новостей" />
			</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>