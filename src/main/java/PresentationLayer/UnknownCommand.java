package PresentationLayer;

import FunctionLayer.LoginSampleException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
Formålet med denne klasse er at give en fejlbesked til brugeren hvis den valgte webside ikke findes.
 */
public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException {
        String msg = "Unknown command. Contact IT";
        throw new LoginSampleException( msg );
    }

}
