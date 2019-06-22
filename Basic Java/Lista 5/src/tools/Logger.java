/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author jacek
 */
import java.util.logging.*;
import java.io.IOException;;


public class Logger {

    private static java.util.logging.Logger log;

    static {
        log = java.util.logging.Logger.getLogger("Logger");
        log.setLevel(Level.ALL);
        
        FileHandler fh;

        try {
            fh = new FileHandler("calc.log", true);
            fh.setLevel(Level.ALL);
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
        }
        catch (IOException e) {
            log.log(Level.SEVERE, "Could not open log file");
        }
    }

    public static void info (String msg) {
        log.log(Level.INFO, "[INFO] " + msg);
    }

    public static void severe (String msg) {
        log.log(Level.SEVERE, "[SEVERE] " + msg);
    }

}

