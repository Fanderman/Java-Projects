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
public class Log extends Symbol{
    public Log(){
        set_arity(2);
    }
    
    public double compute() throws ONP_InvalidLogarithm{
        double a = get_argument();
        double b = get_argument();
        if(a < 0 || b <= 0){
            throw new ONP_InvalidLogarithm("Invalid argument(s) for Log function");
        }
        return Math.log(b)/Math.log(a);
    }
}
