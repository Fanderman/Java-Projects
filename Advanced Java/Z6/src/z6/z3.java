package z6;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.net.Socket;
import java.net.ServerSocket;


class InputReader implements Runnable {

    final ServerSocket serverSocket;

    public InputReader (ServerSocket s) {
        serverSocket = s;
    }

    public void run () {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine()).equals("quit")) { }

        scanner.close();

        try {
            serverSocket.close();
        } catch (IOException e) {
            // System.err.println(e);
        } finally {
            System.exit(0);
        }
    }
}


public class z3 {

    final static int port = 20193;

    public static int GCD (int a, int b) {
        int temp;

        if (a < b) {
            temp = a;
            a = b;
            b = temp;
        }

        if (a % b == 0)
            return b;

        return GCD(a % b, b);
    }

    public static void main (String[] args) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
            System.err.println("Started server on port " + port);

            Thread reader = new Thread(new InputReader(serverSocket));
            reader.start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.err.println("Client connection estabilished");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String input;
                while (!(input = in.readLine()).equals(".")) {
                    try {
                        Integer n = Integer.parseInt(input);
                        if (n <= 0) {
                            out.println("Wrong number you goofus");
                        }
                        else {
                            List<Integer> coprimes = new ArrayList<Integer>();
                            for (int i = 1; i <= n; i++)
                                if (GCD(n, i) == 1)
                                    out.print(i + " ");
                            out.println();
                        }
                    }
                    catch (NumberFormatException e) {
                        out.println("Not a number, try again");
                    }
                }

                System.err.println("Closing connection with client");
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
