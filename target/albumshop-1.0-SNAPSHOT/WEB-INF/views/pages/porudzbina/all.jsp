<%-- 
    Document   : all
    Created on : Aug 23, 2019, 7:43:38 PM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP view all albums!</title>

        <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"  type="text/css"> 
        <link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet"  type="text/css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic' rel='stylesheet' type='text/css'>
    </head>
    <body >
        <jsp:include page="../include/menu.jsp"/>
        <section id="contact wow animated rotateInDownLeft">
            <div class="container-fluid" style="min-height: 460px;">
                <br><br>
                <div class="myShopingCartNaslov center">Moje porudzbine</div>
                <div class="row" style="min-height: 380px;">
                    <div class="col-md-1">

                    </div>
                    <div class="col-md-10">
                        <table class="table tabelaCart">
                            <tr class="tabelaCartHeader">
                                <th>Datum porudzbine</th>
                                <th>Adresa</th>
                                <th>Iznos</th>
                                <th>Otvori</th>

                            </tr>
                            <c:forEach items="${orders}" var="order"> 
                                <tr class="tabelaCartRow">
                                    <td>${order.datumPorudzbine}</td>
                                    <td>${order.drzava}, ${order.postanskiBroj} ${order.grad}, ${order.adresa}</td>
                                    <td>${order.potrosackaKorpa.ukupanIznos} ${order.potrosackaKorpa.valuta.toString()}</td>
                                    <td>
                                        <a href="/albumshop/porudzbina/get/${order.id}" class="btn btn-xs btn-default btnCnt"><span class="glyphicon glyphicon-open" style="color: white;"></span></a>
                                    </td>
                                </tr>

                            </c:forEach>
                        </table>
                        <c:if test="${totalPages > 0}">
                            <ul class="pagination pagination-sm justify-content-center pull-right">
                                <!-- Link of the first page -->
                                <li class='page-item <c:if test="${page <= 1}">disabled</c:if>'>
                                        <a class='page-link' href='/albumshop/porudzbina/all/1'><<</a>
                                    </li>
                                    <!-- Link of the previous page -->
                                    <li class='page-item <c:if test="${page <= 1}">disabled</c:if>'>
                                    <a class='page-link' href='/albumshop/porudzbina/all/${(page>1 ? (page-1) : 1)}'><</a>
                                </li>
                                <!-- Links of the pages with page number -->
                                <c:forEach items="${pageNumbers}" var="i"> 
                                    <li class='page-item <c:if test="${i == page}">active</c:if>'>
                                        <a class='page-link' href='/albumshop/porudzbina/all/${i}'>${i}</a>
                                    </li>
                                </c:forEach>
                                <!-- Link of the next page -->
                                <li class='page-item <c:if test="${page >= totalPages}">disabled</c:if>'>
                                    <a class='page-link' href='/albumshop/porudzbina/all/${(page < totalPages ? (page+1) : totalPages)}'>></a>
                                </li>
                                <!-- Link of the last page -->
                                <li class='page-item <c:if test="${page >= totalPages}">disabled</c:if>'>
                                    <a class='page-link' href='/albumshop/porudzbina/all/${totalPages}'>>>                      
                                    </a>
                                </li>
                            </ul>
                        </c:if>
                    </div>
                    <div class="col-md-1">

                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="../include/cart.jsp"/>
        <script src="<c:url value='/resources/js/cart.js'/>"></script>
</body>

<jsp:include page="../include/footer.jsp"/>
</html>
