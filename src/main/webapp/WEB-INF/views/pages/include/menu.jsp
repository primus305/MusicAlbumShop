<%-- 
    Document   : menu
    Created on : Sep 17, 2019, 2:14:17 PM
    Author     : rancha
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<security:authorize access="hasAuthority('ADMINISTRATOR')">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand em-text" href="#"><span class="glyphicon glyphicon-cd"></span></a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav paginate">
                    <li ><a href="/albumshop/index" >Pocetna</a></li>
                    <li> <a href="/albumshop/album/all/1">Albumi</a></li>
                    <li> <a href="/albumshop/admin/allUsers">Korisnici</a></li>
                </ul>
                <ul class="nav navbar-nav paginate pull-right">
                    <li style="margin-top: 15px; margin-left: 30px; margin-right: 30px;" ><span class="glyphicon glyphicon-user"></span> <security:authentication property="principal.firstname" /> <security:authentication property="principal.lastname" /></li>
                    <li><a href="<c:url value="/logout" />">Logout <span class="glyphicon glyphicon-log-out"></span></a></li>
                </ul>

            </div><!--/.nav-collapse -->
        </div>
    </nav>
</security:authorize>
<security:authorize access="hasAuthority('OBICAN_KORISNIK')">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand em-text" href="#"><span class="glyphicon glyphicon-cd"></span></a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav paginate">
                    <li ><a href="/albumshop/index" >Pocetna</a></li>
                    <li> <a href="/albumshop/album/all/1">Ponuda</a></li>

                    <li><a href="/albumshop/cart/myCart"><span class="glyphicon glyphicon-shopping-cart"></span> My Cart</a> </li>
                    <li> <a href="/albumshop/porudzbina/all/1">My orders</a></li>

                </ul>
                <ul class="nav navbar-nav paginate pull-right">
                    <li>
                        <div id="cart-info" class="nav-info  cart-info " style="width:65px; height: 40px; padding-left: 0px;">
                            <!--<span class="cart-info__icon" ><i class="fa fa-shopping-cart" ></i></span>-->
                            <span class="p1 fa-stack fa-2x has-badge " data-count="${(sessionScope.shoppingCart == null ? 0 : sessionScope.shoppingCart.getStavkeDTOs().size())}" >
                                <!--<i class="p2 fa fa-circle fa-stack-2x"></i>-->
                                <i class="p3 fa fa-shopping-cart fa-stack-1x xfa-inverse" data-count="4b"></i>
                            </span>
                        </div>
                    </li>
                    <li style="margin-top: 15px; margin-left: 30px; margin-right: 30px;" ><span class="glyphicon glyphicon-user"></span> <security:authentication property="principal.firstname" /> <security:authentication property="principal.lastname" /></li>
                    <li><a href="<c:url value="/logout" />">Logout <span class="glyphicon glyphicon-log-out"></span></a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
</security:authorize>
