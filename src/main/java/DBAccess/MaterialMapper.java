package DBAccess;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 Formålet med denne klasse er at hente materialer fra db tabellerne materials og roofmaterial.
 @author claes
 */

public class MaterialMapper {

    /**Returnerer et materiale objekt fra "materials" tabellen ud fra et givet materiale id
     *
     * @param materialId materialenummer
     * @return Material objekt
     * @throws ClassNotFoundException
     * @throws LoginSampleException ved sql fejl
     */
    public static Material getMaterial(int materialId) throws ClassNotFoundException, LoginSampleException {
        String sql = "SELECT * FROM fog.materials WHERE materialId = ?;";
        Material material = new Material();
        try {
            Connection con = Connector.connection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, materialId);
            ResultSet res = pstmt.executeQuery();
            if (res == null) {
                return null;
            } else {
                while (res.next()) {
                    material.setId(res.getInt("materialId"));
                    material.setCategory(res.getString("category"));
                    material.setName(res.getString("name"));
                    material.setDescription(res.getString("description"));
                    material.setPrice(res.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    /**Henter tagmateriale navne fra tabellen roofmaterial afhængigt af om taget er fladt eller med rejsning (true/false)
     *
     * @param roof boolean fladt tag eller ej
     * @return ArrayListe med tagmaterialer til enten fladt eller tag med rejsning
     * @throws ClassNotFoundException
     * @throws LoginSampleException ved sql fejl
     */
    public static ArrayList<String> getRoofMaterials(boolean roof) throws ClassNotFoundException, LoginSampleException {
        ArrayList<String> RoofMaterialList = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String sql = "SELECT * FROM fog.roofmaterial WHERE flatRoof = ?;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setBoolean(1,roof);
            ResultSet res = pstmt.executeQuery();
            if (res == null) {
                return null;
            } else {
                while (res.next()) {
                    RoofMaterialList.add(res.getString("roofmaterial"));
                }
            }
        } catch (SQLException ex) {
            throw new LoginSampleException( ex.getMessage() );
        }
        return RoofMaterialList;
    }
}