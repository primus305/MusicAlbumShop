<%-- 
    Document   : registration
    Created on : Sep 16, 2019, 5:14:33 PM
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
        <div class="registerbox">
            <img src="<c:url value="/resources/images/avatar.jpg" />" class="avatar">
            <h1>Registration</h1>
            <form:form method="POST"  action="/albumshop/register" id="registerForm" modelAttribute="registrationForm">

                <form:label path="username">Username </form:label>
                <form:input path="username" placeholder="Enter username"/><br/>
                <form:label path="password">Password </form:label>
                <form:password path="password" placeholder="Enter password"/><br/>
                <form:label path="firstname">First name </form:label>
                <form:input path="firstname" placeholder="Enter firstname"/><br/>
                <form:label path="lastname">Last name </form:label>
                <form:input path="lastname" placeholder="Enter lastname"/><br/>
                <form:label path="email">Email </form:label>
                <form:input path="email" placeholder="Enter email"/><br/>
                <input type="submit" name="register" value="Register"/>
            </form:form>
        </div>

    </body>
</html>
