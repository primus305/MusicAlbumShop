<%-- 
    Document   : view
    Created on : Aug 14, 2019, 11:24:18 PM
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
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="../include/menu.jsp"/>
        <form:form method="POST" action="/albumshop/album/update" modelAttribute="viewAlbum" enctype="multipart/form-data">
            <h2 class="text-center">Izmena albuma</h2><hr>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6" hidden="true">
                    <form:label path="id">ID</form:label>
                    <form:input path="id" readonly="true"/><form:errors path="id" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <form:label path="naziv">Naziv*:</form:label>
                    <form:input type="text" class="form-control" path="naziv" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">

                    <label for="zanrCombo">Zanr*:</label>
                    <select class="form-control" name="zanrCombo" id="zanrCombo">
                        <c:forEach items="${sessionScope.zanrovi}" var="zanrEnum"> 
                            <option value="${zanrEnum}" <c:if test="${zanrEnum == viewAlbum.zanr.toString().replace('_','/')}">selected</c:if>>${zanrEnum}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3"></div>
            </div>

            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <form:label path="staraCena">Stara cena*:</form:label>
                    <form:input type="text" path="staraCena" class="form-control" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <form:label path="cena">Cena*:</form:label>
                    <form:input type="text" path="cena" class="form-control" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <label for="iznosPS">Poreska stopa*:</label>
                    <input type="text" name="iznosPS" class="form-control" value="${viewAlbum.poreskaStopa.iznos}"/>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <form:label path="dostupnaKolicina">Dostupna kolicina*:</form:label>
                    <form:input type="text" class="form-control" path="dostupnaKolicina" />
                </div>
                <div class="col-md-3"></div>
            </div>

            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <form:label path="izvodjac">Izvodjac*:</form:label>
                    <form:input type="text" path="izvodjac" class="form-control" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <form:label path="datumIzdavanja">Datum izdavanja*:</form:label>
                    <form:input type="text" path="datumIzdavanja" class="form-control" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <form:label path="slika">Slika*:</form:label>
                    <img class="cursor" alt="100%x200" style="height: 400px; width: 100%; display: block;" src="<c:url value="${viewAlbum.slika}" />" data-holder-rendered="true" onclick="openImgModal(${viewAlbum.id})" >
                    <form:input path="slika" readonly="true" hidden="true"/><form:errors path="slika" />

                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <a href="/albumshop/album/all/1" class="btn btn-danger btnCancelUpdate"><span class="glyphicon glyphicon-remove" style="color: white;"></span> Cancel</a>
                    <button id="update" class="btn btn-success" style="margin-left: 0px;">
                        <span class="glyphicon glyphicon-floppy-disk" style="color: white;"></span> Update
                    </button>
                </div>
                <div class="col-md-3"></div>
            </div>

        </form:form>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
    <script type="text/javascript">
                        function openImgModal(id) {
                            var data = {"id": id};
                            jQuery.ajax({
                                url: '/albumshop/album/imgDetails/' + id,
                                method: "get",
                                data: data,
                                success: function (data) {
                                    jQuery('body').append(data);
                                    jQuery('#imgModal').modal('toggle');
                                },
                                error: function () {
                                    alert("Greska!");
                                }
                            });
                        }
                        function closeModal() {
                            jQuery("#imgModal").modal("hide");
                            setTimeout(function () {
                                jQuery("#imgModal").remove();
                                jQuery(".modal-backdrop").remove();
                            }, 500);
                        }

    </script>
</html>