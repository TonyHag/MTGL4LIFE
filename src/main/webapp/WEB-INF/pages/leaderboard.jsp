<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 08.12.14
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Leaderboard: ${leaderboard.name}</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>

<div class="container text-left" role="main">

    <h1>${leaderboard.name}</h1>

    <p>${leaderboard.description}</p>

    <h3>Players</h3>
    <ul class="nav nav-tabs">
        <li role="presentation" id="totalNavTab" class="active">
            <a href="/leaderboard/${leaderboard.id}?statsType=total">Total</a>
        </li>

        <li role="presentation" id="ffaNavTab">
            <a href="/leaderboard/${leaderboard.id}?statsType=ffa">Free For All</a>
        </li>

        <li role="presentation" id="thgNavTab">
            <a href="/leaderboard/${leaderboard.id}?statsType=thg">Two Headed Giant</a>
        </li>
    </ul>

    <div class="table-responsive">
        <table class="table ">
            <thead>
            <tr>
                <th>Name</th>
                <th>Wins</th>
                <th>Losses</th>
                <th>Total</th>
                <th>Win %</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${statsList}" var="stats">
                <tr>
                    <td> ${stats.username}     </td>
                    <td> ${stats.wins}         </td>
                    <td> ${stats.losses}       </td>
                    <td> ${stats.total}        </td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${stats.winPercentage}"/></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>



</div>


<script type="text/javascript">
    $(function() {

        var statsType = "${statsType}";
        console.log(statsType);

        if(statsType == "total") {
            $("#totalNavTab").addClass("active");
            $("#thgNavTab").removeClass("active");
            $("#ffaNavTab").removeClass("active");
        }

        if(statsType == "ffa") {
            $("#totalNavTab").removeClass("active");
            $("#thgNavTab").removeClass("active");
            $("#ffaNavTab").addClass("active");
        }

        if(statsType == "thg") {
            $("#totalNavTab").removeClass("active");
            $("#thgNavTab").addClass("active");
            $("#ffaNavTab").removeClass("active");
        }



    });
</script>
</body>
</html>
