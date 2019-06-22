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
public class Pi extends Symbol{
    public Pi(){
        set_arity(0);
    }
    
    public double compute(){
        return Math.PI;
    }
}
