package struktury;

public class ArraySet extends Set {
    
    /**
     * An array of pairs
     */
    protected Pair[] array;
    /** 
     * current amount of elements in the array
     */
    protected int size;
    /**
     * maximum amount of elements that can be inserted
     */
    protected int maxSize;
    
    /**
     * create a new ArraySet
     * @param size maximum size of our set
     * @throws Exception when size is set to less than 2
     */
    public ArraySet(int size) throws Exception{
        if (size < 2)
             throw new Exception("size of ArraySet cannot be lower than 2");
        maxSize = size;
        array = new Pair[size];
        this.size = 0;
    }
    
    /**
     * create a new ArraySet with max size of 2
     */
    public ArraySet(){
        this.array = new Pair[2];
        this.size = 0;
        this.maxSize = 2;
    }
    public Pair search(String key) throws Exception{
        for(Pair x : array){
            if(x.key.equals(key))
                return x;
        }
        throw new Exception("No entry with key " + key + " found");   
    }
    
    public void insert(Pair p) throws Exception{
        if(size == maxSize)
            throw new Exception("Cannot exceed the max size of set");
        array[size] = p;
        size++;
    }
    
    public double read(String key) throws Exception{
        return search(key).getValue();
    }
    
    public void set(Pair p) throws Exception{
        Pair np; 
        try{
            np = search(p.key);
            np.setValue(p.getValue());
        }
        catch (Exception e){
            insert(p);
        }
    }
    
    public void clear(){
        array = new Pair[maxSize];
        size = 0;
    }
    
    public int size(){
        return size;
    }

    public int maxSize(){
        return maxSize;
    }
}
