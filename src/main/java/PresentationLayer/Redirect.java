package PresentationLayer;

import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {

        String destination = request.getParameter("destination");

        switch (destination) {
            case "slantedRoof":
                request.setAttribute("message", "Du er nu på side for carport med rejsning");
                break;
            case "index":
                request.setAttribute("message", "Du er nu på side for carport med fladt tag");
                break;
            default:
                request.setAttribute("message", "Denne side findes ikke!");
        }

        return destination;
    }
}
