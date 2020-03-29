<%-- 
    Document   : myCart
    Created on : Aug 19, 2019, 12:04:27 AM
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
        <link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet"  type="text/css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Tangerine:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic' rel='stylesheet' type='text/css'>
    </head>
    <body >
        <jsp:include page="../include/menu.jsp"/>
        <c:if test="${sessionScope.shoppingCart == null}" >
            <div style="min-height: 500px;">
                <div class="bg-danger">
                    <p class="text-center text-danger">
                        Vasa potrosacka korpa je prazna!
                    </p>
                </div>
            </div>

        </c:if>
        <c:if test="${sessionScope.shoppingCart != null}" >
            <section id="contact wow animated rotateInDownLeft">
                <div class="container-fluid" style="min-height: 460px;">
                    <div class="container" style="color: black;">
                        <div class="myShopingCartNaslov center">Moja potrosacka korpa</div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-3"></div>
                            <div class="col-md-3"></div>
                            <div class="form-group col-md-3">
                                <form class="form-inline pull-right" method="get" action="/albumshop/porudzbina/restapi">
                                    <select class="form-control" name="valuta" id="valuta" style="width: 120px; color: black;">
                                        <option></option>
                                        <option value="RSD">RSD</option>
                                        <option value="EUR">EUR</option>
                                        <option value="USD">USD</option>
                                        <option value="CHF">CHF</option>
                                        <option value="GBP">GBP</option>
                                    </select>
                                    <!--<input type="text" name="valuta">-->
                                    <div class="form-group">
                                        <input type="submit" name="add_submit" value="Promeni valutu" class="btn btn-default" style="margin-left: 0;">
                                    </div>
                                </form>
                            </div>

                        </div>

                        <table class="table tabelaCart">
                            <tr class="tabelaCartHeader">
                                <th>Redni broj</th>
                                <th>Album</th>
                                <th>Cena bez PDV-a</th>
                                <th>PDV</th>
                                <th>Cena + PDV</th>
                                <th>Valuta</th>
                                <th>Kolicina</th>
                                <th>Ukupna cena</th>
                            </tr>

                            <c:forEach items="${sessionScope.shoppingCart.getStavkeDTOs()}" var="stavka"> 
                                <tr class="tabelaCartRow">
                                    <td style="vertical-align: middle;">${stavka.redniBroj}</td>
                                    <td style="vertical-align: middle;">${stavka.album.naziv}</td>
                                    <td style="vertical-align: middle;">${stavka.cena}</td>
                                    <td style="vertical-align: middle;">${stavka.album.poreskaStopa.iznos * 100} %</td>
                                    <td style="vertical-align: middle;">${stavka.ukupnaCena}</td>
                                    <td style="vertical-align: middle;">${sessionScope.valuta}</td>
                                    <td style="vertical-align: middle;">${stavka.quantity}</td>
                                    <td style="vertical-align: middle;">${stavka.ukupnaCena * stavka.quantity}</td>

                                </tr>

                            </c:forEach>
                            <tr class="tabelaCartRow">
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td style="text-align: right;">Ukupan iznos: </td>
                                <td>${sessionScope.shoppingCart.getUkupanIznos()}</td>
                            </tr>
                            <tr class="ukupnoPdv">
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td style="text-align: right;">Ukupan iznos + PDV:</td>
                                <td>${sessionScope.shoppingCart.getUkupanIznosPDV()}</td>
                            </tr>
                        </table>
                    </div>
                    <a type="button" class="btn btn-success" href="/albumshop/cart/add" style="margin-left: 620px;"><span class="glyphicon glyphicon-send" style="color: white;"></span> Order now</a>

                </div>
            </section>
        </c:if>
        <jsp:include page="../include/cart.jsp"/>
        <script src="<c:url value='/resources/js/cart.js'/>"></script>
    </body>
    <jsp:include page="../include/footer.jsp"/>
</html>
