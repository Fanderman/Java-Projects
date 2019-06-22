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
public abstract class Expression implements Computable{
    // ...
    /** 
     * method used for summing up expressions 
     * @param exp expressions to sum
     * @return sum of expressions */
    public static double sum (Expression... exp) {
        double sum = 0;
        for(int i = 0; i != exp.length; i++){
            sum += exp[i].Compute();
        }
        return sum;
    }
    /** 
     * method used for multiplying expressions
     * @param exp expressions to multiply
     * @return product of expressions */
    
    public static double product (Expression... exp) {
        double pro = 1;
        for(int i = 0; i != exp.length; i++){
            pro *= exp[i].Compute();
        }
        return pro;
    }
    
    /**
     * toString() override
     */
    @Override
    public abstract String toString ();

    /**
     * equals(object o) override
     */
    @Override
    public abstract boolean equals (Object o);
}
