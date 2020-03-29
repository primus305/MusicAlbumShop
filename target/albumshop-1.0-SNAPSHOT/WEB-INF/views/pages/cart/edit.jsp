<%-- 
    Document   : edit
    Created on : Aug 19, 2019, 3:05:21 PM
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
        <link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <jsp:include page="../include/menu.jsp"/>
        <section id="contact wow animated rotateInDownLeft">
            <div class="container-fluid" style="min-height: 460px;">
                <div class="myShopingCartNaslov center">Moja potrosacka korpa</div>
                <table class="table tabelaCart">
                    <tr class="tabelaCartHeader">
                        <th>Redni broj</th>
                        <th>Album</th>
                        <th >Cena bez PDV-a</th>
                        <th >PDV</th>
                        <th> Cena + PDV</th>
                        <th>Valuta</th>
                        <th>Kolicina</th>
                        <th ></th>
                        <th>Ukupna cena</th>
                        <th></th>
                    </tr>

                    <c:forEach items="${sessionScope.shoppingCart.getStavkeDTOs()}" var="stavka"> 
                        <tr class="tabelaCartRow">
                            <td>${stavka.redniBroj}</td>
                            <td>${stavka.album.naziv}</td>
                            <td>${stavka.cena}</td>
                            <td>${stavka.album.poreskaStopa.iznos * 100} %</td>
                            <td>${stavka.ukupnaCena}</td>
                            <td>${sessionScope.valuta}</td>

                        <form action="/albumshop/cart/item/update/${stavka.redniBroj}" method="GET">
                            <td><input type="number" value="${stavka.quantity}" name="quantity" style="width: 45px;">
                                <button type="submit" class="btn btn-success" style="margin-left: 0px;"><span class="glyphicon glyphicon-floppy-disk" style="color: white;"></span></button></td>

                        </form>
                        <td></td>
                        <td>${stavka.ukupnaCena * stavka.quantity} <a href="/albumshop/cart/item/delete/${stavka.redniBroj}" class="btn btn-xs btn-default btnCnt pull-right"><span class="glyphicon glyphicon-remove" style="color: white;"></span></a></td>
                        </tr>


                    </c:forEach>
                    <tr class="tabelaCartRow">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td style="text-align: right;">Ukupan iznos: </td>

                        <td>${sessionScope.shoppingCart.getUkupanIznos()}</td>
                        <td></td>
                    </tr>
                    <tr class="ukupnoPdv">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td style="text-align: right;">Ukupan iznos + PDV:</td>

                        <td>${sessionScope.shoppingCart.getUkupanIznosPDV()}</td>
                        <td></td>
                    </tr>
                </table>
                <div class="row">
                    <div class="col-md-3">
                        <a type="button" class="btn btn-danger" href="/albumshop/porudzbina/add" style="margin-left: 0;"> Cancel</a>
                    </div>
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-3">
                    </div>
                    <div class="col-md-3">
                        <a type="button" class="btn btn-success" href="/albumshop/cart/save" style="margin-left: 180px;"><span class="glyphicon glyphicon-floppy-disk" style="color: white;"></span> Save changes</a>
                    </div>
                </div>

            </div>
        </section>
        <jsp:include page="../include/cart.jsp"/>
        <script src="<c:url value='/resources/js/cart.js'/>"></script>
    </body>
    <jsp:include page="../include/footer.jsp"/>
</html>
