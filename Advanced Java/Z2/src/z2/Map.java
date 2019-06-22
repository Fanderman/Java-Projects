/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z2;


import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import java.util.Random;
import javafx.scene.text.*;

/**
 *
 * @author jacek
 */
public class Map {
    private StackPane cells[][];
    private Rectangle rects[][];
    private Text text[][];
    private int number[][];
    public int height;
    public int width;
    private int zx;
    private int zy;
    private int numbers;
    public Number mobs[];
    public NumberMove cm;
    
    public Map(GridPane body, int height, int width, int size, int numbers){
        cm = new NumberMove();
        cells = new StackPane[height][width];
        rects = new Rectangle[height][width];
        text = new Text[height][width];
        number = new int[height][width];
        this.numbers = numbers;
        this.width = width;
        this.height = height;
        Random rnd = new Random();
        for(int i = 0; i != height; i++){
            for(int j = 0; j != width; j++){
                cells[i][j] = new StackPane();
                rects[i][j] = new Rectangle();
                rects[i][j].setFill(Color.WHITE);
                rects[i][j].setStrokeWidth(1);
                rects[i][j].setStroke(Color.BLACK);
                rects[i][j].setWidth(size-1);
                rects[i][j].setHeight(size-1);
                text[i][j] = new Text();
                text[i][j].setText("");
                number[i][j] = -1;
                cells[i][j].getChildren().addAll(rects[i][j],text[i][j]);
                cells[i][j].setLayoutX(1+size*j);
                cells[i][j].setLayoutY(1+size*i);
                body.add(cells[i][j],i,j);
                
            }
        }
        mobs = new Number[numbers];
        if(numbers > 0){
            int x = (rnd.nextInt()%height+height)%height;
            int y = (rnd.nextInt()%width+width)%width;
            mobs[0] = new Number("0",0,x,y,this,cm);
            number[x][y] = 0;
            text[x][y].setText(Integer.toString(number[x][y]));
            zx = x;
            zy = y;
            for(int i = 1; i < numbers; i++){
                while(number[x][y] > -1){
                    x = (rnd.nextInt()%height+height)%height;
                    y = (rnd.nextInt()%width+width)%width;
                }
                number[x][y] = (rnd.nextInt()%20+20)%20+1;
                mobs[i] = new Number(Integer.toString(i),number[x][y],x,y,this,cm);
                text[x][y].setText(Integer.toString(number[x][y])); 
            } 
        }
        for(int i = 0; i != numbers; i++){
            mobs[i].start();
        }
    }
    
    public void setColor(int x, int y, Color color){
        rects[x][y].setFill(color);
    }
    
    public Paint getColor(int x, int y){
        return rects[x][y].getFill();
    }
    
    public void setNumber(int x, int y, int s){
        number[x][y] = s;
        if(number[x][y] < 0)
            text[x][y].setText("");
        else
            text[x][y].setText(Integer.toString(number[x][y]));
    }
    
    public int getNumber(int x, int y){
        return number[x][y];
    }
    
    public int getzx(){
        return zx;
    }
    public int getzy(){
        return zy;
    }
    public void setzx(int x){
        zx = x;
    }
    public void setzy(int y){
        zy = y;
    }
    public void terminate(int x, int y){
        for(int i = 0; i != numbers; i++){
            if(mobs[i].x == x && mobs[i].y == y){
                mobs[i].end = true;
                System.out.println("TERMINATED");
            }
        }
    }
    
    public void move(int x, int y, int x1, int y1){
        for(int i = 0; i != numbers; i++){
            if(mobs[i].x == x && mobs[i].y == y){
                mobs[i].x = x1;
                mobs[i].y = y1;
            }
        }
    }
}
