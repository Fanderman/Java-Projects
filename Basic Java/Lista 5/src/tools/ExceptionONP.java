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
public class ExceptionONP extends Exception{
 
    public ExceptionONP(String text, Throwable cause){
        super(text, cause);
    }

    public ExceptionONP(String text){
        super(text);
    }
}


