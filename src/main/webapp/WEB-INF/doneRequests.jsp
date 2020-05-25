<%--
  Created by IntelliJ IDEA.
  User: THOMA
  Date: 5/13/2020
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../includes/header.inc"%>


<br>

<h1>Oversigt over færdigbehandlede ordrer: </h1>
<br>

<div class="row">
    <div class="col-sm">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">KundeID</th>
                <th scope="col">Navn</th>
                <th scope="col">Telefonnr</th>
                <th scope="col">Mail</th>
                <th scope="col">Kommentarer</th>
                <th scope="col">Carport bredde</th>
                <th scope="col">Carport længde</th>
                <th scope="col">Carport højde</th>
                <th scope="col">Fladt tag</th>
                <th scope="col">Tagmateriale</th>
                <th scope="col">Skur længde</th>
                <th scope="col">Skur bredde</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="request" items="${requestScope.reqlist}">
                <tr>
                    <td>${request.reqId}</td>
                    <td>${request.name}</td>
                    <td>${request.telno}</td>
                    <td>${request.email}</td>
                    <td>${request.comments}</td>
                    <td>${request.width}</td>
                    <td>${request.length}</td>
                    <td>${request.height}</td>
                    <td>${request.flatRoof}</td>
                    <td>${request.roofMat}</td>
                    <td>${request.shedl}</td>
                    <td>${request.shedw}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>





        <div class="text-center">
            <a href="FrontController?target=redirect&destination=adminpage">Tilbage</a>
        </div>
    </div>
</div>

<%@include file="../includes/footer.inc"%>
