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
public class Multiply extends Symbol{
    
    public Multiply(){
        set_arity(2);
    }
    
    public double compute(){
        double a = get_argument();
        double b = get_argument();
        return a * b;
    }
}
