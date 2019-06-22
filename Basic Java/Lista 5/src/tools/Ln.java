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
public class Ln extends Symbol{
    public Ln(){
        set_arity(1);
    }
    
    public double compute() throws ONP_InvalidLogarithm{
        double a = get_argument();
        if(a <= 0)
            throw new ONP_InvalidLogarithm("Invalid arguemnt for Ln function");
        return Math.log(a);
    }
}
