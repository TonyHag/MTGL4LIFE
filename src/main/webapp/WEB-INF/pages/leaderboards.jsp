<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 08.12.14
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Leaderboards</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="numberOfNotifications" value="${numberOfNotifications}"></jsp:param>

</jsp:include>

<div class="container text-left" role="main">
    <h1> Leaderboards </h1>



    <div>
        <p>Create your own leaderboard and invite your friends</p>
        <a href="/leaderboard/worldLeaderboard">World wide leaderboard</a>
    </div>


    <div>
        <h3>Your leaderboards</h3>

    </div>

    <div class="table-responsive">
        <table class="table">
            <c:forEach items="${leaderboardInfos}" var="info">
                <thead>

                </thead>
                <tbody>
                <tr>
                    <td><a href="/leaderboard/${info.id}">${info.name}</a></td>
                    <c:if test="${info.owner == true}">
                        <td> <a href="/leaderboard/manage/${info.id}"><button>Manage</button></a></td>
                    </c:if>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>

    <div>
        <a href="/leaderboard/create"><button class="btn btn-primary btn-sm">Create Leaderboard</button></a>
    </div>





</div>

</body>
</html>
