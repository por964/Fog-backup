<%--
  Created by IntelliJ IDEA.
  User: claes
  Date: 02-05-2020
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="FogUtil.Initializer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.inc"%>
<head>
    <title>Bekræftelse</title>
</head>
<body>
<h1>Bekræftelse</h1>
<br>
<h2 style="text-align:center">Tak for din bestilling</h2>
<br>
<h4>Du har valgt en carport med følgende mål:</h4>
<br>
<br>
<h5>Højde: ${applicationScope.height} cm</h5>
<br>
<h5>Bredde: ${applicationScope.width} cm</h5>
<br>
<h5>Længde: ${applicationScope.length} cm</h5>
<br>
<h5>Tagmateriale: ${applicationScope.roofMaterial}</h5>
<br>
<h5>Skurlængde: ${applicationScope.shedLength}</h5>
<br>
<h5>Skur bredde: ${applicationScope.shedWidth}</h5>
<br>
<h5>Kommentarer: ${applicationScope.comments}</h5>
<br>
<br>
<h4 style="text-align:center">Tak for din forespørgsel, vi vender tilbage til dig hurtigst muligt.</h4>
<br>

<%
    request.getServletContext().setAttribute("width", null);
    request.getServletContext().setAttribute("length", null);
    request.getServletContext().setAttribute("height", null);
    request.getServletContext().setAttribute("roofMaterial", null);
    request.getServletContext().setAttribute("shedWidth", null);
    request.getServletContext().setAttribute("shedLength", null);
%>

<div class="text-center">
    <a href="FrontController?target=redirect&destination=index">Til forsiden</a>
</div>


</body>

<%@include file="includes/footer.inc"%>
