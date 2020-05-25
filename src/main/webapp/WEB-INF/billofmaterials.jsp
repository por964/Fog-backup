<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.inc"%>
<%@page import="java.text.DecimalFormat" %>
<%@ page import="FunctionLayer.Material" %>
<%@ page import="java.util.ArrayList" %>
<head>
    <title>Materialeliste</title>
</head>
<br>
<br>
<h3>Carport nr: ${applicationScope.reqID} har følgende mål:</h3>
<br>
<h5>Højde: ${applicationScope.height} cm, bredde: ${applicationScope.width} cm, længde: ${applicationScope.length} cm</h5>
<h5>Skur længde: ${applicationScope.shedLength} cm, bredde: ${applicationScope.shedWidth}</h5>
<h5>Tagmateriale: ${applicationScope.roofMaterial}</h5>
<br>
<h4>Materialeliste</h4>
<div class="row">
    <div class="col-sm">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Kategori</th>
                <th scope="col">Navn</th>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Antal</th>
                <th scope="col">Længde</th>
                <th scope="col">Pris</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <c:forEach var="material" items="${sessionScope.materialList}">
                <c:if test="${material.category=='Træ'}">
                    <td>${material.category}</td>
                    <td>${material.name}</td>
                    <td>${material.description}</td>
                    <td>${material.quantity}</td>
                    <td>${material.length}</td>
                    <td>${material.price}</td>
                </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-sm">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Kategori</th>
                <th scope="col">Navn</th>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Antal</th>
                <th scope="col">Pris</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <c:forEach var="material" items="${sessionScope.materialList}">
                <c:if test="${material.category=='Beslag & skruer'}">
                    <td>${material.category}</td>
                    <td>${material.name}</td>
                    <td>${material.description}</td>
                    <td>${material.quantity}</td>
                    <td>${material.price}</td>
                </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="row">
    <div class="col-sm">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Kategori</th>
                <th scope="col">Navn</th>
                <th scope="col">Beskrivelse</th>
                <th scope="col">Antal</th>
                <th scope="col">Pris</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <c:forEach var="material" items="${sessionScope.materialList}">
                <c:if test="${material.category=='Tagpakken'}">
                    <td>${material.category}</td>
                    <td>${material.name}</td>
                    <td>${material.description}</td>
                    <td>${material.quantity}</td>
                    <td>${material.price}</td>
                </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<h5 class="text-right">Samlet pris: ${applicationScope.total} kr inkl. moms
    <br>
    <br>
    <form action="FrontController" method="POST">
        <input type="hidden" name="target" value="processrequest">
        <div class="text-left">
            <label for="newprice">Ny pris:</label>
            <input id="newprice" type="text" name="newprice" value="">
            <button type="submit" class="btn btn-primary">Opdater pris</button>
            <h3>   ${requestScope.error} </h3>
        </div>
    </form>
    <br>
    <br>

    <form action="FrontController" method="POST">
        <input type="hidden" name="target" value="markasdone">
        <div class="text-left">
            <button type="submit" class="btn btn-primary">Marker ordre som færdigbehandlet</button>
        </div>
    </form>
    <br>
    <br>


    <form action="FrontController" method="POST">
    <input type="hidden" name="target" value="drawing">
    <div class="text-center">
        <button type="submit" class="btn btn-primary">Vis tegning</button>
    </div>

</form>
<%@include file="../includes/footer.inc"%>
