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
public class Min extends bOperator{
    public Min(Expression exp1, Expression exp2) {
        setExp(exp1);
        setExp2(exp2);
    }
    
    public double Compute () {
        return Math.min(getExp().Compute(),getExp2().Compute());
    }

    public String toString () {
        return "Min(" + getExp().toString() + " , " + getExp2().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Min) {
            return (((Min) o).getExp().equals(this.getExp()) && 
                    ((Min) o).getExp2().equals(this.getExp2()));
        }
        return false;
    }
}
