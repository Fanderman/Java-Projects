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
public class Max extends bOperator{
    public Max(Expression exp1, Expression exp2) {
        setExp(exp1);
        setExp2(exp2);
    }
    
    public double Compute () {
        return Math.max(getExp().Compute(),getExp2().Compute());
    }

    public String toString () {
        return "Max(" + getExp().toString() + ", " + getExp2().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Max) {
            return (((Max) o).getExp().equals(this.getExp()) && 
                    ((Max) o).getExp2().equals(this.getExp2()));
        }
        return false;
    }
}
