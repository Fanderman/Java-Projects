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
public class Divide extends Symbol{
    
    public Divide(){
        set_arity(2);
    }
    
    public double compute() throws ONP_DivideBy0{
        double a = get_argument();
        double b = get_argument();
        if(b == 0)
            throw new ONP_DivideBy0("tried to divide by 0");
        return a / b;
    }
}
