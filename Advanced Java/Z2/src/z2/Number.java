/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z2;

import java.util.Random;
import static z2.NumberMove.prime;

/**
 *
 * @author jacek
 */
public class Number extends Thread{
    public int x;
    public int y;
    public int id;
    public String name;
    private Thread t;
    private Map m;
    private NumberMove cm;
    public boolean end;
    
    public Number(String name,int id, int x, int y, Map m, NumberMove cm){
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
        this.m = m;
        this.cm = cm;
    }
    
    @Override
    public void run(){
        end = false;
        while(end == false){
            Random rd = new Random();
            try{
                int r = rd.nextInt();
                Thread.sleep(1500 + (r%950+950)%950);
            }
            catch(InterruptedException e){
                System.out.println("Interrupted: " + e);
            }
                int direction = -1;
                double xdistance = x - m.getzx();
                double ydistance = y - m.getzy();

                double Uchance = 100;
                double Dchance = 100;
                double Lchance = 100;
                double Rchance = 100;

                if(xdistance > 0)
                    Lchance = (double)(m.width-xdistance+1)*(5/(double)(m.width))*Lchance;
                if(xdistance < 0)
                    Rchance = (double)(m.width+xdistance+1)*(5/(double)(m.width))*Rchance;
                if(ydistance > 0)
                    Uchance = (double)(m.height-ydistance+1)*(5/(double)(m.height))*Uchance;
                if(ydistance < 0)
                    Dchance = (double)(m.height+ydistance+1)*(5/(double)(m.height))*Dchance;

                int tries = 2;
                while(tries > 0){
                    int ldirection = direction;
                    int S = (int)(Uchance + Dchance + Lchance + Rchance);
                    while(direction == ldirection){
                        System.out.println(id + " " + x + " " + y + " " + Uchance + " " + Rchance + " " + Dchance + " " + Lchance);
                        int w = (rd.nextInt()%S+S)%S;
                        if(w < Uchance)
                            direction = 0;
                        if(w >= Uchance && w < Uchance + Rchance)
                            direction = 1;
                        if(w >= Uchance + Rchance && w < Uchance + Rchance + Dchance)
                            direction = 2;
                        if(w >= Uchance + Rchance + Dchance)
                            direction = 3;
                    }
                    int result = 0;
                        synchronized(cm){
                            if(end == false)
                                result = cm.move(m,x,y,direction);
                        }
                    if(result == 1){
                        if(direction == 0)
                            y = y-1;
                        if(direction == 2)
                            y = y+1;
                        if(direction == 1)
                            x = x+1;
                        if(direction == 3)
                            x = x-1;
                        tries = 0;
                    }
                    else
                        tries--;
                }
            }
        }
    @Override
    public void start() {
      if (t == null) {
         t = new Thread (this, name);
         t.start();
      }
   }
}
