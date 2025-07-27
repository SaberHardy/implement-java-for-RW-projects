package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started. waiting for client...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected...");

        new Thread(() -> handleClientInput(clientSocket)).start();
        new Thread(() -> handleServerOutput(clientSocket)).start();
    }

    public static void handleClientInput(Socket socket) {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))) {
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("Client: " + clientMessage);
                if ("exit".equalsIgnoreCase(clientMessage)) break;
            }
        } catch (IOException e) {
            System.out.println("Client disconnected...");
        }
    }

    public static void handleServerOutput(Socket socket) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage;

            while ((serverMessage = consoleIn.readLine()) != null) {
                out.println(serverMessage);
                if ("exit".equalsIgnoreCase(serverMessage)) break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
