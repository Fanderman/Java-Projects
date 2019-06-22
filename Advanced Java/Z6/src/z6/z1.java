package z6;


import java.io.PrintWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.net.Socket;
import java.net.ServerSocket;


public class z1 {

    final static int port = 20191;

    public static void main (String[] args) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
            System.err.println("Started server on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connection estabilished");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime localDate = LocalDateTime.now();

                out.println(dtf.format(localDate));

                System.err.println("Closing connection with client");
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
