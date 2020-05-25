package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Formålet med denne klasse er at ændre status på et CustomerRequest i db fra ikke-behandlet
 * til færdiggjort. Efter ændring sendes brugeren tilbage til admin web siden.
 */

public class MarkAsDone extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        //Henter værdien fra webside attribut
        int id = (int) request.getServletContext().getAttribute("reqID");
        //Henter den udregnede pris fra websiden
        double price = (double) request.getServletContext().getAttribute("total");
        //Opdaterer pris for det valgte id i tabellen cust_request via LogicFacade/RequestMapper
        LogicFacade.updateRequest(price,id);
        //Sender brugeren retur til admin siden
        return "adminpage";
    }
}
