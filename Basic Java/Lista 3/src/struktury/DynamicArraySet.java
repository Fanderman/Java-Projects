package struktury;

/**
 * A dynamic set, no need to declare max size. 
 */
public class DynamicArraySet extends ArraySet {
    
    /** 
     * Construct a Dynamic Set, starts with maxSize = 2.
     */
    public DynamicArraySet(){
        super();
    }
    
    @Override
    public void insert(Pair p) throws Exception {
        if (size == maxSize) {
            maxSize *= 2;
            Pair[] newArray = new Pair[maxSize];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size] = p;
        size++;
    }
}
