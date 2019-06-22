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
public class Arctan extends uOperator{
    
    public Arctan(Expression exp) {
        setExp(exp);
    }
    
    public double Compute () {
        return Math.atan(getExp().Compute());
    }

    public String toString () {
        return "Arctan(" + getExp().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Arctan) {
            return ((Arctan) o).getExp().equals(this.getExp());
        }
        return false;
    }
}
