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
public class Frac extends Symbol{
    public Frac(){
        set_arity(1);
    }
    
    public double compute(){
        double a = get_argument();
        return a - Math.floor(a);
    }
}
