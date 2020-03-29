<%-- 
    Document   : cart
    Created on : Sep 24, 2019, 3:14:30 AM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div id="cart" class="cart">
    <br>
    <div class="text-center" style="color: black;">
        <c:if test="${sessionScope.shoppingCart.getStavkeDTOs().size() > 1}">
            Imate ${sessionScope.shoppingCart.getStavkeDTOs().size()} proizvoda u korpi.
        </c:if>
        <c:if test="${sessionScope.shoppingCart.getStavkeDTOs().size() == 1}">
            Imate ${sessionScope.shoppingCart.getStavkeDTOs().size()} proizvod u korpi.
        </c:if>
        <c:if test="${sessionScope.shoppingCart == null || sessionScope.shoppingCart.getStavkeDTOs().size() == 0}">
            Vasa korpa je prazna.
        </c:if>
    </div>
    <!--<table class="table">-->
    <c:forEach items="${sessionScope.shoppingCart.getStavkeDTOs()}" var="stavka"> 

        <div class="row cart-item">
            <div class="col-md-4 cart-image">
                <img alt="100%x200" style="height: 80px; width: 80px; display: block; margin-top: 10px; margin-left: 10px; " src="<c:url value="${stavka.album.slika}" />" data-holder-rendered="true">
            </div>
            <div class="col-md-6 cart-item-title">
                <p class="albumCart">${stavka.album.naziv}</p>
                <p class="kolicinaCart">Kolicina: ${stavka.quantity}</p>
                <p class="cenaCart">${stavka.cena} ${sessionScope.valuta}</p>
            </div>
            <div class="col-md-2" style="margin-top: 10px; margin-right: 0px;">
                <a href="/albumshop/cart/delete/${stavka.redniBroj}"> <span class="glyphicon glyphicon-remove"></span></a>
            </div>
        </div>


    </c:forEach>
    <!--</table>-->

    <div class="row ukupnaCenaCart">

        <div class="col-md-6 btnMojaKorpa">
            <a type="button" class="btn btn-primary" href="/albumshop/cart/myCart" style="width: 100%; margin-left: 10px;"><span class="glyphicon glyphicon-shopping-cart" style="color: white;"></span> Moja korpa</a>
        </div>
        <div class="col-md-6" >
            <div class="text-center" style="font-size: 17px; margin-top: 15px; margin-right: 15px; color: black;"><c:if test="${sessionScope.shoppingCart.getStavkeDTOs().size() > 0}">${sessionScope.shoppingCart.getUkupanIznos()} ${sessionScope.valuta}</c:if></div>
        </div>
    </div>
</div>
</div>
