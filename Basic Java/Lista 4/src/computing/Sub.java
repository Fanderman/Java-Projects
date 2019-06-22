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
public class Sub extends bOperator{
    public Sub(Expression exp1, Expression exp2) {
        setExp(exp1);
        setExp2(exp2);
    }
    
    public double Compute () {
        return (getExp().Compute()) - (getExp2().Compute());
    }

    public String toString () {
        return "(" + getExp().toString() + ") - (" + getExp2().toString() + ")";
    }
    
    public boolean equals (Object o) {
        if (o instanceof Sub) {
            return (((Sub) o).getExp().equals(this.getExp()) && 
                    ((Sub) o).getExp2().equals(this.getExp2()));
        }
        return false;
    }
}
