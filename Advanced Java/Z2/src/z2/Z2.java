/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z2;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jacek
 */
public class Z2 extends Application {
    
    
    int width = 10;
    int height = 10;
    int size = 30;
    int numbers = 30;
    int FPS = 25;
    Map map;
    
    
    private void repaint(){
        //System.out.println(map.mobs[0].x + " " + map.mobs[0].y);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane body = new GridPane();
        System.out.println("a");
        map = new Map(body,height,width,size,numbers);
        
        System.out.println("a");
        StackPane root = new StackPane();
        root.getChildren().add(body);
        
        Scene scene = new Scene(root, size*width, size*height);
        System.out.println("a");
        primaryStage.setTitle("Numbers");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        int delay = 1000/FPS;
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(delay), 
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ae) {
                    repaint();
                }
            }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
