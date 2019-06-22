package z6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.Random;


public class Server4 implements Runnable {

    protected int             serverPort    = 20194;
    protected ServerSocket    serverSocket  = null;
    protected boolean         isStopped     = false;
    protected ExecutorService threadPool    = Executors.newFixedThreadPool(10);
    protected Socket          clientSocket  = null;
    
    public Server4 (int port) {
        this.serverPort = port;
    }

    public void run () {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }

        while (!this.isStopped && clientSocket == null) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (this.isStopped) {
                    break;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Random rand = new Random();
                int n = rand.nextInt(9000) + 1000;
                System.err.println(n);

                out.println("Guess a number:");

                boolean win = false;
                String input;
                Integer guess;

                int maxRounds = 13;

                for (int i = 1; i <= maxRounds; i++) {
                    if ((input = in.readLine()).equals("quit"))
                        break;
                    else {
                        try {
                            guess = Integer.parseInt(input);
                            if (guess == n) {
                                out.println("You win!");
                                win = true;
                                break;
                            }
                            else if (guess < n)
                                out.println("Too small, " + (maxRounds - i) + " tries remaining");
                            else
                                out.println("Too big, " + (maxRounds - i) + " tries remaining");
                        } 
                        catch (NumberFormatException e) {
                            out.println("Incorrect input, " + (maxRounds - i) + " tries remaining");
                        }
                    }
                }

                if (!win)
                    out.println("You lose!");

                in.close();
                out.close();
            }
            catch (IOException e) {
            e.printStackTrace();
            }
            clientSocket = null;
        }

        this.threadPool.shutdown();
    }

    public synchronized void stop () {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

}
