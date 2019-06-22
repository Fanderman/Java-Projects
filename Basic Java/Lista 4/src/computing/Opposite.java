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
public class Opposite extends uOperator{
    
    public Opposite(Expression exp) {
        setExp(exp);
    }
    
    public double Compute () {
        return -1 * (getExp().Compute());
    }

    public String toString () {
        return "Opposite(" + getExp().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Opposite) {
            return ((Opposite) o).getExp().equals(this.getExp());
        }
        return false;
    }
}
