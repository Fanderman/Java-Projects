/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z5;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jacek
 */
public class Z5 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);

        try{
            URL url = new URL("https://www.wolframalpha.com/");
            URLConnection uc = url.openConnection();
            InputStream is = uc.getInputStream();
            int ptr = 0;
            StringBuffer buffer = new StringBuffer();
            while ((ptr = is.read()) != -1) {
                buffer.append((char)ptr);
            }
            System.out.println(buffer);
            
            ArrayList <Integer> index = new ArrayList <Integer>();
            String help = buffer.toString();
            
            int ans = (help.indexOf("<a href="));
            int sum = ans;
            
            while(ans > -1){
                index.add(sum);
                System.out.println(sum);
                help = help.substring(ans+1);
                ans = (help.indexOf("<a href="));
                sum += ans+1;
            }
            help = buffer.toString();
            System.out.println(help.charAt(index.get(0)));
        }
        catch(IOException e){
            System.out.println(e.getCause());
        }
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
