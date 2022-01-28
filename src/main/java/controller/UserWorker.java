package controller;

import interfaces.Dimensions;
import view.SoundPlayer;
import java.io.*;
import java.net.Socket;

public class UserWorker extends Thread implements Dimensions{
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private Object waiter;
    private SoundPlayer player;
    private Socket socket;

    public UserWorker(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        this.player = new SoundPlayer();
    }


    @Override
    public void run(){
        while (true){

        }
    }


}

