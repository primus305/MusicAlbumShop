<%-- 
    Document   : registerConfirmation
    Created on : Sep 24, 2019, 11:11:26 PM
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
        <div class="loginboxSuccess">
            <img src="<c:url value="/resources/images/mail.png" />" class="success">
            <br><br><br><br>
            <p class="mass">
                <c:if test="${param.msgRegConfirmation != ''}" >
                <div class="bg-success">
                    <p class="text-center text-success">
                        ${param.msgRegConfirmation}
                    </p>
                </div>
            </c:if>

        </p>     
    </div>
    </body>
</html>
