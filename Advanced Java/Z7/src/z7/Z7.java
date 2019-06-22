package z7;


import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


class ServerImpl implements Runnable {

    protected final String DEST_ADDR = "224.0.1.0";
    protected final int DEST_PORT = 1900;
    protected final int DGRAM_LENGTH = 1000;
    protected final String ENCODING = "ISO-8859-1";

    private boolean isStopped = false;
    private DatagramSocket socket;

    @Override
    public void run () {
        try {
            this.socket = new DatagramSocket();

            String filename = "sound.mp3";
            byte[] fileBytes = Files.readAllBytes(Paths.get("./" + filename));
            int noOfPackets = (int) Math.ceil((double) fileBytes.length / DGRAM_LENGTH);

            System.err.println("Number of packets to send: " + noOfPackets);

            int batchNo = 1;

            while (!isStopped) {
                System.err.println("Sending batch no. " + batchNo);

                String msg = "START:" + noOfPackets + ":" + DGRAM_LENGTH + ":" + filename;
                byte[] buf = msg.getBytes(ENCODING);
                DatagramPacket dgram = new DatagramPacket(buf, buf.length, InetAddress.getByName(DEST_ADDR), DEST_PORT);
                socket.send(dgram);

                String slice;
                for (int i = 0; i < noOfPackets; i++) {
                    slice = new String(Arrays.copyOfRange(fileBytes, DGRAM_LENGTH*i, DGRAM_LENGTH*i+DGRAM_LENGTH), ENCODING);
                    dgram.setData( (String.valueOf(i) + ":" + slice).getBytes(ENCODING) );
                    socket.send(dgram);
                }

                dgram.setData((new String("END:0")).getBytes(ENCODING));
                socket.send(dgram);

                System.err.println("Batch no. " + batchNo + " sent successfully");

                batchNo++;

                for (int i = 0; i < 30; i++)
                    if (this.isStopped)
                        break;
                    else
                        Thread.sleep(1000);
            }

            socket.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized void stop () {
        this.isStopped = true;
    }

}



public class Z7 extends Application {
    
    private static ServerImpl  server              = null;
    private static boolean     serverStarted       = false;
    private static boolean     suppressExitMessage = false;

    private static synchronized void startServer () {
        if (!serverStarted) {
            serverStarted = true;
            server = new ServerImpl();
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
        System.setProperty("java.net.preferIPv4Stack" , "true");
        Scene scene = new Scene(new Group());
        stage.setTitle("Server GUI");

        VBox vb = new VBox();

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

        vb.getChildren().addAll(startServer, stopServer);
        ((Group) scene.getRoot()).getChildren().addAll(vb);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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
