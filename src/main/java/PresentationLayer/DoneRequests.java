package PresentationLayer;

import FunctionLayer.CustomerRequest;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Denne klasse bruges til at vise en liste med kundeforespørgsler der er behandlede på en webside.
 * Attributten "reqlist" får listen som værdi. Listens indhold hentes i db via LogicFacade/RequestMapper.
 *
 */

public class DoneRequests extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {

        ArrayList<CustomerRequest> reqList = null; //Initialiserer arrayliste hvis indhold skal vises på websiden
        try {
            reqList = LogicFacade.showRequests(true); //Henter indhold fra db via logicFacade og Requestmapper samt indsætter det i liste
        } catch (LoginSampleException e) {
            e.printStackTrace();
        }

        request.setAttribute("reqlist", reqList); //Sætter jsp attributten "reqlist" = den nye liste

        //Sender brugeren videre til websiden der viser nye kundeforespørgsler
        return "doneRequests";

    }

}
