package PresentationLayer;

import FunctionLayer.CustomerRequest;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Formålet med denne klasse er at hente de værdier kunden har valgt samt indtastet på websiden, skabe et CustomerRequest
 * objekt af disse og gemme CustomerRequest data i db tabellen cust_request.
 */

public class Request extends Command {


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        //Brugerens valgte/indtastede data hentes fra websiden og variable med disse værdier initialiseres
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        int length = Integer.parseInt(request.getParameter("length"));
        boolean flatRoof = Boolean.parseBoolean(request.getParameter("flatRoof"));
        String roofMaterial = request.getParameter("roofMaterial");
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        String name = request.getParameter("name");
        int telno = Integer.parseInt(request.getParameter("telno"));
        String mail = request.getParameter("mail");
        String comments = request.getParameter("comments");

        //Skaber en instans af CustomerRequest objekt ud fra de data brugeren har valgt/skrevet på websiden
        CustomerRequest custreq = new CustomerRequest(name,telno,mail,comments,width,length,height,flatRoof,roofMaterial,shedLength,shedWidth);
        //Sender objektets attribtter til fb tabellen cust_request via LogicFacade/RequestMappper
        LogicFacade.createRequest(custreq);
        //Kundens valgte værdier sættes som attributter til brug for visning af bekræftelse
        request.getServletContext().setAttribute("width", width);
        request.getServletContext().setAttribute("height", height);
        request.getServletContext().setAttribute("length", length);
        request.getServletContext().setAttribute("flatRoof", flatRoof);
        request.getServletContext().setAttribute("roofMaterial", roofMaterial);
        request.getServletContext().setAttribute("shedWidth", shedWidth);
        request.getServletContext().setAttribute("shedLength", shedLength);
        request.getServletContext().setAttribute("comments", comments);
        //Sender brugeren videre til webside hvor dennes valg vises som bekræftelse
        return "confirmation";
    }
}
