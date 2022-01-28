package Server;

import interfaces.Dimensions;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Dimensions {

    public static void main(String[] args) throws IOException {

        ServerManager serverManager = new ServerManager();
        serverManager.start();
        ServerSocket listener = new ServerSocket(port);
        Socket socket;
        try {
            while (true){
                System.out.println("Waiting for clients ...");
                socket = listener.accept();
                System.out.println("Client connected");
                serverManager.addClient(new ServerWorker(socket));
            }
        }
        finally {
            listener.close();
        }
    }

}
