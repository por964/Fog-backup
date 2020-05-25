package DBAccess;
import FunctionLayer.LoginSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 Formålet med denne klasse er at hente værdier fra dimensionstabeller
 i databasen og returnere dem som arraylister. Listerne bruges til at befolke
 dropdownmenuer på JSP siderne.
 @author Claes
 */
public class DimensionMapper {

    /**Henter og returnerer en arrayliste med de mulige bredder fra tabellen "width" i db
     *
     * @return Liste med værdier for carport bredde
     * @throws ClassNotFoundException
     * @throws LoginSampleException ved sql fejl
     */

    public static ArrayList<Integer> getWidthList() throws ClassNotFoundException, LoginSampleException {
        String sql = "SELECT width FROM fog.width;";
        ArrayList<Integer> widthList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            if (res == null) {
                return null;
            } else {
                while (res.next()) {
                    widthList.add(res.getInt("width"));
                }
            }
        } catch (SQLException ex) {
            throw new LoginSampleException( ex.getMessage() );
        }
        System.out.println(widthList);
        return widthList;
    }

    /**Henter og returnerer en arrayliste med de mulige carport bredder fra tabellen "length" i db
     *
     * @return Liste med værdier for carport bredde
     * @throws ClassNotFoundException
     * @throws LoginSampleException ved sql fejl
     */
    public static ArrayList<Integer> getLengthList() throws ClassNotFoundException, LoginSampleException {
        String sql = "SELECT * FROM fog.length;";
        ArrayList<Integer> lengthList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            if (res == null) {
                return null;
            } else {
                while (res.next()) {
                    lengthList.add(res.getInt("length"));
                }
            }
        } catch (SQLException ex) {
            throw new LoginSampleException( ex.getMessage() );
        }
        return lengthList;
    }

    /**Henter og returnerer en arrayliste med de mulige carport højder fra tabellen "height" i db
     *
     * @return Liste med værdier for højde af carport
     * @throws ClassNotFoundException
     * @throws LoginSampleException ved sql fejl
     */
    public static ArrayList<Integer> getHeightList() throws ClassNotFoundException, LoginSampleException {
        String sql = "SELECT * FROM fog.height;";
        ArrayList<Integer> heightList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            if (res == null) {
                return null;
            } else {
                while (res.next()) {
                    heightList.add(res.getInt("height"));
                }
            }
        } catch (SQLException ex) {
            throw new LoginSampleException( ex.getMessage() );
        }
        return heightList;
    }

    /**Henter og returnerer en arrayliste med de mulige skur længder fra tabellen "shedlength" i db
     *
     * @return ArralyListe med skur længde værdier
     * @throws ClassNotFoundException
     * @throws LoginSampleException ved sql fejl
     */
    public static ArrayList<Integer> getShedLengthList() throws ClassNotFoundException, LoginSampleException {
        String sql = "SELECT * FROM fog.shedlength;";
        ArrayList<Integer> shedLengthList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            if (res == null) {
                return null;
            } else {
                while (res.next()) {
                    shedLengthList.add(res.getInt("shedLength"));
                }
            }
        } catch (SQLException ex) {
            throw new LoginSampleException( ex.getMessage() );
        }
        return shedLengthList;
    }

    /**Henter og returnerer en arrayliste med de mulige skur bredder fra tabellen "shedwidth" i db
     *
     * @return ArrayListe med værdier for skur bredde
     * @throws ClassNotFoundException
     * @throws LoginSampleException ved sql fejl
     */
    public static ArrayList<Integer> getShedWidthList() throws ClassNotFoundException, LoginSampleException {
        String sql = "SELECT * FROM fog.shedwidth;";
        ArrayList<Integer> shedWidthList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            if (res == null) {
                return null;
            } else {
                while (res.next()) {
                    shedWidthList.add(res.getInt("shedWidth"));
                }
            }
        } catch (SQLException ex) {
            throw new LoginSampleException( ex.getMessage() );
        }
        return shedWidthList;
    }

}
