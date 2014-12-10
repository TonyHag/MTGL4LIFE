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
    <jsp:include page="include.jsp" />

    <title>Create Leaderboard</title>
</head>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="user" value="${user}"></jsp:param>
</jsp:include>

<div class="container" role="main" >

    <div class="row text-center pad-top ">
        <div class="col-md-12">
            <h2>Create Leaderboard</h2>
        </div>
    </div>

    <div class="row pad-top">
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
            <div class="panel panel-default">

                <div class="panel-body">
                    <form action="/leaderboard/create" method="post">
                        <div class="form-group input-group">
                            <input type="text" name="name" class="form-control" placeholder="Leaderboard name"/>
                        </div>

                        <div class="form-group input-group">
                            <textarea name="description" rows="2" class="form-control" placeholder="Description" ></textarea>
                        </div>

                        <div class="form-group input-group">
                            <input type="submit" value="Create" class="btn  btn-lg btn-primary"/>
                        </div>
                    </form>
                </div>


            </div>

        </div>
    </div>



</div>


</body>
</html>
