<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>
<title>List Newses</title>

<!-- reference our style sheet -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>NP - NEWS PORTAL</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add News -->

			<input type="button" value="Add News"
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button" />

			<!--  add our html table here -->

			<table>
				<tr>
					<th>Title</th>
					<th>Brief</th>
					<!-- <th>Date</th> -->
					<!-- <th>Content</th> -->
					<th>Action</th>
				</tr>

				<!-- loop over and print our newses -->
				<c:forEach var="tempNews" items="${newses}">
				
					<!-- construct an "read" link with news id -->
					<c:url var="readLink" value="/news/showFormForRead">
						<c:param name="newsId" value="${tempNews.idNews}" />
					</c:url>

					<!-- construct an "update" link with news id -->
					<c:url var="updateLink" value="/news/showFormForUpdate">
						<c:param name="newsId" value="${tempNews.idNews}" />
					</c:url>

					<!-- construct an "delete" link with news id -->
					<c:url var="deleteLink" value="/news/delete">
						<c:param name="newsId" value="${tempNews.idNews}" />
					</c:url>

					<tr>
						<td>${tempNews.titleNews}</td>
						<td>${tempNews.briefNews}</td>
						<%-- <td>${tempNews.dateNews}</td> --%>
						<%-- <td>${tempNews.contentNews}</td> --%>

						<td>
							<!-- display the update link --> 
							<a href="${readLink}">Read</a>
							<a href="${updateLink}">Update</a>
							| <a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this news?'))) return false">Delete</a>
						</td>

					</tr>

				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>









