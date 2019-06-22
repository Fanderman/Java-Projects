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
public class Number extends Symbol{
    private final double value;
    
    public Number(double value){
        this.value = value;
        set_arity(0); 
    }
    
    public double compute(){
        return this.value;
    }
}
