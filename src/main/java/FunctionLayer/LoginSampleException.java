package FunctionLayer;

/**
 * Formålet med denne klasse er at sende en besked til brugeren
 * hvis de indtastede credentials ikke stemmer med db tabellen users
 * (brugeren ikke findes).
 *
 */
public class LoginSampleException extends Exception {

    public LoginSampleException(String msg) {
        super(msg);
    }
    

}
