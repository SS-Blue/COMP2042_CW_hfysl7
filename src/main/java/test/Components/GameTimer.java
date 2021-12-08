package test.Components;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Countdown timer for the game
 */
public class GameTimer implements ActionListener{
    public GameTimer(){}

    Timer timer = new Timer(1000, this);
    int seconds = 0;


    public void countDownStart() {
        timer.start();
    }

    public void resetTimer() {
        seconds = 0;
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getSeconds() {return seconds;}

    public void actionPerformed(ActionEvent e) {

        if (seconds == 180)
            timer.stop();
        else{
            seconds++;
        }
    }
}
