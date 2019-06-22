package struktury;

public class Lista3 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int trials = 6;
            int catched = 0;
            ArraySet arr = new ArraySet(2);
            DynamicArraySet dyn = new DynamicArraySet();

            Pair p1 = new Pair("a", 5);
            Pair p2 = new Pair("b", 2);
            Pair p3 = new Pair("c", 4);
            Pair p4 = new Pair("a", 3);
            
            arr.insert(p1);
            arr.set(p1);
            if(arr.size() == 1)
                catched++;

            arr.insert(p2);
            try {
                arr.set(p3);
            } 
            catch (Exception e){
                catched++;
            }
            
            arr.set(p4);
            if(arr.read("a") == 3)
                catched++;

            try {
                arr.clear();
                arr.read("k1");
            } 
            catch (Exception e){
                catched++;
            }
            
            for (int i = 0; i < 128; i++)
                dyn.insert(p1);

            if(dyn.maxSize() == 128)
                catched++;
            dyn.set(p3);
            if(dyn.maxSize() == 256)
                catched++;
            
            if(catched == trials)
                System.out.println("All tests passed.");
            else
                System.out.println("Failed to pass all the tests.");
        } 
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
}
