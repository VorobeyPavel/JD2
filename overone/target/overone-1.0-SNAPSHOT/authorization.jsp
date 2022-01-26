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



            <h2>Форма для авторизации</h2>
            <h1>Введите свои данные</h1>

         <font color="red" size="3">
         			<c:out value="${param.notAuthorization}"/>
         </font>

        <form action="Authorization" method="get">

        			<input type="text" name="login" placeholder="${login}" value=""
                    required pattern="^[a-zA-Z][a-zA-Z]{2,15}$"
                    title="Логин должен содержать от 2 до 20 латинских символов">
                    <br /> <br />

        			<input type="password" name="password" placeholder="${password}" value=""
                    required pattern="((?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,})"
                    title="Пароль должен содержать: хотя бы одну цифру; хотя бы один специальный символ [! @ # $% ^ & *]; хотя бы одну строчную латинскую букву; хотя бы одну заглавную латинскую букву; состоит как минимум из 8 вышеназванных символов">
                    <br /> <br />


        			<input  type="submit" class="button" value="Отправить" />
        		</form>

    </body>
</html>