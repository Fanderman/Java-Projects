/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jacek
 */
abstract public class Symbol implements Computable{
    
    private int arity;
    private LinkedList<Double> list = new LinkedList<Double>();
    
    public int arity(){
        return this.arity;
    }
    void set_arity(int arity){
        this.arity = arity;
    }
    
    public void add_argument(double arg){
        this.list.add(arg);
    }
    double get_argument(){
        return this.list.pop();
    }
    
    public static Symbol identify(String arg) throws ExceptionONP{
        if(arg.equals("=")){
            return new Assign();
        }
        if(arg.equals("+")){
            return new Sum();
        }
        if(arg.equals("-")){
            return new Sub();
        }
        if(arg.equals("/")){
            return new Divide();
        }
        if(arg.equals("*")){
            return new Multiply();
        }
        if(arg.equals("Min")){
            return new Min();
        }
        if(arg.equals("Max")){
            return new Max();
        }
        if(arg.equals("Log")){
            return new Log();
        }
        if(arg.equals("Pow")){
            return new Power();
        }
        if(arg.equals("Abs")){
            return new Absolute();
        }
        if(arg.equals("Sgn")){
            return new Sign();
        }
        if(arg.equals("Floor")){
            return new Floor();
        }
        if(arg.equals("Ceil")){
            return new Ceil();
        }
        if(arg.equals("Frac")){
            return new Frac();
        }
        if(arg.equals("Sin")){
            return new Sin();
        }
        if(arg.equals("Cos")){
            return new Cosin();
        }
        if(arg.equals("Atan")){
            return new Atan();
        }
        if(arg.equals("Acot")){
            return new Acot();
        }
        if(arg.equals("Ln")){
            return new Ln();
        }
        if(arg.equals("Exp")){
            return new Exp();
        }
        if(arg.equals("E")){
            return new E();
        }
        if(arg.equals("Pi")){
            return new Pi();
        }
        if(arg.equals("Phi")){
            return new Phi();
        }
        Pattern r = Pattern.compile("(\\p{Digit}+\\056\\p{Digit}+)|(\\p{Digit}+)");
        Matcher m = r.matcher(arg);
        if(m.matches()) {
            return new Number(Double.parseDouble(arg));
        }
        r = Pattern.compile("\\p{Alpha}\\p{Alnum}*");
        m = r.matcher(arg);
        if(m.matches()) {
            return new Variable(arg);
        }
        throw new ONP_InvalidSymbol("Couldn't identify the substring as any correct symbol");
    }
}
