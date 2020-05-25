package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 Formålet med denne klasse er at skabe forbindelse til databasen,
 enten via localhost eller via droplet.
 @author Claes
 */
public class Connector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Connection singleton;

    public static void setConnection( Connection con ) {
        singleton = con;
    }

    /**Opretter en forbindelse til MySQL databasen hvis ikke denne er oprettet.
     * Singleton, den samme databaseforbindelse bruges alle steder i applikationen
     *
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection connection() throws ClassNotFoundException, SQLException {
        if ((singleton == null) || singleton.isClosed()) {
            setDBCredentials();
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            singleton = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        }
        return singleton;
    }


    /**Hvis ENV fil ikke er tom, hentes credentials til denne og der oprettes forbindelse.
     * Hvis deployed = null, oprettes forbindelse via localhost.
     *
     */
    public static void setDBCredentials() {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null){
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            URL = System.getenv("JDBC_CONNECTION_STRING");
            USERNAME = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
        } else {
            // Localhost
            URL = "jdbc:mysql://localhost:3306/fog?serverTimezone=CET&useSSL=false";
            USERNAME = "root";
            PASSWORD = "Rufbtr11@";
        }
    }
}
