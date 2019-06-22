/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z4;

/**
 *
 * @author jacek
 */
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  

public class Client {  
   private Client() {}  
   public static void main(String[] args) {  
       LiczbyPierwsze a = new Obliczenia();
        try {  
           // Getting the registry 
           Registry registry = LocateRegistry.getRegistry(1099); 

           // Looking up the registry for the remote object 
           LiczbyPierwsze stub = (LiczbyPierwsze) registry.lookup("LiczbyPierwsze"); 

           // Calling the remote method using the obtained object 
           System.out.println(stub.czyPierwsza(1)); 
           long[] ans = stub.naCzynnikiPierwsze(32416187567560001L);
            for(int i = 0; i != ans.length; i++)
                System.out.println(ans[i]);

           // System.out.println("Remote method invoked"); 
        } catch (Exception e) {
           System.err.println("Client exception: " + e.toString()); 
           e.printStackTrace(); 
        } 
   } 
}
