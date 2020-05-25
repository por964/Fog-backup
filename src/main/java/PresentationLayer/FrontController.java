package PresentationLayer;

import FunctionLayer.LoginSampleException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Denne servlet tager navnet på en klasse i Command mønstret fra et request objekt, skaber en instans af klassen og
 * eksekverer denne. Såfremt navnet på klassen er identisk med en jsp side, sendes brugeren videre til denne.
 *
 */

@WebServlet( name = "FrontController", urlPatterns = { "/FrontController" } )
public class FrontController extends HttpServlet {

    /**
     Behandler requests for både HTTP <code>GET</code> and <code>POST</code>
     metoder.

     @param request servlet request
     @param response servlet response
     @throws ServletException hvis der opstår en servlet fejl
     @throws IOException hvis der opstår en I/O fejl
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            Command action = Command.from( request );
            String view = action.execute( request, response );
            if (view.equals("index") || view.equals("confirmation") || view.equals("slantedRoof")|| view.equals("login")) {
                request.getRequestDispatcher(view + ".jsp").forward(request,response); //Sider der IKKE kræver login
            } else {
                request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response); //Sider der kræver login

            }
        } catch (LoginSampleException | ClassNotFoundException ex ) {
            request.setAttribute( "error", ex.getMessage() ); //Sætter fejlmeddelelse på servlet attributten "error"
            request.getRequestDispatcher( "login.jsp" ).forward( request, response );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     Handles the HTTP <code>GET</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     Handles the HTTP <code>POST</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     Returns a short description of the servlet.

     @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}