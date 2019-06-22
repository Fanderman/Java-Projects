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
public class Logarythm extends bOperator{
    public Logarythm(Expression exp1, Expression exp2) {
        setExp(exp1);
        setExp2(exp2);
    }
    
    public double Compute () {
        return Math.log(getExp2().Compute())/Math.log(getExp().Compute());
    }

    public String toString () {
        return "Log(" + getExp().toString() + ", " + getExp2().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Logarythm) {
            return (((Logarythm) o).getExp().equals(this.getExp()) && 
                    ((Logarythm) o).getExp2().equals(this.getExp2()));
        }
        return false;
    }
}
