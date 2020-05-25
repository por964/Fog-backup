<%@ page import="FunctionLayer.LogicFacade" %>
<%@ page import="FogUtil.Initializer" %>
<%@ page import="FunctionLayer.LoginSampleException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/header.inc"%>
<%
    boolean roof = false;

    if (request.getServletContext().getAttribute("width") == null) {
        request.getServletContext().setAttribute("width", Initializer.getWidthsList());
    }
    if (request.getServletContext().getAttribute("length") == null) {
        request.getServletContext().setAttribute("length", Initializer.getLengthsList());
    }
    if (request.getServletContext().getAttribute("height") == null) {
        request.getServletContext().setAttribute("height", Initializer.getHeightsList());
    }
    if (request.getServletContext().getAttribute("shedLength") == null) {
        request.getServletContext().setAttribute("shedLength", Initializer.getShedLengthsList());
    }
    if (request.getServletContext().getAttribute("shedWidth") == null) {
        request.getServletContext().setAttribute("shedWidth", Initializer.getShedWidthsList());
    }
    request.getServletContext().setAttribute("flatRoof", false);
    try {
        request.getServletContext().setAttribute("roofMaterial", LogicFacade.showRoofMaterialList(roof));
    } catch (LoginSampleException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>
<body>
<br>
<br>
<h3>Design carport med tag med rejsning</h3>
<a href="FrontController?target=redirect&destination=index">Eller gå til carport med fladt tag</a>
<br>
<br>
<div class="container-fluid">
    <div id="navbar-two">
        <div class="row">

            <div class="form-group">
                <form action="FrontController" method="POST">
                    <input type="hidden" name="target" value="request">
                    <label for="width">Vælg bredde:</label>
                    <select class="form-control" name="width" id="width" style="width: 350px">
                        <c:forEach var="width" items="${applicationScope.width}">
                            <option value="${width}">${width} mm.</option>
                        </c:forEach>
                    </select>

                    <div class="form-group">
                        <label for="length">Vælg længde:</label>
                        <select class="form-control" name="length" id="length" style="width: 350px">
                            <c:forEach var="length" items="${applicationScope.length}">
                                <option value="${length}">${length} mm.</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="height">Vælg højde:</label>
                        <select class="form-control" name="height" id="height" style="width: 350px">
                            <c:forEach var="height" items="${applicationScope.height}">
                                <option value="${height}">${height} mm.</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="roofMaterial">Vælg materiale til tag:</label>
                        <select class="form-control" name="roofMaterial" id="roofMaterial" style="width: 350px">
                            <c:forEach var="roofMaterial" items="${applicationScope.roofMaterial}">
                                <option value="${roofMaterial}">${roofMaterial}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="shedWidth">Vælg bredde til redskabsrum:</label>
                        <select class="form-control" name="shedWidth" id="shedWidth" style="width: 350px">
                            <option value="0" selected>Ønsker ikke redskabsrum </option>
                            <c:forEach var="shedWidth" items="${applicationScope.shedWidth}">
                                <option value="${shedWidth}">${shedWidth} mm.</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="shedLength">Vælg længde til redskabsrum:</label>
                        <select class="form-control" name="shedLength" id="shedLength" style="width: 350px">
                            <option value="0" selected>Ønsker ikke redskabsrum </option>
                            <c:forEach var="shedLength" items="${applicationScope.shedLength}">
                                <option value="${shedLength}">${shedLength} mm.</option>
                            </c:forEach>
                        </select>
                    </div>
                    <br>
                    <h6>NB: Længde og bredde til redskabsrum skal være mindst 30 cm kortere end længde og bredde af carporten</h6>
                    <br>
                    <br>
                    <h3>Indtast dine kontakt detaljer:</h3>
                    <div class="form-group">
                        <label for="name">Navn</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Dit navn">
                    </div>
                    <div class="form-group">
                        <label for="mail">Email addresse</label>
                        <input type="email" class="form-control" name="mail" id="mail" placeholder="Din E-mail">
                    </div>
                    <div class="form-group">
                        <label for="telno">Telefonnummer</label>
                        <input type="text" class="form-control" name="telno" id="telno" placeholder="Dit telefonnr">
                    </div>
                    <div class="form-group">
                        <label for="comments">Kommentarer</label>
                        <input type="text" class="form-control" name="comments" id="comments" style="height:100px">
                    </div>
                    <br>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Send forespørgsel</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<br>
<a href="FrontController?target=redirect&destination=login">Til medarbejderlogin</a>
</body>
<%@include file="includes/footer.inc"%>
