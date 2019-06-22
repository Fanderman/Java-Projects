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
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class Server extends Obliczenia { 
   public Server() {} 
   public static void main(String args[]) { 
      try { 
         // Instantiating the implementation class 
         Obliczenia obj = new Obliczenia(); 
    
         // Exporting the object of implementation class  
         // (here we are exporting the remote object to the stub) 
         LiczbyPierwsze stub = (LiczbyPierwsze) UnicastRemoteObject.exportObject(obj, 0);  
         
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.createRegistry(1099);
         registry.rebind("LiczbyPierwsze", stub);
         //registry.bind("Hello", stub);  
         System.out.println("RMI server bound...");
         System.out.println("Awaiting requests...");
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
} 