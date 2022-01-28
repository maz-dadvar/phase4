package Server;

import java.util.LinkedList;

public class ServerManager extends Thread{
    private final LinkedList<ServerWorker> workers;

    public ServerManager(){
        this.workers = new LinkedList<>();
    }

    public void addClient(ServerWorker serverWorker){
        workers.add(serverWorker);
        serverWorker.start();
    }

    @Override
    public void run(){
        while (true){
            //System.out.println("Client Started");
        }
    }
}