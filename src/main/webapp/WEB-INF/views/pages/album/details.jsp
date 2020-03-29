<%-- 
    Document   : details
    Created on : Aug 16, 2019, 7:01:56 PM
    Author     : rancha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${exception ne null}">
    <div class="error">${exception.message}</div>
</c:if>


<div class="modal fade" id="albumModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/albumshop/cart/addItem" method="POST" id="addAlbumForm">
                <div class="modal-header">
                    <button class="close" type="button" onclick="closeModal()" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                    <h4 class="modal-title text-center">${albumDetail.naziv}</h4>
                </div>
                <div class="modal-body">
                    
                    <div class="container-fluid">
                        <div class="row">
                            <span id="modalErrors" class="bg-danger"></span>
                            <div class="col-sm-6">
                                <div class="center-block">
                                    <img src="<c:url value="${albumDetail.slika}" />"  class="details img-responsive">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <h4>Details</h4>
                                <hr>
                                <p>Artist: ${albumDetail.izvodjac}</p>                                                        
                                <p>Genre: ${albumDetail.zanr.toString().replace('_','/')}</p>
                                <p>Release date: ${albumDetail.datumIzdavanja}</p>
                                <p>Price: ${albumDetail.cena} ${sessionScope.valuta}</p>
                                <input type="hidden" name="albumID" value="${albumDetail.id}">

                                <div class="form-group">
                                    <div class="col-xs-6">
                                        <label for="quantity">Quantity</label>
                                        <input type="number" class="form-control" id="quantity" name="quantity" min="0" max="5">
                                    </div> <div class="col-xs-6"></div>
                                    <div class="col-xs-6">
                                        <label for="available">Available</label>
                                        <input type="text" readonly class="form-control" id="available" name="available" value="${albumDetail.dostupnaKolicina}">
                                    </div> <div class="col-xs-6"></div>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">

                    <button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-shopping-cart" style="color: white;"></span> Add To Cart</button>
                    <input type="button" class="btn btn-danger" onclick="closeModal()" value="Close"></input>
                </div>
            </form>
        </div>
    </div>
</div>


