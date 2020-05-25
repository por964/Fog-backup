package PresentationLayer;

import FunctionLayer.LoginSampleException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Denne klasse bruges til at afslutte en brugers session. Brugerens session lukkes og attributter sættes til null.
 * Derefter føres brugeren tilbage til forsiden (index).
 */

public class Logout extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        HttpSession session = request.getSession();

        session.invalidate(); //Gør session ugyldig og fjerner objekter (bruger)
        //Sætter attributter til null
        request.getServletContext().setAttribute("width", null);
        request.getServletContext().setAttribute("length", null);
        request.getServletContext().setAttribute("height", null);
        request.getServletContext().setAttribute("roofMaterial", null);
        request.getServletContext().setAttribute("shedWidth", null);
        request.getServletContext().setAttribute("shedLength", null);

        return "index";
    }

}