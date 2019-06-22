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
public class Cosinus extends uOperator{
    
    public Cosinus(Expression exp) {
        setExp(exp);
    }
    
    public double Compute () {
        return Math.cos(getExp().Compute());
    }

    public String toString () {
        return "Cosinus(" + getExp().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Cosinus) {
            return ((Cosinus) o).getExp().equals(this.getExp());
        }
        return false;
    }
}
