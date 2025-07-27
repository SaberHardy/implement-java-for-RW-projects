package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
    public static void main(String[] args) throws IOException {
        // 1. Create server socket with prot 8000
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server Started....");

        // 2. Accept client connection
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connection Accepted...");

        // 3. Get inputs/outputs streams
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // 4. Read client message
        String clientMessage = in.readLine();
        System.out.println("Client: " + clientMessage);

        // 5. Send Message
        out.println("Hello From server...!");

        // 6. Close connection
        clientSocket.close();
        serverSocket.close();
    }
}
