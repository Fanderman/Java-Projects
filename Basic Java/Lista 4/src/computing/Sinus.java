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
public class Sinus extends uOperator{
    
    public Sinus(Expression exp) {
        setExp(exp);
    }
    
    public double Compute () {
        return Math.sin(getExp().Compute());
    }

    public String toString () {
        return "Sinus(" + getExp().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Sinus) {
            return ((Sinus) o).getExp().equals(this.getExp());
        }
        return false;
    }
}
