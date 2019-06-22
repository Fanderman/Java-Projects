package struktury;

/**
 * Base class for real sets
 */
public abstract class Set {

    /** 
     * searches for a pair with given key 
     * @param key a key to search for
     * @return a pair such that this.key.equals(key)
     * @throws Exception if no entry matches given key
     */
    public abstract Pair search(String key) throws Exception;

    /** 
     * inserts a new pair into the set
     * @param p a pair to insert 
     * @throws Exception if the maximum size of structure is exceeded
     */
    public abstract void insert(Pair p) throws Exception;

    /** 
     * retrieves value under the given key if an entry exists 
     * @param key a key to search for
     * @return the value under the key
     * @throws Exception if no entry matches given key
     */
    public abstract double read(String key) throws Exception;

    /**
     * updates the set with a new pair, inserts a new element if it does not exist
     * @param p a pair to insert
     * @throws Exception if the maximum size of structure is exceeded
     */
    public abstract void set(Pair p) throws Exception;

    /**
     * deletes all the entries in set
     */
    public abstract void clear();

    /**
     * returns the amount of elements in set.
     * @return the number of elements in set
     */
    public abstract int size();

    /**
     * returns the maximum amount of elements in set.
     * @return the maximum number of elements in set
     */
    public abstract int maxSize();

}
