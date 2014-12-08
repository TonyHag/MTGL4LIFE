<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 07.12.14
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Leaderboard</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>
<h1>Create Leaderboard</h1>

<form action="/leaderboard/create" method="post">
    <table>
        <tr> <td>Name: </td> <td> <input type="text" name="name" /> </td>   </tr>
        <tr> <td>Description: </td> <td> <textarea name="description" rows="2" ></textarea> </td>   </tr>


        <tr>  <input type="submit" value="Create"/> </td> </tr>

    </table>
</form>

</body>
</html>
