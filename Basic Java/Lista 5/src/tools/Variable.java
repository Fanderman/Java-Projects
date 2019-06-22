/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jacek
 */
public class Variable extends Symbol {
    
    private final String id;
    private final static TreeMap <String, Double> map = new TreeMap<String, Double>();
    
    public Variable (String id){
        this.id = id;
        set_arity(0);
    }
    
    public void set() throws ONP_InvalidVariable{
        double value = get_argument();
        Pattern r = Pattern.compile("\\p{Alpha}\\p{Alnum}*");
        Matcher m = r.matcher(id);
        if(m.matches()) {
            map.put(id, value);
        }
        else {
            throw new ONP_InvalidVariable("Variable's id was an incorrect chain of symbols");
        }
    }
    
    public static void remove(String x){
        map.remove(x);
    }
    
    public static void clear(){
        map.clear();
    }
    
    public String id(){
        return this.id;
    }
    
    public double compute() throws ONP_InvalidVariable{
        Double a = map.get(this.id);
        if(a != null)
            return a;
        throw new ONP_InvalidVariable("The variable doesn't have an assigned value");
        
    }
}
