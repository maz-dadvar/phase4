package view;

import interfaces.Dimensions;
import interfaces.Listener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SelectTemplate extends JPanel implements Dimensions {

    protected LinkedList<Listener> listeners;

    public SelectTemplate() {
        this.setLayout(null);
        this.setBackground(backgroundColor);
        this.setSize(new Dimension(frameWidth, selectPanelHeight));
        listeners = new LinkedList<>();
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }
}
