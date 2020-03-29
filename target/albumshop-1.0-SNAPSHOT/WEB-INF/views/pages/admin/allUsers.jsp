<%-- 
    Document   : allUsers
    Created on : Aug 16, 2019, 4:16:34 PM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"  type="text/css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../include/menu.jsp"/>
        <c:if test="${param.korisnikPromena != ''}" >
            <div class="bg-success">
                <p class="text-center text-danger">
                    ${param.korisnikPromena}
                </p>
            </div>
        </c:if>
        <div class="container" style="min-height: 500px;">
            <h1>Users</h1>
            <table class="table table-striped">
                <tr>
                    <th>Id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Username</th>
                    <th>E-mail</th>
                    <th>Admin</th>
                    <th></th>
                </tr>

                <c:forEach items="${users}" var="korisnik"> 
                    <tr>
                        <td>${korisnik.id}</td>
                        <td>${korisnik.firstname}</td>
                        <td>${korisnik.lastname}</td>
                        <td>${korisnik.username}</td>
                        <td>${korisnik.email}</td>
                        <c:if test="${korisnik.uloga == 'ADMINISTRATOR'}">
                            <td><input type="checkbox" checked></td>
                            </c:if>
                            <c:if test="${korisnik.uloga == 'OBICAN_KORISNIK'}">
                            <td><input type="checkbox" ></td>
                            </c:if>
                        <td><a href="/albumshop/admin/update/${korisnik.id}" class="btn btn-xs btn-default btnCnt"><span class="glyphicon glyphicon-floppy-disk" style="color:white;"></span></a></td>
                    </tr>

                </c:forEach>

            </table>
        </div>
    </body>
    <jsp:include page="../include/footer.jsp"/>
</html>
