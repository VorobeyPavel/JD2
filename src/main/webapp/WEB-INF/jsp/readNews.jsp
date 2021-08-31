<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Read news</title>

<link rel="stylesheet" href="resources/news.css">

</head>
<body>
	<%-- <h1>
		<c:out value="${requestScope.title}" />
	</h1>
	<h2>
		<c:out value="${requestScope.content}" />
	</h2>

	<form action="Controller" method="post">
		<input type="hidden" name="id" value="${oneNews.id}" /> <input
			type="hidden" name="command" value="add_to_favourites" /> <input
			type="submit" value="Add to favourites">
	</form> --%>
	
	<table class="text" align="center">
   		 <c:forEach  items="${newses}" var="clip" >
   		    <tr><td> <c:out value="${clip.getTitle()}"/> </td></tr>
    	    <tr><td> <c:out value="${clip.getContent()}"/>
     	   <HR WIDTH="90%" ALIGN="center" SIZE="2"> </td></tr> 
 	   	</c:forEach>
	</table>
	
	
	
	
	
	
</body>
</html>