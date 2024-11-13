package task24;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;
    private BufferedWriter writer;

    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // Notify client that connection is successful
            sendMessage("Welcome! You are connected to the server.");

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received from client (" + clientSocket.getInetAddress() + "): " + message);

                // Broadcast the message to other clients
                server.broadcastMessage("Client (" + clientSocket.getInetAddress() + "): " + message, this);

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            server.removeClient(this);
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error sending message to client: " + e.getMessage());
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
