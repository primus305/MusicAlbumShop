<%-- 
    Document   : login
    Created on : Sep 16, 2019, 5:55:50 PM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="<c:url value='/resources/css/style1.css'/>" rel="stylesheet"  type="text/css">
    </head>
    <body>
        <div class="loginbox">

            <img src="<c:url value="/resources/images/avatar.jpg" />" class="avatar">
            <h1>Login</h1>
            <c:if test="${param.error != null}" >
                <div class="bg-danger">
                    <p class="text-center text-danger">
                        Pogresno korisnicko ime ili lozinka.
                    </p>
                </div>
            </c:if>
            <c:if test="${param.porukaRegisterConfirm != ''}" >
                <div class="bg-success">
                    <p class="text-center text-danger">
                        ${param.porukaRegisterConfirm}
                    </p>
                </div>
            </c:if>
            <form action="/albumshop/login" method="POST" id="loginForm">
                <p>Username</p>
                <input type="text" name="username" placeholder="Enter username" required>
                <p>Password</p>
                <input type="password" name="password" placeholder="Enter password" required>
                <input type="submit" name="login" value="Login">
                <div class="tab"><a href="/albumshop/registration">Registruj se besplatno!</a></div><br>
            </form>


        </div>
    </body>
</html>
