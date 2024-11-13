package task24;

public class Main {
    public static void main(String[] args) {
//        Client client = new Client("localhost", 12345);
//
//        client.sendMessage("Hello, Server!\n");
//        client.sendMessage("This is the second line.\n");
//        client.sendMessage("Goodbye!\n");
//
//        System.out.println("Response: " + client.getMessage());
//        client.close();
        //2.4.2
        int port = 12345;
        Server server = new Server(port);
        server.start();
    }
}
