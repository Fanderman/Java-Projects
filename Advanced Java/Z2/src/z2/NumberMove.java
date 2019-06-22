/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z2;

import java.util.Random;

/**
 *
 * @author jacek
 */
public class NumberMove {
    
    public static boolean prime(int x){
        for(int i = 2; i <= x-1; i++){
            if(x%i == 0)
                return false;
        }
        return true;
    }
    
    public int move(Map m, int x, int y, int dir){
        if(m.getzx() == x && m.getzy() == y){
            if(dir == 0){
                if(y-1 >= 0){
                    int nm = m.getNumber(x,y-1);
                    if(prime(nm)){
                        m.move(x,y-1,x,y);
                        m.setNumber(x,y-1,m.getNumber(x,y));
                        m.setNumber(x,y,nm);
                    }
                    else{
                        m.terminate(x,y-1);
                        m.setNumber(x,y-1,m.getNumber(x,y));
                        m.setNumber(x,y,-1);
                    }
                    m.setzy(y-1);
                    return 1;
                }
            }
            if(dir == 2){
                if(y+1 < m.height){
                    int nm = m.getNumber(x,y+1);
                    if(prime(nm)){
                        m.move(x,y+1,x,y);
                        m.setNumber(x,y+1,m.getNumber(x,y));
                        m.setNumber(x,y,nm);
                    }
                    else{
                        m.terminate(x,y+1);
                        m.setNumber(x,y+1,m.getNumber(x,y));
                        m.setNumber(x,y,-1);
                    }
                    m.setzy(y+1);
                    return 1;
                }
            }
            if(dir == 1){
                if(x+1 < m.width){
                    int nm = m.getNumber(x+1,y);
                    if(prime(nm)){
                        m.move(x+1,y,x,y);
                        m.setNumber(x+1,y,m.getNumber(x,y));
                        m.setNumber(x,y,nm);
                    }
                    else{
                        m.terminate(x+1,y);
                        m.setNumber(x+1,y,m.getNumber(x,y));
                        m.setNumber(x,y,-1);
                    }
                    m.setzx(x+1);
                    return 1;
                }
            }
            if(dir == 3){
                if(x-1 >= 0){
                    int nm = m.getNumber(x-1,y);
                    if(prime(nm)){
                        m.move(x-1,y,x,y);
                        m.setNumber(x-1,y,m.getNumber(x,y));
                        m.setNumber(x,y,nm);
                    }
                    else{
                        m.terminate(x-1,y);
                        m.setNumber(x-1,y,m.getNumber(x,y));
                        m.setNumber(x,y,-1);
                    }
                    m.setzx(x-1);
                    return 1;
                }
            }
        }
        else{
            if(dir == 0){
                if(y-1 >= 0){
                    if(m.getNumber(x,y-1) < 0){
                        m.setNumber(x,y-1, m.getNumber(x,y));
                        m.setNumber(x,y,-1);
                        return 1;
                    }
                }
            }
            if(dir == 2){
                if(y+1 < m.height){
                    if(m.getNumber(x,y+1) < 0){
                        m.setNumber(x,y+1, m.getNumber(x,y));
                        m.setNumber(x,y,-1);                    
                        return 1;
                    }
                }
            }
            if(dir == 1){
                if(x+1 < m.width){
                    if(m.getNumber(x+1,y) < 0){
                        m.setNumber(x+1,y, m.getNumber(x,y));
                        m.setNumber(x,y,-1);
                        return 1;
                    } 
                }
            }
            if(dir == 3){
                if(x-1 >= 0){
                    if(m.getNumber(x-1,y) < 0){
                        m.setNumber(x-1,y, m.getNumber(x,y));
                        m.setNumber(x,y,-1);
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
}
