package test.Components;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is for the timer of the game
 * @author Sue Sim
 */
public class GameTimer implements ActionListener{
    public GameTimer(){}

    Timer timer = new Timer(1000, this);
    int seconds = 0;


    /**
     * Start timer
     */
    public void startTimer() {
        timer.start();
    }

    /**
     * Reset timer
     */
    public void resetTimer() {
        seconds = 0;
    }

    /**
     * Stop timer
     */
    public void stopTimer() {
        timer.stop();
    }

    /**
     * @return get the time taken in game
     */
    public int getSeconds() {return seconds;}

    public void actionPerformed(ActionEvent e) {

        if (seconds == 100000)
            timer.stop();
        else{
            seconds++;
        }
    }
}
