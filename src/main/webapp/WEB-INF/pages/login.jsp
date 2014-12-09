<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 02.12.14
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Login</title>

</head>
<body>



<!--<form action="/login" method="post">

    <table>
        <p>${loginError}</p>
        <tr><td>Username: </td> <td><input type="text" name="username"/></td></tr>
        <tr><td>Password: </td> <td><input type="password" name="password" /></td></tr>
        <tr><td><input type="submit" value="Log in" /></td> <td>or <a href="/register">register here!</a></td> </tr>
    </table>

</form>-->



<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue to <p class="mtgsupreme">MTGSupreme</p></h1>
            <div class="account-wall">
                <img class="profile-img" src="data:image/jpeg;base64,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"
                     alt="" height="60" width="209">
                <form class="form-signin" action="/login" method="post">
                    <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                    <input type="password" class="form-control" name="password" placeholder="Password" required>
                    <button class="btn btn-lg btn-success btn-block" type="submit">
                        Log in</button>
                    <label class="checkbox pull-left">
                        <input type="checkbox" value="remember-me">
                        Remember me
                    </label>
                    <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                </form>
            </div>
            <a href="/register" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>
</body>
</html>
