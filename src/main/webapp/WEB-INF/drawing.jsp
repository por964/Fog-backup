<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../includes/header.inc"%>
<head>
    <title>Tegning</title>
</head>
<body>
<h1>Tegning af carport</h1>
<br>
<h5>Højde: ${applicationScope.height} cm, bredde: ${applicationScope.width} cm, længde: ${applicationScope.length} cm</h5>
<h5>Skur længde: ${applicationScope.shedLength} cm, bredde: ${applicationScope.shedWidth}</h5>
<br>
${requestScope.svgdrawing}
<br>
<br>
<a href="FrontController?target=redirect&destination=adminpage">Til admin.siden</a>


</body>
<%@include file="../includes/footer.inc"%>

