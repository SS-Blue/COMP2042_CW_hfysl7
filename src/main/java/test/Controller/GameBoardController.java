package test.Controller;

import test.Components.Wall;
import test.View.GameBoardView;
import test.View.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is to show the controller features in game board
 * @author Sue Sim
 */
public class GameBoardController extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private Timer gameTimer;
    private GameFrame owner;
    private GameBoardView view;

    private Wall wall;

    /**
     * @param owner To call game frame
     * @param view To call game board
     */
    public GameBoardController(GameFrame owner, GameBoardView view) {
        this.view = view;
        this.owner = owner;
        wall = new Wall(new Rectangle(0, 0, view.DEF_WIDTH, view.DEF_HEIGHT), 30, 3, 6 / 2, new Point(300, 430));
        this.initialize();
        this.timer();
    }

    /**
     * Display brick count, time taken, lives remaining and individual score
     */
    private void timer(){
        gameTimer = new Timer(10, e -> {
            view.wall.move();
            view.wall.findImpacts();
            view.message = String.format("Bricks: %d",view.wall.getBrickCount());
            view.message2 = String.format("Timer: %d",view.timer.getSeconds());
            view.message3 = String.format("Remaining Life: %s",view.remainingLife());
            view.individualScore = String.format("Score: %d",view.wall.getIndividualScore());
            if (view.wall.isBallLost()) {
                if (view.wall.ballEnd()) {
                    view.printHighScore();
                    view.wall.wallReset();
                    view.timer.resetTimer();
                    view.wall.resetScore();
                    view.message = "Game Over";
                }
                view.wall.ballReset();
                gameTimer.stop();
            } else if (view.wall.isDone()) {
                if (view.wall.hasLevel()) {
                    view.message = "Go to Next Level";
                    gameTimer.stop();
                    view.timer.resetTimer();
                    view.wall.getTotalScore();
                    view.wall.resetScore();
                    view.wall.ballReset();
                    view.wall.wallReset();
                    view.wall.nextLevel();
                } else {
                    view.message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    view.printHighScore();
                }
            }
            view.repaint();
        });
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * @param keyEvent Key to enable player movement
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                view.wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                view.wall.player.moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                view.showPauseMenu = !view.showPauseMenu;
                view.repaint();
                gameTimer.stop();
                view.timer.stopTimer();
                break;
            case KeyEvent.VK_SPACE:
                if (!view.showPauseMenu)
                    if (gameTimer.isRunning()) {
                        gameTimer.stop();
                        view.timer.stopTimer();
                    }
                    else {
                        gameTimer.start();
                        view.timer.startTimer();
                    }
                break;
            case KeyEvent.VK_F1:
                if (keyEvent.isAltDown() && keyEvent.isShiftDown())
                    view.debugConsole.setVisible(true);
            default:
                view.wall.player.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        view.wall.player.stop();
    }

    /**
     * @param mouseEvent Display after clicking on a button
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (!view.showPauseMenu)
            return;
        if (view.continueButtonRect.contains(p)) {
            view.showPauseMenu = false;
            view.repaint();
        } else if (view.restartButtonRect.contains(p)) {
            view.message = "Restarting Game...";
            view.timer.resetTimer();
            view.wall.ballReset();
            view.wall.wallReset();
            view.showPauseMenu = false;
            view.repaint();
        } else if (view.exitButtonRect.contains(p)) {
            System.exit(0);
        }

    }

    /**
     * @param mouseEvent When mouse is pressed
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent When mouse is released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent When mouse enters window
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent When mouse exits from window
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent When mouse drags on window
     */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * @param mouseEvent When mouse moves in window
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (view.exitButtonRect != null && view.showPauseMenu) {
            if (view.exitButtonRect.contains(p) || view.continueButtonRect.contains(p) || view.restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * When focus is lost
     */
    public void onLostFocus() {
        view.timer.stopTimer();
        gameTimer.stop();
        view.message = "Start Game";
        view.repaint();
    }

    /**
     * Initialization
     */
    private void initialize() {
        view.setPreferredSize(new Dimension(view.DEF_WIDTH, view.DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
    }
}

