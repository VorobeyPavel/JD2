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



            <h2>Отправьте сообщение</h2>


         <font color="red" size="3">
         			<c:out value="${sessionScope.notAuthorization} send message!!!"/>
         </font>

         <c:out value="${sentMessage} send message!!!"/>

        <form action="message" method="post">

                    <input type="text" name="login" placeholder="${login}" value=""
                    required pattern="^[a-zA-Z][a-zA-Z]{2,15}$"
                    title="Логин должен содержать от 2 до 20 латинских символов">
                    <br /> <br />

        			<input type="text" name="message" placeholder="${message}" value="">
                    <br /> <br />

        			<input  type="submit" class="button" value="Отправить" />
        		</form>


        <form action="Chat" method="post">
        	<input  type="submit" class="button" value="Посмотреть отправленные мне сообщения" />
        </form>

    </body>
</html>