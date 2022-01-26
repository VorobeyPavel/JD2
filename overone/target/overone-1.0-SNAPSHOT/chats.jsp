<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>

<%!
  String getFormattedDate() {
     SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
     return sdf.format(new Date());
  }
%>

<html>
 <head>
   <title>!DOCTYPE</title>
   <meta charset="utf-8">
 </head>

 <body>

 <p><%= getFormattedDate() %></p>

            <h2>Ваши сообщения</h2>

            <c:forEach var="oneMessage" items="${sessionScope.messages}">
                 <c:out value="${oneMessage}"/>
            </c:forEach>

            <h2>_______________</h2>

            <c:forEach var="name" items="${sessionScope.messages}">
                             <tr>
                                 <h1>1</h1>
                                 <td>${name}</td>
                              </tr>
            </c:forEach>


    </body>
</html>