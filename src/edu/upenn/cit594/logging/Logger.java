package edu.upenn.cit594.logging;

import edu.upenn.cit594.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Implement singleton design to create logger class . To log message to log
 * file.
 * 
 * @author linjinhuang
 *
 */
public class Logger {
    private static Logger instance = new Logger(Main.logfileName);
    private PrintWriter out;

    /**
     * Constructor for Logger class.
     * 
     * @param filename String
     */
    private Logger(String fileName) {
        try {
            out = new PrintWriter(new FileOutputStream(new File(fileName),true));
        } catch (Exception e) {

        }
    }

    public static Logger getInstance() {
        return instance;
    }

    /**
     * To log in messages.
     * 
     * @param msg String
     */
    public void log(String msg) {
        out.append(msg + "\n");
        out.flush();
    }

}