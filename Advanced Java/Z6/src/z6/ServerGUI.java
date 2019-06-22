package z6;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.*;


public class ServerGUI extends Application {

    private static Server4  server              = null;
    private static boolean serverStarted       = false;
    private static boolean suppressExitMessage = false;

    private static synchronized void startServer () {
        if (!serverStarted) {
            serverStarted = true;
            server = new Server4(20194);
            new Thread(server).start();
            System.err.println("Server started");
        }
        else
            System.err.print("Server already running");
    }

    private static synchronized void stopServer () {
        if (serverStarted) {
            serverStarted = false;
            server.stop();
            System.err.println("Server stopped");
        }
        else if (!suppressExitMessage)
            System.err.println("Server not running");
    }

    @Override
    public void start (Stage stage) {
        stage.setTitle("Server GUI");
        
        int ph = 200;
        int pw = 200;
        
        VBox vb = new VBox();
        Scene scene = new Scene(vb, ph, pw);
        
        final Button startServer = new Button("Start server");
        startServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                startServer();
            }
        });
        
        final Button stopServer = new Button("Stop server");
        stopServer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                stopServer();
            }
        });

        startServer.setPrefHeight(ph/2);
        stopServer.setPrefHeight(ph/2);
        startServer.setPrefWidth(pw);
        stopServer.setPrefWidth(pw);
                
        vb.getChildren().addAll(startServer, stopServer);
        
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
        
        vb.heightProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                startServer.setPrefHeight((double)(newValue)/2);
                stopServer.setPrefHeight((double)(newValue)/2);
            }
        });
        vb.widthProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                startServer.setPrefWidth((double)(newValue));
                stopServer.setPrefWidth((double)(newValue));
            }
        });
    }

    @Override
    public void stop () {
        suppressExitMessage = true;
        stopServer();
    }
    
    public static void main (String[] args) {
        launch(args);
    }
    
}


