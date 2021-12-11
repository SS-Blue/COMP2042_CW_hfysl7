package test.Controller;

import test.Components.Wall;
import test.View.GameBoardView;
import test.View.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoardController extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private Timer gameTimer;
    private GameFrame owner;
    private GameBoardView view;

    private Wall wall;

    public GameBoardController(GameFrame owner, GameBoardView view) {
        this.view = view;
        this.owner = owner;
        wall = new Wall(new Rectangle(0, 0, view.DEF_WIDTH, view.DEF_HEIGHT), 30, 3, 6 / 2, new Point(300, 430));
        this.initialize();
        this.timer();
    }

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
                    view.wall.wallReset();
                    view.timer.resetTimer();
                    view.wall.resetScore();
                    view.message = "Game over";
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
                        view.timer.countDownStart();
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

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

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
    public void onLostFocus() {
        view.timer.stopTimer();
        gameTimer.stop();
        view.message = "Focus Lost";
        view.repaint();
    }

    private void initialize() {
        view.setPreferredSize(new Dimension(view.DEF_WIDTH, view.DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
    }
}

