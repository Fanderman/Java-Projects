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
public class Cosin extends Symbol{
    public Cosin(){
        set_arity(1);
    }
    
    public double compute(){
        double a = get_argument();
        return Math.cos(a);
    }
}
