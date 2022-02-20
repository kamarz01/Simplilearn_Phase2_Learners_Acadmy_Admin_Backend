<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String userName = (String) session.getAttribute("username");
    if (userName != null){
        response.sendRedirect("home.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.html" %>
    <title>Zaghloul Academy Admin Login</title>
</head>
<body class="login-page" style="min-height: 466px;">
<div class="login-box">
    <div class="card card-outline card-primary">
        <div class="card-header text-center">
            <a href="#" class="h1"><b>Zaghloul</b>Academy</a>
        </div>
        <div class="card-body">
            <p class="login-box-msg">Sign in to start your session</p>
            <form action="login" method="post">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Username" name="username" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-envelope"></span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <input type="password" class="form-control" placeholder="Password" name="password" required>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-8">
                    </div>
                    <div class="col-4">
                        <button type="submit" class="btn btn-primary btn-block">Sign In</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <c:if test="${error == true}">
                            <p style="color: red;text-align: center;margin-top: 15px">Invalid username or password.</p>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>

    </div>

</div>
<%@ include file="/WEB-INF/common/scripts.html" %>
</body>
</html>