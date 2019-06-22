package z6;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.Arrays;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import java.net.Socket;
import java.net.ServerSocket;


class Server implements Callable<Void> {

    final static int port = 20192;

    public Void call () {
        Socket clientSocket = null;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.err.println("Started server on port " + port);

            while (!Thread.currentThread().isInterrupted()) {
                clientSocket = serverSocket.accept();
                System.err.println("Client connection estabilished");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String input;
                while (!(input = in.readLine()).equals("."))
                    out.println(input);

                System.err.println("Closing connection with client");
                in.close();
                out.close();
                clientSocket.close();
            }

            serverSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try { if (clientSocket != null) clientSocket.close(); }
            catch (IOException ex) { }
        }

        return null;
    }

}

public class z2 {

    public static void main (String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            executor.invokeAll(Arrays.asList(new Server()), 120, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        executor.shutdown();
    }

}
