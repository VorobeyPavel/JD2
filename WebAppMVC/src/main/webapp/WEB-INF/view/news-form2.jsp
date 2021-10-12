<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>Save News</title>

	<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css"/>" />
	
	<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/customer-form.css"/>" />
	
	<%-- <link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/add-news-form.css" />" /> --%>
	
	
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>CRM - News Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save News</h3>
	
		<form:form action="saveNews" modelAttribute="news" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="idNews" />
					
					
			<div class="textarea">
				<label for="title" style="color:green">Title:</label>
				<br />
				<form:textarea class="txta" id="title" name="title" path="titleNews"/>
				<form:errors path = "titleNews" cssClass="error" ></form:errors>
			</div>
			<br />
			
			<div class="textarea">
				<label for="brief" style="color:green">Brief:</label>
				<br />
				<form:textarea class="txta" id="brief" name="brief" path="briefNews"/>
				<form:errors path = "briefNews" cssClass="error" />
			</div>
			
			<br />
			<div class="textarea">
				<label for="content" style="color:green">Content:</label>
				<br />
				<form:textarea class="txta" id="content" name="content" path="contentNews"/>
				<form:errors path = "contentNews" cssClass="error" />
			</div>
			
			
			<input class= add-button type="submit" value="Save" class="save" />

		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="list">Back to List</a>
		</p>
	
	</div>

</body>

</html>