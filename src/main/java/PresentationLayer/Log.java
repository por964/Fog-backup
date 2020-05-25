package PresentationLayer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * Formålet med denne klasse er at lagre fejl i en log fil.
 * Der er 3 niveauer for fejltype: finest, info og severe.
 * Nye loggings bliver tilføjet til den eksisterende fil.
 */

public class Log {

    private static final String FILENAME = "log.log";
    private static final String FILEPATH = "C:/Datamatiker/2.semester/Fog/src/main/java/DBAccess/";

    //private static final String FILEPATH = "/var/log/tomcat8/";
    private static final String PATH = FILEPATH + FILENAME;

    private Log() {
    }

    /**
     *
     * @param lvl fejlens niveau
     * @param decription fejlens tekst
     * @throws IOException fejl i fil, forbindelse, læs/skriv
     */
    private static void log(Level lvl, String decription) throws IOException {

        Logger logger = Logger.getLogger(Log.class.getName());   //Alle operationer på logger er thread safe

        FileHandler fh = new FileHandler(PATH, true); //Sti til fil samt tilføjer log til filen (append)
        fh.setFormatter(new VerySimpleFormatter());
        logger.addHandler(fh);

        logger.setLevel(Level.FINEST);   //Her sættes niveauet for logningen.

        logger.log(lvl, decription);

        fh.close();

    }

    public static void severe(String description) {

        try {
            log(Level.SEVERE, description);
        } catch (Exception e) {
        }
    }

    public static void info(String description) {

        try {
            log(Level.INFO, description);
        } catch (Exception e) {
        }
    }

    public static void finest(String description) {

        try {
            log(Level.FINEST, description);
        } catch (Exception e) {
        }
    }
}

/**
 * Metode der tilføjer tidsstempel til en logning
 */
class VerySimpleFormatter extends Formatter {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";


    @Override
    public String format(final LogRecord record) {
        return String.format(
                "%1$s %2$-7s %3$s\n",
                new SimpleDateFormat(PATTERN).format(
                        new Date(record.getMillis())),
                record.getLevel().getName(),
                formatMessage(record));
    }
}