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
public class Assign extends Symbol{
    public Assign(){
        set_arity(2);
    }
    
    public double compute() throws ExceptionONP{
        throw new ExceptionONP("Tried to assign value to a non-variable");
    }
}
