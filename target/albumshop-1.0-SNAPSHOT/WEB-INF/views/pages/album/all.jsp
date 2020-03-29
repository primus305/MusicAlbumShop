<%-- 
    Document   : all
    Created on : Aug 14, 2019, 3:36:55 PM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP view all albums!</title>

        <link href="<c:url value='/resources/css/bootstrap.css'/>" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"  type="text/css"> 
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet"  type="text/css"> 
        <link href='http://fonts.googleapis.com/css?family=Tangerine:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <jsp:include page="../include/menu.jsp"/>

        <security:authorize access="hasAuthority('ADMINISTRATOR')">

            <c:if test="${param.albumUnos != ''}" >
                <div class="bg-success">
                    <p class="text-center text-danger">
                        ${param.albumUnos}
                    </p>
                </div>
            </c:if>

            <c:if test="${poruka != null}" >
                <div class="bg-success">
                    <p class="text-center text-danger">
                        ${poruka}
                    </p>
                </div>
            </c:if>

            <div class="container">
                <h1>Albumi</h1>
                <table class="table table-striped">
                    <tr>
                        <th>Picture</th>
                        <th>Name</th>
                        <th>Release date</th>
                        <th>Genre</th>
                        <th>List Price</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Artist</th>
                        <th></th>
                    </tr>

                    <c:forEach items="${albums}" var="album"> 
                        <tr>
                            <td><img alt="100%x200" style="height: 80px; width: 80px; display: block;" src="<c:url value="${album.slika}" />" data-holder-rendered="true"></td>
                            <td style="vertical-align: middle;">${album.naziv}</td>
                            <td style="vertical-align: middle;">${album.datumIzdavanja}</td>
                            <td style="vertical-align: middle;">${album.zanr.toString().replace('_','/')}</td>
                            <td style="vertical-align: middle;">${album.staraCena}</td>
                            <td style="vertical-align: middle;">${album.cena}</td>
                            <td style="vertical-align: middle;">${album.dostupnaKolicina}</td>
                            <td style="vertical-align: middle;">${album.izvodjac}</td>
                            <td style="vertical-align: middle;"> 
                                <a href="/albumshop/album/get/${album.id}" class="btn btn-xs btn-default btnCnt"><span class="glyphicon glyphicon-pencil" style="color: white;"></span></a>
                                <a href="/albumshop/album/delete/${album.id}" class="btn btn-xs btn-default btnCnt"><span class="glyphicon glyphicon-trash" style="color: white;"></span></a>
                            </td>

                        </tr>

                    </c:forEach>
                    <c:if test="${totalPages > 0}">
                        <ul class="pagination pagination-sm justify-content-center pull-right" style="margin-right: 20px;">
                            <!-- Link of the first page -->
                            <li class='page-item <c:if test="${page <= 1}">disabled</c:if>'>
                                    <a class='page-link' href='/albumshop/album/all/1'><<</a>
                                </li>
                                <!-- Link of the previous page -->
                                <li class='page-item <c:if test="${page <= 1}">disabled</c:if>'>
                                <a class='page-link' href='/albumshop/album/all/${(page>1 ? (page-1) : 1)}'><</a>
                            </li>
                            <!-- Links of the pages with page number -->
                            <c:forEach items="${pageNumbers}" var="i"> 
                                <li class='page-item <c:if test="${i == page}">active</c:if>'>
                                    <a class='page-link' href='/albumshop/album/all/${i}'>${i}</a>
                                </li>
                            </c:forEach>
                            <!-- Link of the next page -->
                            <li class='page-item <c:if test="${page >= totalPages}">disabled</c:if>'>
                                <a class='page-link' href='/albumshop/album/all/${(page < totalPages ? (page+1) : totalPages)}'>></a>
                            </li>
                            <!-- Link of the last page -->
                            <li class='page-item <c:if test="${page >= totalPages}">disabled</c:if>'>
                                <a class='page-link' href='/albumshop/album/all/${totalPages}'>>>                      
                                </a>
                            </li>
                        </ul>
                    </c:if>
                </table>

                <a type="button" class="btn btn-default" href="/albumshop/album/add" style="margin-left: 970px;"><span class="glyphicon glyphicon-plus-sign" style="color: white;"></span> Add new album</a>
                <form action="/albumshop/album/search" method="post" class="form-inline text-center" >
    
                    <input type="text" name="min_price" class="price_range" placeholder="MinCena"> To
                    <input type="text" name="max_price" class="price_range" placeholder="MaxCena"> 
                    
                    <br><br>
                    
                    <c:forEach items="${sessionScope.zanrovi}" var="zanrEnum"> 
                        <input type="radio" name="zanr" value="${zanrEnum}" > ${zanrEnum}  
                    </c:forEach>
                    <br>
                    <button type="submit" class="btn btn-xs btn-default" style="margin-left: 10px; margin-top: 20px;">
                        <span class="glyphicon glyphicon-search" style="color: white;"></span> Search
                    </button>
                </form>
            </div>

            <br><br>
        </security:authorize>
        <security:authorize access="hasAuthority('OBICAN_KORISNIK')">
            <section id="contact wow animated rotateInDownLeft">
                <div class="container-fluid" style="min-height: 480px;">
                    <c:if test="${param.err != ''}" >
                        <div class="bg-danger">
                            <p class="text-center text-danger">
                                ${param.err}
                            </p>
                        </div>
                    </c:if>
                    <c:if test="${param.porukaUspesnosti != ''}" >
                        <div class="bg-success">
                            <p class="text-center text-danger">
                                ${param.porukaUspesnosti}
                            </p>
                        </div>
                    </c:if>
                    <c:if test="${param.albumDelete != ''}" >
                        <div class="bg-success">
                            <p class="text-center text-danger">
                                ${param.albumDelete}
                            </p>
                        </div>
                    </c:if>

                    <div class="col-md-2">
                        <c:if test="${poruka != null}" >
                            <div class="bg-danger">
                                <p class="text-center text-danger">
                                    ${poruka}
                                </p>
                            </div>
                        </c:if>

                        <form action="/albumshop/album/search" method="post">
                            <h3 class="text-center pretraz">Cena</h3>
                            <br><br>
                            <input type="text" name="min_price" class="price_range" placeholder="Min"> <div class="priceTo">to</div>
                            <input type="text" name="max_price" class="price_range" placeholder="Max"> <br><br>
                            <h3 class="text-center pretraz">Zanr</h3>
                            <br><br>
                            <c:forEach items="${sessionScope.zanrovi}" var="zanrEnum"> 
                                <input  type="radio" name="zanr" value="${zanrEnum}" > <div class="zanr">${zanrEnum}</div> <br>
                            </c:forEach>
                            <br>
                            <button type="submit" value="Search" class="btn btn-sm btn-success" style="margin-left: 52px;">
                                <span class="glyphicon glyphicon-search" style="color: white;"></span> Search
                            </button>
                        </form>

                    </div>
                    <div class="col-md-9" >
                        <div class="row">
                            <c:forEach items="${albums}" var="album"> 

                                <div class="col-sm-6 col-md-3">
                                    <div class="thumbnail albumThumbDiv" style="padding-bottom: 0;">
                                        <img alt="100%x200" style="height: 210px; width: 100%; display: block;" src="<c:url value="${album.slika}" />" data-holder-rendered="true"> 
                                        <div class="caption"> 


                                            <p class="albumThumb">${album.naziv}</p>
                                            <p class="albumThumb">${album.izvodjac}</p>
                                            <p class="text-center list-price text-danger parAlbum" style="margin-top: 0; margin-bottom: 0;"><b>List Price:</b> <s>${album.staraCena} ${sessionScope.valuta}</s></p>
                                            <p class="text-center price parAlbum" style="margin-top: 0px;"> Our price: ${album.cena} ${sessionScope.valuta}</p>
                                            <p class="albumThumb"><button type="button" class="btn btn-sm btn-default" data-toggle="modal" onclick="openModal(${album.id});" style="margin-top: 1px; margin-left: 6px; margin-bottom: 0;">
                                                    Details
                                                </button></p> 
                                        </div> 
                                    </div> 
                                </div> 


                            </c:forEach>
                        </div>
                        <c:if test="${totalPages > 0}">
                            <ul class="pagination pagination-sm justify-content-center pull-right">
                                <!-- Link of the first page -->
                                <li class='page-item <c:if test="${page <= 1}">disabled</c:if>'>
                                        <a class='page-link' href='/albumshop/album/all/1'><<</a>
                                    </li>
                                    <!-- Link of the previous page -->
                                    <li class='page-item <c:if test="${page <= 1}">disabled</c:if>'>
                                    <a class='page-link' href='/albumshop/album/all/${(page>1 ? (page-1) : 1)}'><</a>
                                </li>
                                <!-- Links of the pages with page number -->
                                <c:forEach items="${pageNumbers}" var="i"> 
                                    <li class='page-item <c:if test="${i == page}">active</c:if>'>
                                        <a class='page-link' href='/albumshop/album/all/${i}'>${i}</a>
                                    </li>
                                </c:forEach>
                                <!-- Link of the next page -->
                                <li class='page-item <c:if test="${page >= totalPages}">disabled</c:if>'>
                                    <a class='page-link' href='/albumshop/album/all/${(page < totalPages ? (page+1) : totalPages)}'>></a>
                                </li>
                                <!-- Link of the last page -->
                                <li class='page-item <c:if test="${page >= totalPages}">disabled</c:if>'>
                                    <a class='page-link' href='/albumshop/album/all/${totalPages}'>>>                      
                                    </a>
                                </li>
                            </ul>
                        </c:if>
                    </div>
                    <div class="col-md-1">

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
        </security:authorize>


        <jsp:include page="../include/footer.jsp"/>
    </body>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
    <script type="text/javascript">
                                                function openModal(id) {
                                                    var data = {"id": id};
                                                    jQuery.ajax({
                                                        url: '/albumshop/album/details/' + id,
                                                        method: "get",
                                                        data: data,
                                                        success: function (data) {
                                                            jQuery('body').append(data);
                                                            jQuery('#albumModal').modal('toggle');
                                                        },
                                                        error: function () {
                                                            alert("Greska!");
                                                        }
                                                    });

                                                }
                                                function closeModal() {
                                                    jQuery("#albumModal").modal("hide");
                                                    setTimeout(function () {
                                                        jQuery("#albumModal").remove();
                                                        jQuery(".modal-backdrop").remove();
                                                    }, 500);
                                                }


    </script>

</html>
