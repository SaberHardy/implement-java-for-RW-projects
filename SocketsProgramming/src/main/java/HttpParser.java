package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpParser {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        )) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
