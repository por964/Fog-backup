package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Material;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class MaterialMapperTest {
    /**
     * Tester klassen MaterialMapper, som lagrer og henter materialer i databasen
     */

    public static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "root";
    private static String DBNAME = "fogtest?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    /**
     * Skaber forbindelse til testdatabasen
     */
    @BeforeClass
    public static void setUp() {
        try {
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                Connector.setConnection(testConnection);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    /**
     * Opretter testtabeller med materialer
     */
    @Before
    public void beforeEachTest() {
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("drop table if exists materials");
            stmt.execute("CREATE TABLE `materials` LIKE fog.materials;");
            stmt.execute("insert into materials VALUES" +
                    "(1, 'Træ', '25x200 mm. trykimp.bræt', 'Stern vandbræt sider', 50)," +
                    "(2, 'Træ', '25x200 mm. trykimp.bræt', 'Stern vandbræt forende', 50)," +
                    "(3, 'Træ','19x100 mm. trykimp. bræt','Beklædningsplader skur', 15)," +
                    "(4, 'Træ','38x73 mm. lægte ubeh.','Z til bagside af dør i skur', 20)," +
                    "(5, 'Træ','45x195 mm. spærtræ ubeh.','Remme i sider+spær', 250)," +
                    "(6, 'Træ','45x95 mm reglar ubh.','Løsholter til skur sider+gavle', 80)");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("drop table if exists roofmaterial");
            stmt.execute("CREATE TABLE `roofmaterial` LIKE fog.roofmaterial;");
            stmt.execute("insert into roofmaterial VALUES" +
                    "(1, 'Plastmo sort', 1)," +
                    "(2, 'Plastmo gennemsigtig', 1)," +
                    "(3, 'Plastmo hvid', 1)," +
                    "(4, 'Tagsten sort', 0)," +
                    "(5, 'Tagpap sort', 0)," +
                    "(6, 'Trapez plast sort', 0)");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }

    }

    /**
     * Tester, at der er forbindelse til testdatabasen
     */
    @Test
    public void testSetUpOK() {
        assertNotNull(testConnection);
    }

    /**
     * Tester, at der hentes et materiale med den rigtige beskrivelse
     * @throws LoginSampleException
     * @throws ClassNotFoundException
     */
    @Test
    public void getMaterial1() throws LoginSampleException, ClassNotFoundException {
        Material mat = MaterialMapper.getMaterial(1);
        assertEquals("Stern vandbræt sider", mat.getDescription());
    }

    /**
     * Tester, at navnet på materiale nr. 6 er korrekt
     * @throws LoginSampleException
     * @throws ClassNotFoundException
     */
    @Test
    public void getMaterial6() throws LoginSampleException, ClassNotFoundException {
        Material mat = MaterialMapper.getMaterial(6);
        assertEquals("Træ", mat.getCategory());
        assertEquals("45x195 mm. spærtræ ubeh.", mat.getName());
    }


    /**
     * Tester antal og indhold til listen med tagmaterialer
     * @throws LoginSampleException
     * @throws ClassNotFoundException
     */
    @Test
    public void roofMaterialTest() throws LoginSampleException, ClassNotFoundException {
        ArrayList<String> roofMaterialList = MaterialMapper.getRoofMaterials(true);
        assertThat(roofMaterialList, hasSize(3));
        assertThat(roofMaterialList, contains("Plastmo sort", "Plastmo gennemsigtig", "Plastmo hvid"));
    }

}