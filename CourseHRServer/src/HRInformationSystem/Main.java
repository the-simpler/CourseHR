package HRInformationSystem;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    private static int PORT = 8888;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);

            System.out.println("Server is Started on port "+PORT);

            while (true) {
                Socket socket = server.accept();
                System.out.println( socket.getInetAddress()+" connected");
                ServerThread thread = new ServerThread(socket);
                thread.start();

            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}