/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z1;

import java.math.BigInteger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author jacek
 */
public class Z1 extends Application {
    
    
    int Swidth = 600;
    int Sheight = 606;
    int fheight = 50;
        
    Group root = new Group();
        
    Rectangle nfield = new Rectangle();
    HBox pane = new HBox();
    GridPane buttonsL = new GridPane();
    Button buttons[];
     
    Scene scene;
    
    BigInteger nm[];
    int curr = 0;
    String operation;
    
    public void redraw(){
        nfield.setWidth(scene.getWidth()-6);
        pane.setMinSize(scene.getWidth()-16,fheight);
        pane.setMaxSize(scene.getWidth()-16,fheight);
        for(int i = 0; i != 8; i++){
            buttons[i].setMinWidth((scene.getWidth()-2)/4);
            buttons[i].setMaxWidth((scene.getWidth()-2)/4);
            buttons[i].setMinHeight((scene.getHeight()-fheight-8)/2);
            buttons[i].setMaxHeight((scene.getHeight()-fheight-8)/2);
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        root.getChildren().add(nfield);
        root.getChildren().add(pane);
        root.getChildren().add(buttonsL);
        scene = new Scene(root, Swidth, Sheight);
        scene.widthProperty().addListener(observable -> redraw());
        scene.heightProperty().addListener(observable -> redraw());
        
        nfield.setX(3);
        nfield.setY(3);
        nfield.setWidth(scene.getWidth()-6);
        nfield.setHeight(fheight);
        nfield.setFill(Color.WHITE);
        nfield.setStrokeWidth(3);
        nfield.setStroke(Color.BLACK);
        
        pane.setLayoutX(8);
        pane.setLayoutY(3);
        pane.setMinSize(scene.getWidth()-16,fheight);
        pane.setMaxSize(scene.getWidth()-16,fheight);
        
        Text numbers = new Text();
        numbers.setFont(new Font(56));
        numbers.setBoundsType(TextBoundsType.VISUAL);
        numbers.setTextAlignment(TextAlignment.RIGHT);
        nm = new BigInteger[2];
        nm[0] = new BigInteger("0",2);
        nm[1] = new BigInteger("0",2);
        numbers.setText(nm[0].toString(2));
        pane.setAlignment(Pos.CENTER_RIGHT);
        pane.getChildren().add(numbers);
        
        
        buttonsL.setLayoutX(2);
        buttonsL.setLayoutY(fheight+6);
        
        buttons = new Button[8];
        for(int i = 0; i != 8; i++){
            buttons[i] = new Button();
            buttons[i].setMinWidth((scene.getWidth()-2)/4);
            buttons[i].setMaxWidth((scene.getWidth()-2)/4);
            buttons[i].setMinHeight((scene.getHeight()-fheight-8)/2);
            buttons[i].setMaxHeight((scene.getHeight()-fheight-8)/2);
            buttonsL.add(buttons[i],i%4,i/4);
        }
        buttons[0].setText("0");
        buttons[4].setText("1");
        buttons[1].setText("+");
        buttons[5].setText("-");
        buttons[2].setText("*");
        buttons[6].setText("\\");
        buttons[3].setText("C");
        buttons[7].setText("=");
        
        buttons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nm[curr] = nm[curr].multiply(BigInteger.valueOf(2));
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        buttons[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nm[curr] = nm[curr].multiply(BigInteger.valueOf(2));
                nm[curr] = nm[curr].add(BigInteger.valueOf(1));
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        buttons[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curr = 1;
                operation = "+";
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        buttons[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curr = 1;
                operation = "-";
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        buttons[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curr = 1;
                operation = "*";
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        buttons[6].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curr = 1;
                operation = "\\";
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        buttons[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curr = 0;
                nm[0] = BigInteger.valueOf(0);
                nm[1] = BigInteger.valueOf(0);
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        buttons[7].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                curr = 0;
                if(operation == "+")
                    nm[0] = nm[0].add(nm[1]);
                if(operation == "-")
                    nm[0] = nm[0].add(nm[1].negate());
                if(operation == "*")
                    nm[0] = nm[0].multiply(nm[1]);
                if(operation == "\\")
                    nm[0] = nm[0].divide(nm[1]);
                nm[1] = BigInteger.valueOf(0);
                numbers.setText(nm[curr].toString(2));
            }
        });
        
        primaryStage.setTitle("Binary Calculator");
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
