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

         <h2>Добро пожаловать</h2>
         <h2>Вы успешно авторизовались</h2>

        <font color="red" size="5">
                    <c:out value="${authorization}"/>
         			<c:out value="${sessionScope.user}"/>
         </font>

         <br/><br/><br/>

         <iframe src="https://www.alta.ru/currency/graph_frame/?min=27&max=27"
         width="600" height="495" style="border: 1px solid #333;"></iframe>

         <br/><br/><br/>

         <!--Currency Converter widget by FreeCurrencyRates.com -->

         <div id='gcw_mainFQvqDJGLj' class='gcw_mainFQvqDJGLj'></div>
         <a id='gcw_siteFQvqDJGLj' href='https://freecurrencyrates.com/ru/'>FreeCurrencyRates.com</a>
         <script>function reloadFQvqDJGLj(){ var sc = document.getElementById('scFQvqDJGLj');
         if (sc) sc.parentNode.removeChild(sc);sc = document.createElement('script');
         sc.type = 'text/javascript';sc.charset = 'UTF-8';sc.async = true;sc.id='scFQvqDJGLj';
         sc.src = 'https://freecurrencyrates.com/ru/widget-vertical-editable?iso=USD-EUR-RUB-GBP-UAH-CNY&df=1&p=FQvqDJGLj&v=fits&source=nbrb&width=245&width_title=0&firstrowvalue=1&thm=A6C9E2,FCFDFD,4297D7,5C9CCC,FFFFFF,C5DBEC,FCFDFD,2E6E9E,000000&title=%D0%9A%D0%BE%D0%BD%D0%B2%D0%B5%D1%80%D1%82%D0%B5%D1%80%20%D0%B2%D0%B0%D0%BB%D1%8E%D1%82&tzo=-180';var div = document.getElementById('gcw_mainFQvqDJGLj');div.parentNode.insertBefore(sc, div);} reloadFQvqDJGLj(); </script>
         <!-- put custom styles here: .gcw_mainFQvqDJGLj{}, .gcw_headerFQvqDJGLj{}, .gcw_ratesFQvqDJGLj{}, .gcw_sourceFQvqDJGLj{} -->
         <!--End of Currency Converter widget by FreeCurrencyRates.com -->


    </body>
</html>