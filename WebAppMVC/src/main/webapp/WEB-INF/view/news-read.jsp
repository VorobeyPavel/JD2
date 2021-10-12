<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!-- reference our style sheet -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />


</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<h1>NRM - News Relationship Manager</h1>
		</div>
	</div>
	
	<%-- <h2>News №"${tempNews.idNews}"</h2> --%>
	
	
	<div id="container">

		<div id="content">

			<!-- put new button: Add News -->

			<input type="button" value="Add News"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />

			<!--  add our html table here -->

			<table>
				<tr>
					
					<th>Content</th>
					<th>Action</th>
				</tr>
				
				<%-- <!-- loop over and print our newses -->
				<c:forEach var="tempNews" items="${newses}">
		 --%>

					<!-- construct an "update" link with news id -->
					<c:url var="updateLink" value="/news/showFormForUpdate">
						<c:param name="newsId" value="${news.idNews}" />
					</c:url>

					<!-- construct an "delete" link with news id -->
					<c:url var="deleteLink" value="/news/delete">
						<c:param name="newsId" value="${news.idNews}" />
					</c:url>
				

				
					<tr>
						
						<td>${news.contentNews}</td>

						<td>
							<!-- display the update link --> 
							<a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this news?'))) return false">Delete</a>
						</td>

					</tr>
					
					<%-- </c:forEach> --%>

			</table>

		</div>

	</div>
	
	
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="list">Back to List</a>
		</p>


</body>
</html>