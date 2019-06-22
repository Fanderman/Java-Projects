package z7;


import java.io.IOException;
import java.io.FileOutputStream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.FileSystems;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.InetAddress;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class client extends Application {
    protected final String DEST_ADDR = "224.0.1.0";
    protected final int DEST_PORT = 1900;
    protected final int BUFSIZE = 2048;

    private int DGRAM_LENGTH;

    private MulticastSocket socket;
    private byte[] buf = new byte[BUFSIZE];

    private String[] receiveMsg (DatagramPacket dgram) throws Exception {
        socket.receive(dgram);
        String[] res = (new String(Arrays.copyOfRange(dgram.getData(), 0, dgram.getLength()), StandardCharsets.ISO_8859_1)).split(":", 2);
        dgram.setLength(BUFSIZE);
        return res;
    }

    @Override
    public void start (Stage stage) {
        try {
            socket = new MulticastSocket(DEST_PORT);
            socket.joinGroup(InetAddress.getByName(DEST_ADDR));
            DatagramPacket dgram = new DatagramPacket(buf, BUFSIZE);

            boolean fileReceived = false;

            String filename = "";
            String[] msg;
            byte[][] fileBuf = null;
            int packetsReceived = 0;

            while (!fileReceived) {
                if ( (msg = receiveMsg(dgram))[0].equals("START") ) {
                    String[] info = msg[1].split(":", 3);
                    int noOfPackets = Integer.parseInt(info[0]);
                    DGRAM_LENGTH = Integer.parseInt(info[1]);
                    filename = info[2].trim();

                    fileBuf = new byte[noOfPackets][DGRAM_LENGTH];

                    while ( !(msg = receiveMsg(dgram))[0].equals("END") ) {
                        packetsReceived++;
                        fileBuf[Integer.parseInt(msg[0])] = Arrays.copyOf(msg[1].getBytes("ISO-8859-1"), DGRAM_LENGTH);
                    }

                    fileReceived = true;
                }
            }

            List<Byte> fullFile = new ArrayList<Byte>();

            for (byte[] ba : fileBuf)
                for (byte b : ba)
                    fullFile.add(b);

            try (FileOutputStream fos = new FileOutputStream("./out.mp3")) {
                fos.write(ArrayUtils.toPrimitive(fullFile.toArray(new Byte[fullFile.size()])));
            }

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You received a music file named " + filename);
            alert.setContentText("Do you want to play it now?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Scene scene = new Scene(new Group());
                stage.setTitle("Media player");

                MediaPlayer musicplayer;
                Media mp3MusicFile = new Media(    ("file:/" + FileSystems.getDefault().getPath("out.mp3").toAbsolutePath()).replace(" ", "%20").replace("\\","/") );
                musicplayer = new MediaPlayer(mp3MusicFile);
                musicplayer.setAutoPlay(true);
                musicplayer.setVolume(0.5);
                musicplayer.play();

                MediaView m = new MediaView(musicplayer);

                VBox vb = new VBox();
                vb.getChildren().addAll(m);
                ((Group) scene.getRoot()).getChildren().addAll(vb);

                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } 
            else {
                System.err.println("Goodbye.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void main (String[] args) {
        launch(args);
    }
}
