<%-- 
    Document   : badUser
    Created on : Sep 18, 2019, 12:11:18 AM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <div class="loginboxError">
            <img src="<c:url value="/resources/images/error.png" />" class="error" style="margin-top: 20px;">
            <br><br><br>
            <p class="mass">
                <c:if test="${param.neuspesnaRegistracija != ''}" >
                <div class="bg-danger">
                    <p class="text-center text-danger">
                        ${param.neuspesnaRegistracija}
                    </p>
                </div>
            </c:if>

        </p>     
        <a href="/albumshop/login"><button class="button button-block">Home</button></a>
    </body>
</html>
