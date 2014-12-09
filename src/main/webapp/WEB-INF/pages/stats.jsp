<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 05.12.14
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Stats</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>


<h1>Statistics for ${stats.username}</h1>

<table>
    <tr> <th>Wins</th>              <th>Losses</th>           <th>Total</th>              <th>Win%</th>        </tr>
    <tr> <td>${stats.wins}</td> <td>${stats.losses}</td> <td>${stats.total}</td> <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${stats.winPercentage}"/></td>  </tr>

</table>


</body>
</html>
