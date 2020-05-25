<%@ page import="FunctionLayer.Material" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../includes/header.inc"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Customer home page</title>
</head>
<body>
<h1>Hej ${sessionScope.email} </h1>
<br>
<h2>Kundeforespørgsel nr: ${applicationScope.reqID} er opdateret med pris: ${applicationScope.total}</h2>
<br>
<br>
<a href="FrontController?target=redirect&destination=adminpage">Til adminsiden</a>


</body>
<%@include file="../includes/footer.inc"%>