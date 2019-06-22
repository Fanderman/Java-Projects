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
public class Absolute extends uOperator{
    
    public Absolute(Expression exp) {
        setExp(exp);
    }
    
    public double Compute () {
        double val = (getExp().Compute());
        if(val < 0)
            return -1 * val;
        return val;
    }

    public String toString () {
        return "Absolute(" + getExp().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Absolute) {
            return ((Absolute) o).getExp().equals(this.getExp());
        }
        return false;
    }
}
