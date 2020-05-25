package PresentationLayer;

import FunctionLayer.CustomerRequest;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Denne klasse viser ikke-behandlede kundeforespørgsler på websiden "showRequests".
 * Data bliver hentet som liste fra tabellen cust_request via LogicFacade/RequestMapper og derefter
 * sættes attributten "reqlist" = listen, denne vises på websiden ved brug af et loop.
 */

public class ShowRequests extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        ArrayList<CustomerRequest> reqList = null; //Initialiserer arrayliste hvis indhold skal vises på websiden
        try {
            reqList = LogicFacade.showRequests(false); //Henter indhold fra db via logicFacade og Requestmapper samt indsætter det i liste
        } catch (LoginSampleException e) {
            e.printStackTrace();
        }

        request.setAttribute("reqlist", reqList);//Sætter jsp attributten "reqlist" = den nye liste

        //Sender brugeren videre til websiden der viser nye kundeforespørgsler
        return "showRequests";
    }
}
