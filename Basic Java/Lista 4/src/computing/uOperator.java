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
public abstract class uOperator extends Expression {

    private Expression exp;

    public void setExp (Expression exp) {
        this.exp = exp;
    }

    public Expression getExp () {
        return this.exp;
    }

}
