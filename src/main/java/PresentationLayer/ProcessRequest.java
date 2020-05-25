package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Formålet med denne klasse er at opdatere en valgt kundeforespørgsel med en ny pris.
 * Når medarbejderen har indsat den nye pris, sendes denne videre til en webside med bekræftelse.
 * Der checkes først om der er indtastet en ny pris og derefter om denne kan parses til en double
 * dvs, om den er gyldig. Hvis ingen ny pris er indtastet, bruges den eksisterende.
 */

public class ProcessRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        //Henter nr på den kundeforespørgsel der skal ændres og parser den til int
        int id = (int) request.getServletContext().getAttribute("reqID");
        //Henter den samlede beregnede materialepris (beregnet i BillOfMaterials)
        String price = String.valueOf(request.getServletContext().getAttribute("total"));
        //Henter den nye indtastede pris
        String newPrice = request.getParameter("newprice");
        //Checker om der er indtastet en pris, hvis ja bliver den sat som pris attribut
        if (newPrice.length() > 0) {
            price = newPrice;
        }
        //Double instantieres til brug i db opdatering
        double finalPrice;
        //Checker at den indtastede pris kan parses fra String til double, kaster fejl hvis ikke og sender retur til side med materialeliste
        try {
            finalPrice = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            request.setAttribute( "error", "Fejl: Den nye pris skal være et positivt tal, prøv igen." );
            return "billofmaterials";
        }
        LogicFacade.updatePrice(finalPrice,id);
        //Sætter den nye totalpris så den kan vises på requestConfirmation.jsp
        request.getServletContext().setAttribute("total", finalPrice);
        //Sender videre til bekræftelse af opdatering
        return "requestConfirmation";
    }
}
