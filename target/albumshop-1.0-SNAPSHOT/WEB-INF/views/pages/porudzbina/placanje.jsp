<%-- 
    Document   : placanje
    Created on : Aug 23, 2019, 2:24:50 AM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="${payPalConfig.posturl}">
            <input type="hidden" name="upload" value="1"/>
            <input type="hidden" name="return" value="${payPalConfig.returnurl}"/>
            <input type="hidden" name="cmd" value="_cart"/>
            <input type="hidden" name="business" value="${payPalConfig.business}"/>

            <c:forEach items="${sessionScope.shoppingCart.getStavkeDTOs()}" var="stavka"> 
                <input type="hidden" name="item_number_${stavka.redniBroj}" value="${stavka.redniBroj}"/>
                <input type="hidden" name="item_name_${stavka.redniBroj}" value="${stavka.album.naziv}"/>
                <input type="hidden" name="amount_${stavka.redniBroj}" value="${stavka.cena}"/>
                <input type="hidden" name="quantity_${stavka.redniBroj}" value="${stavka.quantity}"/>
                
            </c:forEach>
<!--                <input type="submit" value="PayPal" />-->
                <input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" />
        </form>
                <hr>
                <h1>OR</h1> 
                <hr>
                <a href="/albumshop/porudzbina/success">[payment on delivery]</a>
    </body>
</html>
