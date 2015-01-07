<%--
  Created by IntelliJ IDEA.
  User: eirikskogland
  Date: 02.12.14
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="include.jsp" />

    <title>Register</title>
</head>
<body>



<!--<form action="/register" method="post">
    <table>
        <tr> <td>Username: </td>        <td><input type="text" name="username" value="${validUsername}"></td>   <td>${usernameError}</td></tr>
        <tr> <td>Password: </td>        <td><input type="password" name="password1"></td>                       <td>${password1Error}</td></tr>
        <tr> <td>Re-type password: </td><td><input type="password" name="password2"></td>                       <td>${password2Error}</td></tr>
        <tr> <td>email: </td>           <td><input type="email" name="email" value="${validEmail}"></td>        <td>${emailError}</td></tr>
        <tr> <td><input type="submit" value="Register"> </td> </tr>
    </table>

</form>-->

<div class="container">
    <div class="row text-center pad-top ">
        <div class="col-md-12">
            <h2>Register</h2>
        </div>
    </div>
    <div class="row  pad-top">

        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>   Register Yourself </strong>
                </div>
                <div class="panel-body">
                    <form role="form" action="/register" method="post">
                        <br/>

                        <p>${usernameError}</p>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                            <input type="text" class="form-control" name="username" value="${validUsername}" placeholder="Desired Username" />
                        </div>

                        <p>${emailError}</p>
                        <div class="form-group input-group">
                            <span class="input-group-addon"></span>
                            <input type="email" class="form-control" name="email" value="${validEmail}" placeholder="Your Email" />
                        </div>


                        <p>${password1Error}</p>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                            <input type="password" class="form-control" name="password1" placeholder="Desired Password" />
                        </div>


                        <p>${password2Error}</p>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                            <input type="password" class="form-control" name="password2" placeholder="Retype Password" />
                        </div>

                        <p>${country}</p>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                            <input type="text" class="form-control" name="country" placeholder="Country" />
                        </div>

                        <p>${city}</p>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                            <input type="text" class="form-control" name="city" placeholder="City" />
                        </div>


                        <input class="btn btn-success" type="submit" value="Register">

                        <hr />
                        Already Registered ?  <a href="/login" >Login here</a>
                    </form>
                </div>

            </div>
        </div>


    </div>
</div>
</body>
</html>
