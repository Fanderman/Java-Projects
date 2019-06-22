/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z4;

import static java.lang.Math.floor;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import java.util.ArrayList;


/**
 *
 * @author jacek
 */
public class Obliczenia implements LiczbyPierwsze{
    
    
    @Override
    public boolean czyPierwsza (long x){
        int range = (int)(floor(sqrt(x))+1);
        
        boolean sito[] = new boolean[range];
        for(int i = 2; i < range; i++)
            sito[i] = true;
        
        if(x == 2)
            return true;
        if (x % 2 == 0)
            return false;
        
        for(int j = 2; j < range; j += 2)
            sito[j] = false;
        
        for(int i = 3; i < range; i+=2){
            if(sito[i]){
                if(x % i == 0)
                    return false;
                
                for(int j = i; j < range; j += i)
                    sito[j] = false;
            }
        }
        return true;
    }
    
    @Override
    public long[] naCzynnikiPierwsze (long x){
        ArrayList <Long> Czynniki = new ArrayList <Long>();
        int range = (int)(floor(sqrt(x))+1);
        Czynniki.add(1L);
        
        ArrayList <Long> Pierwsze = new ArrayList <Long>();
        Pierwsze.add(2L);
        
        boolean sito[] = new boolean[min(range,1000000)];
        int mln = 0;
        for(int i = 2; i < min(range,1000000); i++)
            sito[i] = true;
        
        while(x%2 == 0){
            x /= 2;
            Czynniki.add(2L);
            range = (int)(floor(sqrt(x))+1);
        }
        for(int j = 2; j < min(range,1000000); j += 2)
            sito[j] = false;
        
        for(int i = 3; i < range; i+=2){
            if (i >= mln*1000000){
                mln++;
                for(int j = 2; j < min(range-mln*1000000,1000000); j += 2)
                    sito[j] = false;
                for(int j = 0; j != Pierwsze.size(); j++){
                    for(int q = Pierwsze.get(j).intValue()%1000000; q < min(range-mln*1000000,1000000); q += Pierwsze.get(j).intValue())
                        sito[q] = false;
                }
            }
            if(sito[i%1000000]){
                Pierwsze.add((long)(i));
                while(x%i == 0){
                    x /= i;
                    Czynniki.add((long)(i));
                    range = (int)(floor(sqrt(x))+1);
                }
         
                for(int j = i; j < min(range-mln*1000000,1000000); j += i)
                    sito[j] = false;
            }
        }
        Czynniki.add(x);
        long[] ans = new long[Czynniki.size()];
        ans[0] = 1;
        for(int i = 0; i != Czynniki.size(); i++)
            ans[i] = Czynniki.get(i);
        return ans;
    }
    
}
