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

import static DBAccess.MaterialMapperTest.testConnection;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DimensionMapperTest {

    /**
     * DimensionMapper er den klasse, der går i databasen og henter lister med de forskellige specialmål, og befolker drop
     * down-menuerne med disse
     */
    public static Connection testConnection;
    private static String USER = "root";
    private static String USERPW = "root";
    private static String DBNAME = "fogtest?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    /**
     * Forbindelsen til testdatabasen oprettes
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
     * Tabeller med dimensioner skabes
     */
    @Before
    public void beforeEachTest(){
        try ( Statement stmt = testConnection.createStatement()) {
            stmt.execute( "drop table if exists height" );
            stmt.execute( "CREATE TABLE `height` LIKE fog.height;" );
            stmt.execute("INSERT INTO height VALUES " +
                    "(1,'2000')," +
                    "(2,'2200')," +
                    "(3,'2400');");
        } catch ( SQLException ex ) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
        try ( Statement stmt = testConnection.createStatement()) {
            stmt.execute( "drop table if exists length " );
            stmt.execute( "CREATE TABLE `length` LIKE fog.length;" );
            stmt.execute("INSERT INTO length VALUES " +
                    "(1,'2800')," +
                    "(2,'3800')," +
                    "(3,'4800');");
        } catch ( SQLException ex ) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
        try ( Statement stmt = testConnection.createStatement()) {
            stmt.execute( "drop table if exists width" );
            stmt.execute( "CREATE TABLE `width` LIKE fog.width;" );
            stmt.execute("INSERT INTO width VALUES " +
                    "(1,'2400')," +
                    "(2,'3400')," +
                    "(3,'4400');");
        } catch ( SQLException ex ) {
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
        }
    }

    /**
     * Forbindelsen til testdatabasen testes
     */
    @Test
        public void testSetUpOK() {
            assertNotNull( testConnection );
        }

    /**
     * Det testes, om listen med højder indeholder de rigtige værdier, og at antallet af højder passer
     * @throws LoginSampleException
     * @throws ClassNotFoundException
     */
    @Test
    public void testHeightList() throws LoginSampleException, ClassNotFoundException {
            ArrayList<Integer> heightList = DimensionMapper.getHeightList();
            assertThat(heightList, hasSize(3));
            assertThat(heightList, contains(2000, 2200, 2400));
    }
    @Test

    /**
     * Det testes, at listen med længder indholder de rigtige værdier, og at antallet af længder passer
     */
    public void testLengthList() throws LoginSampleException, ClassNotFoundException {
        ArrayList<Integer> lengthList = DimensionMapper.getLengthList();
        assertThat(lengthList, hasSize(4));
        assertThat(lengthList, contains(2800, 3800, 4800, 6000));
    }


    /**
     * Det testes, at listen med bredder indholder de rigtige værdier, og at antallet af bredder passer
     */
        @Test
        public void testWidthList() throws LoginSampleException, ClassNotFoundException {
            ArrayList<Integer> widthList = DimensionMapper.getWidthList();
            assertThat(widthList, hasSize(4));
            assertThat(widthList, contains(2400, 3400, 4400, 7800));
        }


    /**
     * Det testes, at listen med skurbredder indholder de rigtige værdier, og at antallet passer
     */
    @Test
    public void testShedWidthList() throws LoginSampleException, ClassNotFoundException {
        ArrayList<Integer> shedWidthList = DimensionMapper.getShedWidthList();
        assertThat(shedWidthList, hasSize(2));
        assertThat(shedWidthList, contains(1400, 2400));
    }


    /**
     * Det testes, at listen med længder til skuret indholder de rigtige værdier, og at antallet passer
     */
    @Test
    public void testShedLengthList() throws LoginSampleException, ClassNotFoundException {
        ArrayList<Integer> shedLengthList = DimensionMapper.getShedLengthList();
        assertThat(shedLengthList, hasSize(2));
        assertThat(shedLengthList, contains(1500, 1800));
    }
 }