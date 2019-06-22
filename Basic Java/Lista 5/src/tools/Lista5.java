
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.*;
import java.util.logging.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jacek
 */

public class Lista5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(System.in);
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        boolean exit = false;
        while(!exit){       
            boolean event = false;
            Logger.info("Enter ONP expressions:");
            String next = input.nextLine();
            Logger.info(next);
            if(next.equals("exit")){
                Logger.info("Exiting...");
                exit = true;
                event = true;
            }
            Pattern r = Pattern.compile("calc(.)*");
            Matcher m = r.matcher(next);
            if(m.matches()) {
                event = true;
                try{    
                    ONP x = new ONP(next.substring(4));
                    Logger.info("Result: " + x.compute());
                }
                catch(ExceptionONP e){
                    Logger.severe(e.toString());
                    if(e.getCause() != null){
                        Logger.severe("Cause: " + e.getCause().toString());
                    }
                }
            }
            r = Pattern.compile("clear(.)*");
            m = r.matcher(next);
            if(m.matches()){
                event = true;
                Logger.info("Erasing declared variables...");
                ONP.Erase(next.substring(5));
            }        
            if(!event)
                Logger.severe("Unknown expression");
            assert event == true : "Unknown Command";
        }
    }   
}
