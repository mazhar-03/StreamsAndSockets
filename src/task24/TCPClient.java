package task24;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        String host = "localhost"; // Server address
        int port = 12345; // Server port

        try (
                Socket socket = new Socket(host, port);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                );
                Scanner scanner = new Scanner(System.in)
        ) {

            System.out.println("Connected to the server.");
            System.out.println("Server says: " + reader.readLine());

            Thread readThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message); // Display messages from the server
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from server: " + e.getMessage());
                }
            });
            readThread.start();

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                writer.write(message + "\n");
                writer.flush();

                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("Closing connection...");
                    break;
                }
            }
            readThread.join();
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
