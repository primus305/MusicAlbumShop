<%-- 
    Document   : index
    Created on : Sep 16, 2019, 11:36:01 PM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    </head>
    <body>
        <jsp:include page="include/menu.jsp"/>
        <div class="jumbotron wow animated rotateInDownLeft">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">

                    </div>

                </div>
            </div>
        </div>
        <section id="middle ">
            <div class="container wow animated rotateInDownLeft">
                <div class="col-sm-6 col-md-4"> 
                    <div class="thumbnail novostThumbDiv"> 
                        <img alt="100%x200" data-src="holder.js/100%x200" style="height: 300px; width: 100%; display: block;" src="<c:url value="/resources/images/1.jpg" />" data-holder-rendered="true"> 
                        <div class="caption"> 
                            <h3 class="vesti">Mozak jazz sviraca vidi muziku kao jezik </h3> 
                            <p> <a href="https://www.nbcnews.com/science/science-news/jazz-players-brains-see-music-language-n35601" class="btn btn-default" role="button">Vise..</a></p> 
                        </div> 
                    </div> 
                </div> 
                <div class="col-sm-6 col-md-4"> 
                    <div class="thumbnail novostThumbDiv"> 
                        <img alt="100%x200" data-src="holder.js/100%x200" style="height: 300px; width: 100%; display: block;" src="<c:url value="/resources/images/2.jpg" />" data-holder-rendered="true"> 
                        <div class="caption"> 
                            <h3 class="vesti">Jethro Tull u Beogradu!</h3> 
                            <p> <a href="http://rs.n1info.com/Showbiz/a390350/Jethro-Tull-u-Beogradu.html" class="btn btn-default" role="button">Vise..</a></p> 
                        </div> 
                    </div> 
                </div> 
                <div class="col-sm-6 col-md-4"> 
                    <div class="thumbnail novostThumbDiv"> 
                        <img alt="100%x200" data-src="holder.js/100%x200" style="height: 300px; width: 100%; display: block;" src="<c:url value="/resources/images/3.jpg" />" data-holder-rendered="true"> 
                        <div class="caption"> 
                            <h3 class="vesti">Miles Davis proglasen za najboljeg jazz umetnika</h3> 
                            <p> <a href="https://www.bbc.com/news/entertainment-arts-34827355" class="btn btn-default" role="button">Vise..</a></p> 
                        </div> 
                    </div> 
                </div> 

            </div>
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
        </section>

        <script src="<c:url value='/resources/js/cart.js'/>"></script>
</body>
<jsp:include page="include/footer.jsp"/>
</html>
