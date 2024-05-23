package gui;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockTime extends Thread{

    private JLabel viewer;
    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    public ClockTime(JLabel viewer){
        this.viewer = viewer;
    }

    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(TIME_FORMATTER);
            viewer.setText(time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        viewer.setText(" ");
    }

}
