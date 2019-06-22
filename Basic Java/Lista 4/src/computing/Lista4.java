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
public class Lista4 {

    public static void main(String[] args) {
        Variable.set("x", 2.0);
        
        Expression exp =
            new Sum(
                new Number(7),
                new Number(5)
            );
        System.out.println("Expression: " + exp.toString());
        System.out.println("Evaluated: " + exp.Compute());
        exp =
            new Sum(
                new Number(2),
                new Multiply(
                    new Variable("x"),
                    new Number(7)
                )
            );
        System.out.println("Expression: " + exp.toString());
        System.out.println("Evaluated: " + exp.Compute());
        exp =
            new Divide(
                new Sub(
                    new Multiply(
                        new Number(3),
                        new Number(11)
                    ),
                    new Number(1)
                ),
                new Sum(
                    new Number(7),
                    new Number(5)
                )
            );
        System.out.println("Expression: " + exp.toString());
        System.out.println("Evaluated: " + exp.Compute());
        exp =
            new Arctan(
                new Divide(
                    new Multiply(
                        new Sum(
                            new Variable("x"),
                            new Number(13)
                        ),
                        new Variable("x")
                    ),
                    new Number(2)
                )
            );
        System.out.println("Expression: " + exp.toString());
        System.out.println("Evaluated: " + exp.Compute());
        exp =
            new Sum(
                new Power(
                    new Number(2),
                    new Number(5)
                ),
                new Multiply(
                    new Variable("x"),
                    new Logarythm(
                        new Number(2),
                        new Variable("y")
                    )
                )
            );
        System.out.println("Expression: " + exp.toString());
        System.out.println("Evaluated: " + exp.Compute());

    }
}