/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z4;
import java.rmi.Remote; 
import java.rmi.RemoteException;  

/**
 *
 * @author jacek
 */
public interface LiczbyPierwsze extends Remote
{
    public boolean czyPierwsza (long x) throws RemoteException;
    public long[] naCzynnikiPierwsze (long x) throws RemoteException;
}