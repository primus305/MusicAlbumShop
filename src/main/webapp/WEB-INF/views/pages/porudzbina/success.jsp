<%-- 
    Document   : success
    Created on : Aug 23, 2019, 2:30:25 AM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"  type="text/css">
        <link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet"  type="text/css">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic' rel='stylesheet' type='text/css'>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../include/menu.jsp"/>
        <section id="contact wow animated rotateInDownLeft">
            <div class="container-fluid">
                <c:if test="${orderSuccessMsg != ''}" >
                    <div class="bg-success">
                        <p class="text-center text-danger">
                            ${orderSuccessMsg}
                        </p>
                    </div>
                </c:if>
                <form action="/albumshop/porudzbina/gotovo" method="post" id="payment-form">
                    <div class="form-group col-md-12">
                        <label class="porudzbinaKrajnja" for="ime">Ime</label>
                        <input class="form-control" readonly type="text" id="ime" name="ime" value="<security:authentication property="principal.firstname" />">
                    </div>
                    <div class="form-group col-md-12">
                        <label class="porudzbinaKrajnja" for="prezime">Prezime</label>
                        <input class="form-control" readonly type="text" id="prezime" name="prezime" value="<security:authentication property="principal.lastname" />">
                    </div>
                    
                    <div class="form-group col-md-12">
                        <label class="porudzbinaKrajnja" for="adresa">Adresa dostave:</label>
                        <input id="adresa" type="text" name="adresa" class="form-control" value="${orderSuccess.drzava}, ${orderSuccess.postanskiBroj} ${orderSuccess.grad}, ${orderSuccess.adresa}" readonly="true"/>
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

                        <c:forEach items="${orderSuccess.potrosackaKorpa.getStavkeDTOs()}" var="stavka"> 
                            <tr class="tabelaCartRow">
                                <td>${stavka.redniBroj}</td>
                                <td>${stavka.album.naziv}</td>
                                <td>${stavka.cena}</td>
                                <td>${stavka.album.poreskaStopa.iznos * 100} %</td>
                                <td>${stavka.ukupnaCena}</td>
                                <td>${orderSuccess.potrosackaKorpa.valuta.toString()}</td>
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
                            <td>Ukupan iznos: </td>
                            <td>${orderSuccess.potrosackaKorpa.getUkupanIznos()} ${orderSuccess.potrosackaKorpa.valuta.toString()}</td>
                        </tr>
                        <tr class="ukupnoPdv">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Ukupan iznos + PDV:</td>
                            <td>${orderSuccess.potrosackaKorpa.getUkupanIznosPDV()} ${orderSuccess.potrosackaKorpa.valuta.toString()}</td>
                        </tr>
                    </table>

                    <div class="row">
                        <div class="form-group col-md-3" >
                            <button id="save" class="btn btn-default" style="margin-left: 10px;">
                                <span class="glyphicon glyphicon-arrow-left" style="color: white;"></span>    Vrati se na pocetnu
                            </button>
                        </div>
                        <div class="form-group col-md-3 pull-right" style="margin-right: 0px; display: inline;">
                            <!--<label class="porudzbinaKrajnja">Placanje:</label>-->
                            <c:if test="${orderSuccess.payed == true}">
                                <img alt="100%x200" style="height: 150px; width: 150px; display: inline; margin-left: 80px;" src="<c:url value="/resources/images/success.png" />">
                                <p class="pull-right porudzbinaKrajnja" style="margin-right: 125px; margin-top: 0px;">Placeno</p>
                            </c:if>
                            <c:if test="${orderSuccess.payed == false}">
                                <label class="porudzbinaKrajnja" for="payed">Placanje prilikom dostave</label>
                            </c:if>

                        </div>
                    </div>


                </form>
            </div>
        </section>
        <jsp:include page="../include/cart.jsp"/>
        <script src="<c:url value='/resources/js/cart.js'/>"></script>
    </body>
    <jsp:include page="../include/footer.jsp"/>
</html>
