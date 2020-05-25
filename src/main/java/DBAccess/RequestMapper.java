package DBAccess;

import FunctionLayer.CustomerRequest;
import FunctionLayer.LoginSampleException;

import java.sql.*;
import java.util.ArrayList;
/**
Formålet med denne klasse er: 1.Oprette en ny kundeforespørgsel i db, 2. Hente kundeforespørgsler i db og returnere dem
 i en arrayliste 3.Opdatere/ændre data i db på en tidligere oprettet forespørgsel.
 @author claes
 */

public class RequestMapper {

    /**Indsætter attributterne fra et nyt request objekt i db tabellen cust_request
     *
     * @param request objekt med navn, tlf.nr, email,kommentarer,bredde,længde,højde,tagtype,tagmateriale, skur længde og bredde
     * @throws LoginSampleException ved sql fejl
     */
    public static void createRequest(CustomerRequest request) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO cust_request (name, tel_no, email, comments, width,length,height,flatRoof,roofMaterial,shed_length," +
                    "shed_width,processed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString( 1, request.getName() );
            ps.setInt( 2, request.getTelno() );
            ps.setString( 3, request.getEmail() );
            ps.setString( 4, request.getComments() );
            ps.setInt( 5, request.getWidth() );
            ps.setInt( 6, request.getLength() );
            ps.setInt( 7, request.getHeight());
            ps.setBoolean(8, request.isFlatRoof());
            ps.setString( 9, request.getRoofMat() );
            ps.setInt(10, request.getShedl());
            ps.setInt( 11, request.getShedw() );
            ps.setBoolean(12,false);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt( 1 );
            request.setReqId( id );
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    /**Henter attributter på requests der endnu ikke er behandlet af medarbejder og returnerer dem i en arrayliste af requestobjekter
     *
     * @param processed behandlet eller ej
     * @return ArrayListe med ubehandlede forspørgsler
     * @throws LoginSampleException ved sql fejl
     */
    public static ArrayList<CustomerRequest> showNewRequests(boolean processed) throws LoginSampleException {
        ArrayList<CustomerRequest> newRequestsList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM cust_request where processed = " + processed + "";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            CustomerRequest req = null;
            while (rs.next()) {
                int id = rs.getInt("reqId");
                String name = rs.getString("name");
                int tel = rs.getInt("tel_no");
                String email = rs.getString("email");
                String comments = rs.getString("comments");
                int width = rs.getInt("width");
                int length = rs.getInt("length");
                int height = rs.getInt("height");
                boolean rooftype = rs.getBoolean("flatRoof");
                String material = rs.getString("roofMaterial");
                int shedl = rs.getInt("shed_length");
                int shedw = rs.getInt("shed_width");
                req = new CustomerRequest(id,name,tel,email,comments,width,length,height,rooftype,material,shedl,shedw);
                newRequestsList.add(req);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return newRequestsList;
    }

    /**Henter requestattributter med et givet id i tabellen cust_request og skaber et request objekt som returneres
     *
     * @param id forespørgsel nr
     * @return Customerrequest objekt med bredde,længde,højde,tagtype,tagmateriale,skur længde og bredde
     * @throws LoginSampleException ved sql fejl
     */
    public static CustomerRequest getRequestFromID(int id) throws LoginSampleException {
        CustomerRequest req = null;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT width,length,height,flatRoof,roofMaterial,shed_length,shed_width FROM cust_request where reqId = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, "" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int width = rs.getInt("width");
                int length = rs.getInt("length");
                int height = rs.getInt("height");
                boolean flatRoof = rs.getBoolean("flatRoof");
                String material = rs.getString("roofMaterial");
                int shedlength = rs.getInt("shed_length");
                int shedwidth = rs.getInt("shed_width");
                req = new CustomerRequest(width,length,height,flatRoof,material,shedlength,shedwidth);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }

        return req;
    }

    /**Opdaterer et givet request id med en ny pris i tabellen cust_request og ændrer parametren
     * processed til true.
     *
     * @param price pris i kr
     * @param id forespørgsels nummer
     * @throws LoginSampleException ved sql fejl
     */
    public static void processRequest(double price, int id) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE cust_request SET processed = true, price = ? where reqId = ?;";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setDouble(1,price);
            ps.setInt(2,id);
            ps.executeUpdate();

        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }


    }
    /**Opdaterer et givet request id med en ny pris i tabellen cust_request.
     *
     * @param price pris i kr
     * @param id forespørgsels nummer
     * @throws LoginSampleException ved sql fejl
     */
    public static void setPrice(double price, int id) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE cust_request SET price = ? where reqId = ?;";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setDouble(1,price);
            ps.setInt(2,id);
            ps.executeUpdate();

        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new LoginSampleException( ex.getMessage() );
        }


    }


}
