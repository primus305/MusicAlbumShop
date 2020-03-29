<%-- 
    Document   : add
    Created on : Aug 19, 2019, 5:52:38 PM
    Author     : rancha
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${exception ne null}">
    <div class="error">${exception.message}</div>
</c:if>
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
            <div class="container-fluid">
                <c:if test="${param.korpaEdit != ''}" >
                    <div class="bg-success">
                        <p class="text-center text-danger">
                            ${param.korpaEdit}
                        </p>
                    </div>
                </c:if>
                <div class="myShopingCartNaslov center">Moja potrosacka korpa</div>
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
                            <td>${stavka.redniBroj}</td>
                            <td>${stavka.album.naziv}</td>
                            <td>${stavka.cena}</td>
                            <td>${stavka.album.poreskaStopa.iznos * 100} %</td>
                            <td>${stavka.ukupnaCena}</td>
                            <td>${sessionScope.valuta}</td>
                            <td>${stavka.quantity}</td>
                            <td>${stavka.ukupnaCena * stavka.quantity}</td>

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
                <c:if test="${sessionScope.order == null}" >
                    <a type="button" class="btn btn-default pull-right" href="/albumshop/cart/edit" style="margin-left: 615px;"><span class="glyphicon glyphicon-pencil" style="color: white;"></span> Edit cart</a>
                    <br><br><br>
                    <form:form method="POST" action="/albumshop/porudzbina/save" modelAttribute="porudzbina">
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="form-group col-md-6 podaciPorudzbina">
                                <form:label path="drzava">Drzava*:</form:label>
                                <form:input type="text" class="form-control" path="drzava" />
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="form-group col-md-6 podaciPorudzbina">
                                <form:label path="grad">Grad*:</form:label>
                                <form:input type="text" class="form-control" path="grad" />
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="form-group col-md-6 podaciPorudzbina">
                                <form:label path="adresa">Adresa*:</form:label>
                                <form:input type="text" path="adresa" class="form-control" />
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="form-group col-md-6 podaciPorudzbina">
                                <form:label path="postanskiBroj">Postanski broj*:</form:label>
                                <form:input type="number" path="postanskiBroj" class="form-control" />
                            </div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <button id="save" class="btn btn-success" style="margin-left: 615px;">
                                    <span class="glyphicon glyphicon-ok" style="color: white;"></span> Complete order
                                </button>
                            </div>
                        </div>


                    </form:form>
                </c:if>
                <c:if test="${sessionScope.order != null}" >
                    <br><br>
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-2"></div>
                        <div class="placanjebox col-md-4">
                            <br>
                            <img src="<c:url value="/resources/images/money.jpg" />" class="money">
                            <br><br><br><br>
                            <h1 style="color: black;">Izaberite nacin placanja</h1>
                            <form method="POST" action="${payPalConfig.posturl}">
                                <input type="hidden" name="upload" value="1"/>
                                <input type="hidden" name="return" value="${payPalConfig.returnurl}"/>
                                <input type="hidden" name="cmd" value="_cart"/>
                                <input type="hidden" name="business" value="${payPalConfig.business}"/>

                                <c:forEach items="${sessionScope.shoppingCart.getStavkeDTOs()}" var="stavka"> 
                                    <input type="hidden" name="item_number_${stavka.redniBroj}" value="${stavka.redniBroj}"/>
                                    <input type="hidden" name="item_name_${stavka.redniBroj}" value="${stavka.album.naziv}"/>
                                    <input type="hidden" name="amount_${stavka.redniBroj}" value="${stavka.ukupnaCena}"/>
                                    <input type="hidden" name="quantity_${stavka.redniBroj}" value="${stavka.quantity}"/>

                                </c:forEach>
                                <!--                <input type="submit" value="PayPal" />-->
                                <input class="finishPaypal" type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" />
                            </form>
                            <form method="GET" action="/albumshop/porudzbina/success">
                                <input type="submit" class="finishPayment" value="Placanje prilikom dostave"> 
                            </form>


                        </div>
                        <div class="col-md-2"></div>
                        <div class="col-md-2"></div>
                    </div>

                </c:if>

            </div>
        </section>
        <jsp:include page="../include/cart.jsp"/>
        <script src="<c:url value='/resources/js/cart.js'/>"></script>
    </body>
    <jsp:include page="../include/footer.jsp"/>

</html>
