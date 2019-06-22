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

import java.rmi.Remote; 
import java.rmi.RemoteException;  

// Creating Remote interface for our application 
public class Z4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Obliczenia ob = new Obliczenia();
        long[] ans = ob.naCzynnikiPierwsze(32416187567560001L);
        for(int i = 0; i != ans.length; i++)
            System.out.println(ans[i]);
    }
    
}
