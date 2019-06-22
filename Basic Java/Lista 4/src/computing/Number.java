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
public class Number extends Expression {
    
    public final double value;
    
    public Number (double value) {
        this.value = value;
    }
    
    public double Compute(){
        return this.value;
    }
    public String toString () {
        return "Number(" + this.value + ")";
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
