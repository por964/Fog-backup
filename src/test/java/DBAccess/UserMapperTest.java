package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
Test af klassen UserMapper, som bruges, når der oprettes et brugerlogin til en medarbejder
 */

public class UserMapperTest {
    public static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "root";
    private static String DBNAME = "fogtest?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    /**
    Der oprettes forbindelse til testdatabasen
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
    Der oprettes en tabel magen til Users
     */
    @Before
    public void beforeEachTest() {
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("drop table if exists users");
            stmt.execute("CREATE TABLE `users` LIKE fog.users;");
            stmt.execute("insert into users VALUES" +
                    "(1, 'test', 'test')," +
                    "(2, 'admin','admin')");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }


    }

    /**
     Tester, at der er forbindelse til MySql
     */
    @Test
    public void testSetUpOK() {
        assertNotNull( testConnection );
    }

    /**
    Tester, om vi kan logge ind med kodeord og password
     */
    @Test
    public void loginTest() throws LoginSampleException {
        User user = UserMapper.login( "test", "test" );
        assertTrue( user != null );
    }

    /**
    Tester, om der smides en exception, hvis kodeordet er forkert
     */
    @Test( expected = LoginSampleException.class )
    public void loginTest2() throws LoginSampleException {

        User user = UserMapper.login( "test", "forkertkode" );
    }

}
