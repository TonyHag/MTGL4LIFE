<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 05.12.14
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Stats</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
    <jsp:param name="numberOfNotifications" value="${numberOfNotifications}"></jsp:param>

</jsp:include>

<div class="container text-left" role="main">

    <c:choose>
        <c:when test="${stats != null}" >

            <div class="row">
                <div class="col-md-6">
                    <h1>Statistics for ${stats.username}</h1>
                </div>

                <div class="col-md-6">
                    <form action="/stats/search" method="post">
                        <input type="text" name="searchForPlayer" type="search" placeholder="Find stats for a player">
                        <input type="submit" value="Search">
                    </form>
                </div>
            </div>


            <ul class="nav nav-tabs">
                <li role="presentation" id="totalNavTab" class="active">
                    <a href="/stats/${stats.username}?statsType=total">Total</a>
                </li>

                <li role="presentation" id="1v1NavTab">
                    <a href="/stats/${stats.username}?statsType=1v1">1v1</a>
                </li>

                <li role="presentation" id="ffaNavTab">
                    <a href="/stats/${stats.username}?statsType=ffa">Free For All</a>
                </li>

                <li role="presentation" id="thgNavTab">
                    <a href="/stats/${stats.username}?statsType=thg">Two Headed Giant</a>
                </li>
            </ul>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Total</th>
                        <th>Wins</th>
                        <th>Losses</th>
                        <th>Draws</th>
                        <th>Win%</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${stats.total}</td>
                        <td>${stats.wins}</td>
                        <td>${stats.losses}</td>
                        <td>TODO</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${stats.winPercentage}"/></td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <div class="row">
                <div class="col-md-6">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th colspan="4">Worst enemy: N/A </th>
                            </tr>
                            <tr>
                                <th>T</th>
                                <th>W</th>
                                <th>L</th>
                                <th>D</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th colspan="4">Top victim: N/A </th>
                            </tr>
                            <tr>
                                <th>T</th>
                                <th>W</th>
                                <th>L</th>
                                <th>D</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th colspan="4">Opponent: N/A</th>
                            </tr>
                            <tr>
                                <th>T</th>
                                <th>W</th>
                                <th>L</th>
                                <th>D</th>
                            </tr>
                            </thead>

                            <tbody>
                            <tr>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                                <td>0</td>
                            </tr>
                            </tbody>
                        </table>
                        <form action="/stats/search" method="post">
                            <input type="text" name="searchForPlayer" type="search" placeholder="Find opponent">
                            <input type="submit" value="Search">
                        </form>
                    </div>
                </div>
            </div>

        </c:when>
        <c:otherwise>
            <h1>Player not found</h1>
        </c:otherwise>
    </c:choose>


</div>

<script type="text/javascript">
    $(function() {

        var statsType = "${statsType}";
        console.log(statsType);

        if(statsType == "total") {
            $("#totalNavTab").addClass("active");
            $("#thgNavTab").removeClass("active");
            $("#ffaNavTab").removeClass("active");
            $("#1v1NavTab").removeClass("active");

        }

        if(statsType == "ffa") {
            $("#totalNavTab").removeClass("active");
            $("#thgNavTab").removeClass("active");
            $("#ffaNavTab").addClass("active");
            $("#1v1NavTab").removeClass("active");

        }

        if(statsType == "thg") {
            $("#totalNavTab").removeClass("active");
            $("#thgNavTab").addClass("active");
            $("#ffaNavTab").removeClass("active");
            $("#1v1NavTab").removeClass("active");

        }

        if(statsType == "1v1") {
            $("#totalNavTab").removeClass("active");
            $("#thgNavTab").removeClass("active");
            $("#ffaNavTab").removeClass("active");
            $("#1v1NavTab").addClass("active");

        }



    });
</script>

</body>
</html>
