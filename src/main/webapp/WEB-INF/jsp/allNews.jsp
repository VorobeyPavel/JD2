<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="main.css" /> -->
<meta charset="utf-8">
<title>All news</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.allNews" var="All_News" />

<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />

<fmt:message bundle="${loc}" key="local.locbutton.log.in" var="log_in" />
<fmt:message bundle="${loc}" key="local.locbutton.sign.up" var="sign_up" />
<fmt:message bundle="${loc}" key="local.locbutton.name.exit" var="exit_button" />
<%-- <fmt:message bundle="${loc}" key="local.locbutton.log.out" var="log_out" /> --%>
<fmt:message bundle="${loc}" key="local.locbutton.name.myProfile" var="my_profile" />
<fmt:message bundle="${loc}" key="local.locbutton.main.page" var="main_Page" />


<fmt:message bundle="${loc}" key="local.locbutton.name.read" var="read" />
<fmt:message bundle="${loc}" key="local.locbutton.name.edit" var="edit" />
<fmt:message bundle="${loc}" key="local.locbutton.name.delete" var="delete" />

</head>
<body>

<%-- <jsp:include page="/WEB-INF/jsp/commands.jsp" /> --%>



<div class="buttonLogIn">
	
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="AFTER_AUTHORIZATION" /> <input
					type="submit" value="${main_Page}" />
			</form>
	
	</div>

	<div class="buttonLogIn">
		<form action="Controller" method="post">
			<input type="hidden" name="local" value="ru" /> 
			<input type="hidden" name="command" value="CHANGE_LOCAL" /> 
			<input type="submit" value="${ru_button}" /><br />
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
	




	<!-- <h2>All news</h2> -->
	<c:out value="${All_News}" />
	<table>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>brief</th>
			<th>action</th>
		</tr>

		<c:forEach var="oneNews" items="${limitNews}">
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
								type="submit" class="button_local" value="${read}" />
						</form>
					</div>
					<div class="buttonSignUp">
						<form action="Controller" method="post">
							<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
								type="hidden" name="command" value="GO_TO_EDIT_NEWS_PAGE" /> <input
								type="submit" class="button_local" value="${edit}" />
						</form>
					</div>
					<div class="buttonSignUp">
						<form action="Controller" method="post">
							<input type="hidden" name="idNews" value="${oneNews.id}" /> <input
								type="hidden" name="command" value="delete_news" /> <input
								type="submit" class="button_local" value="${delete}" />
						</form>
					</div>
				</td>

			</tr>
		</c:forEach>
	</table>


	<%--For displaying Previous page except for the 1st page --%>
	<c:if test="${param.currentPage != 1}">
		<td>

			<form action="Controller" method="post">
				<input type="hidden" name="page" value="${param.currentPage-1}" /> <input
					type="hidden" name="command" value="read_All_News" /> <input
					type="submit" value="Previous" />
			</form>


		</td>
	</c:if>

	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${param.numberOfPages}" var="i">
				<c:choose>
					<c:when test="${param.currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td>

							<form action="Controller" method="post">
								<input type="hidden" name="page" value="${i}" /> <input
									type="hidden" name="command" value="read_All_News" /> <input
									type="submit" value="${i}" />
							</form>

						</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<%--For displaying Next page --%>
	<c:if test="${param.currentPage lt param.numberOfPages}">
		<td><form action="Controller" method="post">
				<input type="hidden" name="command" value="read_All_News" />
				<input type="hidden" name="page" value="${param.currentPage+1}" /> <input
					type="submit" value="Next" />
			</form></td>
	</c:if>
</body>
</html>