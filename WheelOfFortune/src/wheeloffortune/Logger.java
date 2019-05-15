/*
 * IST 261
 * Fall 2017
 * Instructor: Phil O'Connell
 * Student: Mostafa Apu
 * PSU User: mja5612
 */
package wheeloffortune;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This logger class is used to log messages to a file that will be helpful with
 * debugging
 */
public class Logger {

    private static Logger _instance; // for singleton instance
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir"); // gets the
    private static final String LOG_FILE = String.format("%s\\logFile.txt", CURRENT_DIRECTORY);
    private final File _file = new File(LOG_FILE);
    private final Date _now = new Date();
    private final SimpleDateFormat _militaryTime = new SimpleDateFormat("MM-dd-yyyy kk:mm:ss");

    /**
     * Private constructor following singleton design patter.
     */
    private Logger() {}

    /**
     * logging levels to show the importance of the messages
     *
     * ALL - all levels (DEBUG,INFO,WARN,ERROR,FATAL) DEBUG - Messages to help
     * with debugging (INFO,WARN,ERROR,FATAL) INFO - Information that will help
     * track the progress of the application (WARN,ERROR,FATAL) WARN - Not a
     * critical issue, but something to note (ERROR,FATAL) ERROR - A critical
     * issue, but the program can continue (FATAL) FATAL - An issue so critical,
     * that the application must exit
     */
    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR, FATAL
    }

    /**
     * main creates instance of logger class. NetBeans made me throw exception,
     * even though this method just passes file name as a string to another
     * method, not sure why.
     */
    public static void main(String[] args) {
        //Create instance of this class.
        Logger logger = Logger.getInstance();

        //Log file path to log file.
        logger.log(LogLevel.INFO, "Board Created");
        logger.log(LogLevel.DEBUG, "Board Created");
        logger.log(LogLevel.FATAL, "Board Created");
        logger.log(LogLevel.ERROR, "Board Created");
        logger.log(LogLevel.WARN, "Board Created");

        // tests if the exceptions works!
        // (SBI-19)
        try {
            int stuff = 2 / 0;
        } catch (Exception e) {
            logger.log(e);
        }

        System.out.printf("The file can be found in \n'%s'", LOG_FILE);
    }

    /**
     * creates a Logger instance if not made already and returns the instance
     */
    public static Logger getInstance() {
        if (_instance == null) {
            _instance = new Logger();
        }

        return _instance;
    }

    /**
     * receives argument and writes it to the file along with dates and time
     */
    public void log(LogLevel level, String msg) {
        try {
            // makes a file if doesn't exists
            createFile();

            // writes to the file
            String longMsg =
                    _militaryTime.format(_now) + " [" + level + "] " + msg + System.lineSeparator();
            filterAndWriteToFile(level, longMsg);

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * if file doesn't exist, then create it
     */
    private void createFile() {
        try {
            if (!_file.exists()) {
                _file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * filters depending on what the config file is and then writes to the file
     * Ex. If config file is set to 'INFO' the DEBUG messages won't be written
     */
    private void filterAndWriteToFile(LogLevel level, String longMsg) throws IOException {
        String configFileLevel = Config.getSetting("logLevel");

        if (configFileLevel.equals(LogLevel.DEBUG.toString()) || configFileLevel.equals("ALL")) {
            writeToFile(longMsg);
        } else if (configFileLevel.equals(LogLevel.INFO.toString())) {
            if (!(level == LogLevel.DEBUG)) {
                writeToFile(longMsg);
            }
        } else if (configFileLevel.equals(LogLevel.WARN.toString())) {
            if (!(level == LogLevel.DEBUG
                    || level == LogLevel.INFO)) {
                writeToFile(longMsg);
            }
        } else if (configFileLevel.equals(LogLevel.ERROR.toString())) {
            if (!(level == LogLevel.DEBUG
                    || level == LogLevel.INFO
                    || level == LogLevel.WARN)) {
                writeToFile(longMsg);
            }
        } else if (configFileLevel.equals(LogLevel.FATAL.toString())) {
            if (!(level == LogLevel.DEBUG
                    || level == LogLevel.INFO
                    || level == LogLevel.WARN
                    || level == LogLevel.ERROR)) {
                writeToFile(longMsg);
            }
        }
    }

    /**
     * (SBI-18)
     * it will act as a wrapper method
     */
    public void log(Exception exception) {
        LogLevel logLevel = LogLevel.WARN;
        this.log(logLevel, exception);
    }

    /**
     * appends the log messages to the log file
     */
    private void writeToFile(String longMsg) {
        try {
            Files.write(Paths.get(LOG_FILE), longMsg.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * (SBI-18)
     * receives a level and exception message and writes it to the file along
     * with dates and time
     */
    public void log(LogLevel level, Exception exception) {
        this.log(level, exception.toString());
    }

}
