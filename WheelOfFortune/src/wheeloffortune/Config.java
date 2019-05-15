/*
 * IST 261
 * Fall 2017
 * Instructor: Phil O'Connell
 * Student: Mostafa Apu
 * PSU User: mja5612
 */
package wheeloffortune;

import java.io.IOException;
import java.util.Properties;

/**
 * got the code from this link:
 * https://stackoverflow.com/questions/7665310/how-to-use-config-properties-file-throughout-the
 * -classes/7665341#7665341
 *
 * returns the value from the config.properties file.
 */
public final class Config {

    private static final Properties PROPERTIES = new Properties();

    /**
     *
     * looks for the file name specified to make sure it exists
     */
    static {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            PROPERTIES.load(loader.getResourceAsStream("config.properties"));
        } catch (IOException e) {
            // (SBI-19)
            Logger.getInstance().log(e);
        }
    }

    /**
     * accepts and variable name for the config.properties file to look in the
     * config.properties file with the passed in String
     */
    public static String getSetting(String key) {
        return PROPERTIES.getProperty(key);
    }

}
