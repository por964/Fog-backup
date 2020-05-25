package DBAccess;

import FunctionLayer.CustomerRequest;
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
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RequestMapperTest {

    /**
    Tester RequestMapper, som bruges til at hente og lagre customer requests i databasen
     */

    public static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "root";
    private static String DBNAME = "fogtest?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";


    /**
    Opretter forbindelse til testdatabasen i MySql
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
    Skaber tabeller magen til dem, der bruges i RequestMapper
     */
    @Before
    public void beforeEachTest() {
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("drop table if exists cust_request");
            stmt.execute("CREATE TABLE `cust_request` LIKE fog.cust_request;");
            stmt.execute("INSERT INTO cust_request (name, tel_no, email, comments, width, length, height, flatRoof, roofMaterial,shed_length, shed_width) VALUES " +
                    "('Ole Olsen', 11223344, 'ole@gmail.com','Intet tag, tak', 2400, 2800, 2000, true, 'Plastmo sort', 2800, 2400)," +
                    "('Per Jensen', 99887766, 'per@gmail.com','Kan man få tagterrasse?', 4400, 3800, 2200, false, 'Tagsten sort', 2800, 2400)");

        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }

    }


    /**
    Tester forbindelsen til MySql
     */
    @Test
    public void testSetUpOK() {
        assertNotNull(testConnection);
    }


    /**
     Tester, at der kan oprettes en ny customer request, som tilføjes til arraylisten,
     og at antallet af customer requests på arraylisten stemmer overens
     @throws LoginSampleException
     */
    @Test
    public void createReqTest() throws LoginSampleException {
        CustomerRequest cr = new CustomerRequest("Bente Hansen", 11223344, "bente@bentemail.com", "Kan taget være rødt?",2400, 3000, 2200, false, "Tagsten sort", 0, 0);
        RequestMapper.createRequest(cr);
        ArrayList<CustomerRequest> reqList = RequestMapper.showNewRequests(false);
        assertEquals("Bente Hansen", cr.getName());
        assertThat(reqList, hasSize(1));

    }

    /**
     * Tester, at der kan hentes et request ud fra ID
     * @throws LoginSampleException
     * @throws ClassNotFoundException
     */
    @Test
    public void getReqFromIdTest() throws LoginSampleException, ClassNotFoundException {
        CustomerRequest req = RequestMapper.getRequestFromID(1);
        assertEquals(2000, req.getHeight());
        assertEquals(2800, req.getShedl());
    }

}