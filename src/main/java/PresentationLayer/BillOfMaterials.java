package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class BillOfMaterials extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {

        HttpSession session = request.getSession();

        int reqID;

        try {
            reqID = Integer.parseInt(request.getParameter("reqID"));
        } catch (NumberFormatException e) {
            request.setAttribute( "error", "Fejl: Du har ikke indtastet et heltal, ret din indtastning." );
            return "showRequests";
        }

        CustomerRequest custreq = LogicFacade.showRequest(reqID);

        Calculator cal = new Calculator();

        Calculator.bom.clear();

        ArrayList<Material> bom;
        try {
            bom = cal.bomCalculator(custreq.getWidth(), custreq.getLength(), custreq.getHeight(),
                    custreq.isFlatRoof(), custreq.getRoofMat(), custreq.getShedw(), custreq.getShedl());
        } catch (NullPointerException e) {
            request.setAttribute( "error", "FEJL: Der findes ikke en kundeforespørgsel med det indtastede nr." );
            return "adminpage";
        }

        Double total = 0.0;

        for (Material mat : bom) {
            total += mat.getPrice();
        }

        request.getServletContext().setAttribute("width", custreq.getWidth());
        request.getServletContext().setAttribute("height", custreq.getHeight());
        request.getServletContext().setAttribute("length", custreq.getLength());
        request.getServletContext().setAttribute("reqID", reqID);
        request.getServletContext().setAttribute("total", total);
        request.getServletContext().setAttribute("shedLength", custreq.getShedl());
        request.getServletContext().setAttribute("shedWidth", custreq.getShedw());
        request.getServletContext().setAttribute("roofMaterial", custreq.getRoofMat());

        session.setAttribute("materialList", bom);

        return "billofmaterials";
    }
}