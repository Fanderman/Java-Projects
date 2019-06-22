package z6;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.Socket;


public class ClientGUI extends Application {

    private static Socket socket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;

    @Override
    public void start (Stage stage) {
        try {
            socket = new Socket("localhost", 20194);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.err.println("Connection refused");
            System.exit(0);
        }

        VBox vb = new VBox();
        
        Scene scene = new Scene(vb);
        stage.setTitle("Client GUI");

        stage.setWidth(300);

        vb.prefWidthProperty().bind(stage.widthProperty());

        final TextField inputField = new TextField();
        inputField.setPromptText("Write here...");

        final Label outputField = new Label("");
        try {
            outputField.setText(in.readLine());
        } catch (IOException e) { }

        final Button submit = new Button("Submit");
        VBox.setVgrow(submit, Priority.ALWAYS);
        submit.setMaxWidth(Double.MAX_VALUE);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                out.println(inputField.getText());
                inputField.clear();
                try {
                    outputField.setText(in.readLine());
                } catch (IOException ex) { }
            }
        });

        vb.getChildren().addAll(inputField, outputField, submit);
        
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    @Override
    public void stop () {
        if (out != null)
            out.println("quit");
    }
    
    public static void main (String[] args) {
        launch(args);
    }
    
}
