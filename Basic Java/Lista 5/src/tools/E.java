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
public class E extends Symbol{
    public E(){
        set_arity(0);
    }
    
    public double compute(){
        return Math.E;
    }
}
