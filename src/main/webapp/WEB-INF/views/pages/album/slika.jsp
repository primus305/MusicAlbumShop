<%-- 
    Document   : slika
    Created on : Aug 30, 2019, 6:59:42 PM
    Author     : rancha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${exception ne null}">
    <div class="error">${exception.message}</div>
</c:if>


<div class="modal fade" id="imgModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/albumshop/album/promeniSliku" method="POST" id="addAlbumForm" enctype="multipart/form-data">
                <div class="modal-header">
                    <button class="close" type="button" onclick="closeModal()" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>

                </div>
                <div class="modal-body">

                    <div class="container-fluid">
                        <div class="row">
                            <span id="modalErrors" class="bg-danger"></span>
                            <div class="col-sm-3">

                            </div>
                            <div class="col-sm-6">
                                <div class="center-block">
                                    <input type="hidden" name="albumID" value="${albumZaPromenuSlike.id}">
                                    <input type="file" name="file"/>

                                    <!--<img src="<c:url value="${albumZaPromenuSlike.slika}" />"  class="details img-responsive">-->
                                </div>
                            </div>
                            <div class="col-sm-3">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="submit"></span>Update picture</button>

                    <input type="button" class="btn btn-warning" onclick="closeModal()" value="Close"></input>
                </div>
            </form>
        </div>
    </div>
</div>
