package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Formålet med denne klasse er at validere et login i databasen og sætte bruger objektets værdier som session attributter
 * ved log in. Ved succesfuldt login føres brugeren til admin web siden.
 */
public class Login extends Command {

    /**
     *
     * @param request henter værdier fra websiden
     * @param response sender værdier samt ny webside
     * @return admin websiden
     * @throws LoginSampleException ved SQL fejl
     */
    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        //Henter værdier fra webside parametre
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        User user = LogicFacade.login( email, password );

        HttpSession session = request.getSession(); //Checker om session allerede eksisterer, hvis nej oprettes ny
        //Sætter attributter på session
        session.setAttribute( "user", user );
        session.setAttribute("email", email);
        session.setAttribute("userID", user.getId());

        //Sender den validerede bruger til side i WEB-INF
        return "adminpage";
    }

}
