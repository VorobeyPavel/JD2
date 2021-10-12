<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Read news</title>

<!-- <link rel="stylesheet" href="resources/news.css"> -->

</head>
<body>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="AFTER_AUTHORIZATION" /> <input
			type="submit" value="На главную" />
	</form>
	
	<c:out value="${param.responseCommand}" />


	<h1>
		<c:out value="${requestScope.title}" />
	</h1>
	
	<h2>
		<c:out value="${requestScope.content}" />
	</h2>
	
	<%-- <h3>
		<c:out value="${requestScope.idNews}" />
	</h3>
	
	<h4>
		<c:out value="${requestScope.id}" />
	</h4> --%>
	
	
	<c:if test="${requestScope.isFavourite==true}">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${requestScope.idNews}" />
			<input type="hidden" name="command" value="delete_from_favorites" />
			<input type="submit" value="Delete from favourites">
		</form>

	</c:if>

	<c:if test="${requestScope.isFavourite==false}">
		<form action="Controller" method="post">
			<input type="hidden" name="idNews" value="${requestScope.idNews}" />
			<input type="hidden" name="command" value="add_to_favorites" /> <input
				type="submit" value="Add to favourites">
		</form>
	</c:if>
	

	<br>
	<br>
	<table>
		<tr>
			<c:forEach var="comment" items="${requestScope.comments}">
				<tr>
					<td>${comment.content}</td>
					<td><fmt:formatDate value="${comment.publishingDate}"
							pattern="yyyy-MM-dd" /></td>



				</tr>
			</c:forEach>
	</table>
	<br>
	<br>
	<form action="Controller" method="post">
		<input type="hidden" name="idNews" value="${requestScope.idNews}" />
		<input type="hidden" name="command" value="add_comment" /> <input
			type="text" name="comment" placeholder="comment..." value="" /> <br>
		<input type="submit" value="Comment">
	</form>
	
	
	
	<%-- <table class="text" align="center">
   		 <c:forEach  items="${newses}" var="clip" >
   		    <tr><td> <c:out value="${clip.getTitle()}"/> </td></tr>
    	    <tr><td> <c:out value="${clip.getContent()}"/>
     	   <HR WIDTH="90%" ALIGN="center" SIZE="2"> </td></tr> 
 	   	</c:forEach>
	</table> --%>
	
	
	
	
</body>
</html>