package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientClass {
    public static void main(String[] args) throws IOException {
        // 1. Connect to the server
        Socket socket = new Socket("localhost", 8000);

        // 2. Get Input/output
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        // 3. Send Message
        out.println("Hello from client...!");

        // 4. Read server response
        String serverResponse = in.readLine();
        System.out.println("Server: " + serverResponse);

        socket.close();
    }
}