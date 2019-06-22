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
public class Reciprocal extends uOperator{
    
    public Reciprocal(Expression exp) {
        setExp(exp);
    }
    
    public double Compute () {
        return 1/(getExp().Compute());
    }

    public String toString () {
        return "Reciprocal(" + getExp().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Reciprocal) {
            return ((Reciprocal) o).getExp().equals(this.getExp());
        }
        return false;
    }
}
