<%-- 
    Document   : view
    Created on : Sep 23, 2019, 11:09:30 PM
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
        <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"  type="text/css">
        <link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet"  type="text/css"> 
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <jsp:include page="../include/menu.jsp"/>
        <section id="contact wow animated rotateInDownLeft">
            <div class="container-fluid" style="min-height: 460px;">
                <br><br>
                <div class="container" style="min-height: 500px;">
                    <form:form method="POST" action="" modelAttribute="viewOrder" enctype="multipart/form-data">

                        <div class="form-group col-md-12" hidden="true">
                            <form:label path="id">ID</form:label>
                            <form:input path="id" readonly="true"/><form:errors path="id" />
                        </div>
                        
                        <div class="form-group col-md-12" style="color: black;">
                            <form:label path="adresa">Adresa dostave:</form:label>
                            <form:input type="text" path="adresa" class="form-control" value="${viewOrder.drzava}, ${viewOrder.postanskiBroj} ${viewOrder.grad}, ${viewOrder.adresa}" readonly="true"/>
                        </div>
                        
                        <div class="form-group col-md-6" style="color: black;">
                            <form:label path="datumPorudzbine">Datum porudzbine:</form:label>
                            <form:input type="text" class="form-control" path="datumPorudzbine" readonly="true"/>
                        </div>
                        <div class="form-group col-md-6" style="color: black;">
                            <form:label path="datumDostave">Datum dostave:</form:label>
                            <form:input type="text" path="datumDostave" class="form-control" readonly="true"/>
                        </div>

                        <table class="table col-md-12 tabelaCart">
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
                            <c:forEach items="${viewOrder.potrosackaKorpa.getStavkeDTOs()}" var="stavka"> 
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
                                <td style="text-align: right;">Ukupan iznos:</td>
                                <td>${viewOrder.potrosackaKorpa.ukupanIznos}</td>
                            </tr>
                            <tr class="ukupnoPdv">
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td style="text-align: right;">Ukupan iznos + PDV:</td>
                                <td>${viewOrder.potrosackaKorpa.ukupanIznosPDV}</td>
                            </tr>
                        </table>

                        <div class="row">

                            <div class="form-group col-md-3">

                                <a type="button" href="/albumshop/porudzbina/all/1" id="update" class="btn btn-default" style="margin-left: 0;">
                                    <span class="glyphicon glyphicon-arrow-left" style="color: white;"></span> Nazad
                                </a>
                            </div> 

                            <div class="form-group col-md-3 pull-right" style="margin-right: 0px; display: inline;">
                                <c:if test="${viewOrder.payed == true}">
                                    <img alt="100%x200" style="height: 150px; width: 150px; display: inline; margin-left: 80px;" src="<c:url value="/resources/images/success.png" />">
                                    <p class="pull-right porudzbinaKrajnja" style="margin-right: 80px; margin-top: 0px;">Placeno</p>
                                </c:if>
                                <c:if test="${viewOrder.payed == false}">
                                    <label class="porudzbinaKrajnja" for="payed">Placanje prilikom dostave</label>

                                </c:if>

                            </div>
                        </div>


                    </form:form>


                </div>
            </div>
        </section>
        <jsp:include page="../include/cart.jsp"/>
        <script src="<c:url value='/resources/js/cart.js'/>"></script>
        <jsp:include page="../include/footer.jsp"/>
    </body>

</html>
