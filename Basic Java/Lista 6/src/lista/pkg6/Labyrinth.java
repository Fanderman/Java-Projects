/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.pkg6;
import java.util.*;

/**
 *
 * @author jacek
 */


public class Labyrinth {
    
    int size;
    int [][] tab;
    int px;
    int py;
    int finalx;
    int finaly;
    private ArrayList update;
    
    public void move(char c){
        switch (c) {
            case 'w':
                if(tab[px][py-1] == 1){
                    update.add(new Pair(px,py));
                    py--; 
                }
                break;

            case 'a':
                if(tab[px-1][py] == 1){
                    update.add(new Pair(px,py));
                    px--;   
                }
                break;

            case 's':
                if(tab[px][py+1] == 1){
                    update.add(new Pair(px,py));
                    py++;
                }
                break;
            
            case 'd':
                if(tab[px+1][py] == 1){
                    update.add(new Pair(px,py));
                    px++;
                }
                break;
        }

        if ((px == finalx) && (py == finaly))
            System.exit(0);
    }
    
    private void build(){
        //reseting
        for(int i = 0; i != size; i++){
            for(int j = 0; j != size; j++)
                tab[i][j] = 0;
        }
        //building boundary
        for(int i = 0; i != size; i++){
            tab[0][i] = 2;
            tab[size-1][i] = 2;
            tab[i][0] = 2;
            tab[i][size-1] = 2;
        }
        tab[1][1] = 1;
        this.px = 1;
        this.py = 1;
        ArrayList nodes = new ArrayList();
        nodes.add(new Pair(1,1));
        while(nodes.size() > 0){
            int added = 0;
            for(int i = 0; i < nodes.size() - added; i++){
                
                Pair node = (Pair)nodes.get(i);
                int x = (int)node.getFirst();
                int y = (int)node.getSecond();
                nodes.remove(i);
                i--;
                
                double chance = 0.77;
                
                String[] way = new String[] {"N","E","S","W"};
                List<String> strList = Arrays.asList(way);
                Collections.shuffle(strList);
                way = strList.toArray(new String[strList.size()]);
                
                for(int j = 0; j != 4; j++){
                    switch (way[j]) {
                        case "S":
                            if (y + 2 < size-1 && Math.random() < chance) 
                                if (tab[x][y+2] != 1) {
                                    tab[x][y+2] = 1;
                                    tab[x][y+1] = 1;
                                    nodes.add(new Pair(x, y+2));
                                    added++;
                                    chance = chance/2;
                                }
                            break;

                        case "W":
                            if (x - 2 >= 1 && Math.random() < chance) 
                                if (tab[x-2][y] != 1) {
                                    tab[x-2][y] = 1;
                                    tab[x-1][y] = 1;
                                    nodes.add(new Pair(x-2, y));
                                    added++;
                                    chance = chance/2;
                                }
                            break;

                        case "N":
                            if (y - 2 >= 1 && Math.random() < chance) 
                                if (tab[x][y-2] != 1) {
                                    tab[x][y-2] = 1;
                                    tab[x][y-1] = 1;
                                    nodes.add(new Pair(x, y-2));
                                    added++;
                                    chance = chance/2;
                                }
                            break;

                        case "E":
                            if (x + 2 < size-1 && Math.random() < chance) 
                                if (tab[x+2][y] != 1) {
                                    tab[x+2][y] = 1;
                                    tab[x+1][y] = 1;
                                    nodes.add(new Pair(x+2, y));
                                    added++;
                                    chance = chance/2;
                                }
                            break;
                    }
                }
            }
        }
        for(int i = 0; i != size; i++){
            for(int j = 0; j != size; j++)
                if(tab[i][j] == 0) tab[i][j] = 2;
        }
        tab[0][1] = 1;
        int p = 1;
        for(int i = size-1; i != -1; i--){
            if(tab[size-2][i] == 1){
                tab[size-1][i] = 1;
                finalx = size-1;
                finaly = i;
                p = 0;
                break;
            }
        }
        for(int i = 0; i != size; i++){
            for(int j = 0; j != size; j++)
                update.add(new Pair(i,j));
        }
        if(p == 1)
            build();
    }
    
    public Labyrinth(int n){
        this.size = n;
        this.tab = new int[n][n];
        this.update = new ArrayList();
        build();
    }
    
    public void clearUpdate(){
        update.clear();
    }
    
    public ArrayList update(){
        return update;
    }
}
