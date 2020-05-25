package FogUtil;

import FunctionLayer.LogicFacade;

import java.util.List;
/**
 * Denne klasse indeholder metoder der befolker drop-down menuer på .jsp sider ved system start.
 * Metoderne kalder metoder i FacadeLogic klassen som en del af facade mønstret.
 * @author claes
 */

public class Initializer {

    private static List<Integer> widthsList = null;
    private static List<Integer> lengthsList = null;
    private static List<Integer> heightsList = null;
    private static List <Integer> shedWidthsList = null;
    private static List <Integer> shedLengthsList = null;

    /**Returnerer liste med værdier for carport bredde fra db via LogicFacade og DimensionMapper
     *
     * @return værdier for bredde
     */
    public static List<Integer> getWidthsList() {
        if (widthsList == null) {
            try {
                widthsList = LogicFacade.showWidths();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return widthsList;
    }

    /**Returnerer liste med værdier for carport længde fra db via LogicFacade og DimensionMapper
     *
     * @return værdier for længde
     */
    public static List<Integer> getLengthsList() {
        if (lengthsList == null) {
            try {
                lengthsList = LogicFacade.showLengths();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lengthsList;
    }

    /**Returnerer liste med værdier for carport højde fra db via LogicFacade og DimensionMapper
     *
     * @return liste med værdier for højde
     */
    public static List<Integer> getHeightsList() {
        if (heightsList == null) {
            try {
                heightsList = LogicFacade.showHeights();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return heightsList;
    }

    /**Returnerer liste med værdier for skur bredde fra db via LogicFacade og DimensionMapper
     *
     * @return liste med værdier for skur bredde
     */
    public static List<Integer> getShedWidthsList() {
        if (shedWidthsList == null) {
            try {
                shedWidthsList = LogicFacade.showShedWidths();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return shedWidthsList;
    }

    /**Returnerer liste med værdier for skur længde fra db via LogicFacade og DimensionMapper
     *
     * @return liste med værdier for skur længde
     */
    public static List<Integer> getShedLengthsList() {
        if (shedLengthsList == null) {
            try {
                shedLengthsList = LogicFacade.showShedLengths();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return shedLengthsList;
    }
}
