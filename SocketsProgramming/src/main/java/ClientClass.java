package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.net.*;
import java.io.*;

public class ClientClass {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8000);
        System.out.println("Connected to server!");

        // Separate threads for reading/writing
        new Thread(() -> handleServerInput(socket)).start();
        new Thread(() -> handleClientOutput(socket)).start();
    }

    private static void handleServerInput(Socket socket) {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))) {

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Server: " + serverMessage);
                if ("exit".equalsIgnoreCase(serverMessage)) break;
            }
        } catch (IOException e) {
            System.out.println("Server disconnected");
        }
    }

    private static void handleClientOutput(Socket socket) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleIn = new BufferedReader(
                     new InputStreamReader(System.in))) {

            String clientMessage;
            while ((clientMessage = consoleIn.readLine()) != null) {
                out.println(clientMessage);
                if ("exit".equalsIgnoreCase(clientMessage)) break;
            }
        } catch (IOException e) {
            System.out.println("Connection error");
        }
    }
}