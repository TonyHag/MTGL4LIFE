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
        <p></p>
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
                <img class="profile-img" src="http://i426.photobucket.com/albums/pp349/farris31/Mtgsupreme-2.png?t=1420170873" alt="" height="40" width="156">
                <form class="form-signin" action="/login" method="post">
                    <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                    <input type="password" class="form-control" name="password" placeholder="Password" required>
                    <p>${loginError}</p>
                    <button class="btn btn-lg btn-success btn-block" type="submit">
                        Log in</button>
                    <label class="checkbox pull-left">
                        <input type="checkbox" value="remember-me">
                        Remember me
                    </label>
                </form>
            </div>
            <a href="/register" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>
</body>
</html>
