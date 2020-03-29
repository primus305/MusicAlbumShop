<%-- 
    Document   : add
    Created on : Aug 14, 2019, 4:58:41 PM
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
        <form:form method="POST" action="/albumshop/album/save" modelAttribute="album" enctype="multipart/form-data">
            <c:if test="${albumUnosErr != ''}" >
                <div class="bg-danger">
                    <p class="text-center text-danger">
                        ${albumUnosErr}
                    </p>
                </div>
            </c:if>
            <h2 class="text-center">Unos novog albuma</h2><hr>
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
                            <option value="${zanrEnum}">${zanrEnum}</option>
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
                    <input type="text" name="iznosPS" class="form-control" />
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
                    <label for="file">Slika*:</label>
                    <input type="file" class="form-control" name="file" />
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="form-group col-md-6">
                    <a href="/albumshop/album/all/1" class="btn btn-danger btnCancel"><span class="glyphicon glyphicon-remove" style="color: white;"></span> Cancel</a>
                    <button id="save" class="btn btn-success" style="margin-left: 0px;">
                        <span class="glyphicon glyphicon-floppy-disk" style="color: white;"></span> Save
                    </button>

                </div>
                <div class="col-md-3"></div>
            </div>

        </form:form>

    </body>
    <jsp:include page="../include/footer.jsp"/>
</html>