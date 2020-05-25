package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Denne klasse henter værdierne for den valgte carport og opretter variable med disse værdier.
 * Disse variable bruges som parametre for en SVG tegning. SVG tegningen sendes som en String til websiden
 * som brugeren derefter sendes til.
 */

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        //Højde og bredde for den valgte carport hentes
        int width = ((int) request.getServletContext().getAttribute("width") / 10);
        int length = ((int) request.getServletContext().getAttribute("length") / 10);

        //Højde og bredde for skur hentes
        int shedWidth = ((int) request.getServletContext().getAttribute("shedWidth") / 10);
        int shedLength = ((int) request.getServletContext().getAttribute("shedLength") / 10);

        //Viewbox størrelse bliver afhængig af carport størrelse, så tegningen får en passende størrelse
        String viewbox = "0,0," + width + "," + length;

        //SVG klassen kaldes med carport dimensionerne
        Svg svg = new Svg(width, length, viewbox, 0, 0);

        //Rammen på tegningen tilføjes
        svg.addRect(0, 0, length, width);

        //Diverse variable instantieres
        int x = 0;
        int y;
        int thickness;
        int poleThickness = 8;
        int distanceBetweenEach = 55;
        int xStartingPoint;
        int xEndingPoint;
        int yStartingPoint;
        int yEndingPoint;

        //Tilføjer remme:
        thickness = 8;
        svg.addStrap(30, 0, length, thickness);
        int xStrap = width - 30;
        svg.addStrap(xStrap, 0, length, thickness);

        //Disse stolper skal kun tilføjes, hvis der ikke er skur:
        if (shedLength == 0 && shedWidth == 0) {
            //Tilføjer stolper (4 stk. hvis carport længde < 480 cm. Ellers 6 stk):
            if (length < 481) {

                int yPole1 = (length / 10) * 2;
                int xPole1 = 30;
                svg.addRect(xPole1, yPole1, poleThickness, poleThickness);

                int yPole2 = (length / 10) * 2;
                int xPole2 = width - 30;
                svg.addRect(xPole2, yPole2, poleThickness, poleThickness);

                int yPole3 = (length / 10) * 8;
                int xPole3 = 30;
                svg.addRect(xPole3, yPole3, poleThickness, poleThickness);

                int yPole4 = (length / 10) * 8;
                int xPole4 = width - 30;
                svg.addRect(xPole4, yPole4, poleThickness, poleThickness);

            } else {

                int yPole1 = (length / 10) * 2;
                int xPole1 = 30;
                svg.addRect(xPole1, yPole1, poleThickness, poleThickness);

                int yPole2 = (length / 10) * 2;
                int xPole2 = width - 30;
                svg.addRect(xPole2, yPole2, poleThickness, poleThickness);

                int yPole3 = (length / 10) * 8;
                int xPole3 = 30;
                svg.addRect(xPole3, yPole3, poleThickness, poleThickness);

                int yPole4 = (length / 10) * 8;
                int xPole4 = width - 30;
                svg.addRect(xPole4, yPole4, poleThickness, poleThickness);

                int yPole5 = (length / 10) * 5;
                int xPole5 = 30;
                svg.addRect(xPole5, yPole5, poleThickness, poleThickness);

                int yPole6 = (length / 10) * 5;
                int xPole6 = width - 30;
                svg.addRect(xPole6, yPole6, poleThickness, poleThickness);
            }
        }

        //Tilføjer ramme om skur
        if (shedLength != 0 && shedWidth != 0) {
            //Vandret streg tegnes:
            xStartingPoint = 4;
            xEndingPoint = shedWidth;
            yStartingPoint = length - shedLength;
            yEndingPoint = length - shedLength;
            svg.addCross(xStartingPoint, yStartingPoint, xEndingPoint, yEndingPoint);

            //Lodret streg tegnes:
            xStartingPoint = shedWidth + poleThickness;
            xEndingPoint = shedWidth + poleThickness;
            yStartingPoint = length;
            yEndingPoint = length - shedLength;
            svg.addCross(xStartingPoint, yEndingPoint, xEndingPoint, yStartingPoint);
        }

        //Disse stolper skal kun tilføjes, hvis der er skur:
        if (shedLength != 0 && shedWidth != 0) {
            //Tilføjer de stolper der skal bære remmen i modsatte ende af skuret
            int yPole1 = (length / 10) * 2;
            int xPole1 = 30;
            svg.addRect(xPole1, yPole1, poleThickness, poleThickness);

            int yPole2 = (length / 10) * 2;
            int xPole2 = width - 30;
            svg.addRect(xPole2, yPole2, poleThickness, poleThickness);

            //Tilføjer stolper der skal bære remmen i enden hvor skuret er:
            int yPole3 = length - shedLength;
            int xPole3 = 30;
            svg.addRect(xPole3, yPole3, poleThickness, poleThickness);

            int yPole4 = length - shedLength;
            int xPole4 = width - 30;
            svg.addRect(xPole4, yPole4, poleThickness, poleThickness);

            //Tilføjer stolper til skuret:
            int yPole5 = length - 10;
            int xPole5 = 4;
            svg.addRect(xPole5, yPole5, poleThickness, poleThickness);

            int yPole6 = length - shedLength;
            int xPole6 = 4;
            svg.addRect(xPole6, yPole6, poleThickness, poleThickness);

            int yPole7 = length - shedLength;
            int xPole7 = shedWidth;
            svg.addRect(xPole7, yPole7, poleThickness, poleThickness);

            int yPole8 = length - 10;
            int xPole8 = shedWidth;
            svg.addRect(xPole8, yPole8, poleThickness, poleThickness);

        }

            //Første streg til kryds tegnes:
            xStartingPoint = 30;
            xEndingPoint = width - 24;
            yStartingPoint = length - 50;
            yEndingPoint = 50;
            svg.addCross(xStartingPoint, yStartingPoint, xEndingPoint, yEndingPoint);

            //Anden streg til kryds tegnes:
            xStartingPoint = 30;
            xEndingPoint = width - 24;
            yStartingPoint = length - 50;
            yEndingPoint = 50;
            svg.addCross(xStartingPoint, yEndingPoint, xEndingPoint, yStartingPoint);

            //For loop som tilføjer antal spær tilpasset carport dimensionerne:
            thickness = 4;
            y = 55;
            for (int i = length; i > y; y = y + distanceBetweenEach) {
                svg.addRect(x, y, thickness, width);
            }


            request.setAttribute("svgdrawing", svg.toString());

        return "drawing";
    }
}

