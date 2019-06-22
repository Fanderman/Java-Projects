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
public abstract class bOperator extends uOperator{
    
    private Expression exp2;
    
    public void setExp2 (Expression exp) {
        this.exp2 = exp;
    }

    public Expression getExp2 () {
        return this.exp2;
    }
}
