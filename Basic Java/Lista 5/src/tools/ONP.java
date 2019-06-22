/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * @author jacek
 */
public class ONP {
    /*private static TreeMap <String, Double> map = new TreeMap<String, Double>();
    */
    private ArrayDeque<Double> stack;
    
    public static void Erase(String text){
        boolean start = false;
        String arg = "";
        int l = 0;
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == ' '){
                if(start){
                    l++;
                    Variable.remove(arg);
                    arg = "";
                    start = false;
                }
            }
            else{
                start = true;
                arg += text.charAt(i);
            }
        }
        if(start){
            l++;
            Variable.remove(arg);
        }
        if(l == 0){
            Variable.clear();
        }
    }
    
    public ONP(String text) throws ExceptionONP{
        boolean start = false;
        String arg = "";
        stack = new ArrayDeque<Double>();
        ArrayList<Symbol> symbols = new ArrayList <Symbol>();
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == ' '){
                if(start){
                    try{
                        symbols.add(Symbol.identify(arg));
                        arg = "";
                        start = false;
                    }
                    catch(ExceptionONP e){
                        throw new ExceptionONP("Incorrect argument at "+ (i+1) +". character", e);
                    }
                }
            }
            else{
                start = true;
                arg += text.charAt(i);
            }
        }
        if(start){
            try{
                symbols.add(Symbol.identify(arg));
            }
            catch(ExceptionONP e){
                throw new ExceptionONP("Incorrect argument at last character", e);
            }
        }
        for (int i = 0; i < symbols.size(); i++) {
            for(int j = 0; j < symbols.get(i).arity(); j++){
                try{
                    double val = stack.pop();
                    symbols.get(i).add_argument(val);
                }
                catch(Exception e){
                    throw new ONP_EmptyStack("Tried to get argument from empty stack, " + (i+1) + ". symbol",e);
                }
            }
            if(symbols.get(i) instanceof Variable && i+1 < symbols.size() && symbols.get(i+1) instanceof Assign){
                //assign the value to the variable and skip assign statement
                Variable a = (Variable)symbols.get(i);
                try{
                    double val = stack.pop();
                    a.add_argument(val);
                }
                catch(Exception e){
                    throw new ONP_EmptyStack("Tried to get argument from empty stack, " + (i+1) + ". symbol",e);
                }
                a.set();
                stack.add(a.compute());
                i++;
            }
            else{
                try{
                    double val = (symbols.get(i)).compute();
                    stack.add(val);
                }
                catch(ExceptionONP e){
                    throw new ExceptionONP("Couldn't compute function correctly, " + (i+1) + ". symbol", e);
                }
            }
        }
    }
    
    public double compute() throws ExceptionONP{
        if(stack.size() != 1)
            throw new ExceptionONP("Invalid formula, ended up with incorrect amount of results on stack");
        return stack.pop();
    }
}
