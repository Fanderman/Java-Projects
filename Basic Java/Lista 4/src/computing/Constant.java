/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computing;

/**
 *
 * @author jacek
 */
public class Constant extends Expression{
    
    public final String name;
    public final double value;
    public Constant(String name){
        double val = 0;
        if(name.equals("pi"))
            val = 3.14159;
        if(name.equals("e"))
            val = 2.71828;
        if(name.equals("phi"))
            val = 1.61803;
        this.value = val;
        this.name = name;
    }
    
    public double Compute(){
        return value;
    }
    
    public String toString () {
        return "(" + name + "=" + value + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Constant) {
            return ((Constant) o).value == this.value;
        }
        if (o instanceof Number) {
            return((Number) o).value == this.value;
        }
        if (o instanceof Variable){
            return((Variable) o).value == this.value;
        }
        return false;
    }
}
