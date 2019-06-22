package struktury;
import java.util.*;

public class Pair {
    /**
     * pair identifier
     */
    public final String key;
    /**
     * pair value
     */
    private double value;
    
    /**
     * creates a new pair
     * @param key pair's identifier
     * @param value pair's value
     * @throws Exception if key is empty or null 
     */
    public Pair(String key, double value) throws Exception{
        if(key.equals(""))
            throw new Exception("Error: incorrect 'key' value.");
        this.key = key;
        this.value = value;
    }
    
    /**
     * sets the pair's value
     * @param value the value to replace the previous one with
     */
    public void setValue(double value){
        this.value = value;
    }
    
    /**
     * @return value of this pair
     */
    public double getValue(){
        return value;
    }
    /**
     * Overrided toString().
     * @return formated key and value of pair
     */
    @Override 
    public String toString(){ 
        return "(" + key + ", " + value + ")";
    }
    
    /**
     * @param obj the object we want to compare our pair to.
     * @return true if compared pairs have the same key (string).
     */
    @Override 
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Pair a = (Pair) obj;
        return Objects.equals(a.key,this.key);
    }
}
