/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computing;

import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author jacek
 */
public class Variable extends Expression{
    
    public final String key;
    public final double value;
    private final static Map <String, Double> map = new HashMap<String, Double>();

    public Variable (String key) {
        Double val = map.get(key);
        if (val != null) {
            this.key = key;
            this.value = val;
        }
        else {
            this.key = "NULL";
            this.value = Double.NaN;
        }
    }
    
    public static void set (String key, double value) {
        map.put(key, value);
    }
    
    public double Compute() {
        return this.value;
    } 
    
    public String toString () {
        return "(" + this.key + ") = (" + String.valueOf(this.value) + ")";
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
