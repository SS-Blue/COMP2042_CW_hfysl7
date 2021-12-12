package test.Components;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This class is to show the characteristics of balls in general
 * @author Sue Sim
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * @param center Center of ball
     * @param radiusA Radius of ball
     * @param radiusB Radius of ball
     * @param inner Inner color of ball
     * @param border Border color of ball
     */
    public Ball(Point2D center, int radiusA, int radiusB, Color inner, Color border) {
        this.setCenter(center);

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(), center.getY() - (radiusB / 2));
        down.setLocation(center.getX(), center.getY() + (radiusB / 2));

        left.setLocation(center.getX() - (radiusA / 2), center.getY());
        right.setLocation(center.getX() + (radiusA / 2), center.getY());


        ballFace = makeBall(center, radiusA, radiusB);
        zeroSpeed();
        setBallColor(inner, border);

    }

    /**
     * Set speed to 0
     */
    private void zeroSpeed() {
        speedX = 0;
        speedY = 0;
    }

    /**
     * @param inner Inner color of ball
     * @param border Border color of ball
     */
    private void setBallColor(Color inner, Color border) {
        this.border = border;
        this.inner = inner;
    }


    /**
     * @param center Center point of ball
     * @param radiusA Radius of ball
     * @param radiusB Radius of ball
     * @return Appearance of ball
     */
    protected abstract Shape makeBall(Point2D center, int radiusA, int radiusB);

    /**
     * Move the ball by updating on the center point and frame
     */
    public void move() {
        RectangularShape tmp = (RectangularShape) ballFace;
        getCenter().setLocation((getCenter().getX() + speedX), (getCenter().getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((getCenter().getX() - (w / 2)), (getCenter().getY() - (h / 2)), w, h);
        setPoints(w, h);


        ballFace = tmp;
    }

    /**
     * @param x Set speed on x-axis
     * @param y Set speed on y-axis
     */
    public void setSpeed(int x, int y) {
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s) {
        speedX = s;
    }

    public void setYSpeed(int s) {
        speedY = s;
    }

    /**
     * Reverse speed on x-axis
     */
    public void reverseX() {
        speedX *= -1;
    }

    /**
     * Reverse speed on y-axis
     */
    public void reverseY() {
        speedY *= -1;
    }

    /**
     * @return Get border color of ball
     */
    public Color getBorderColor() {
        return border;
    }

    /**
     * @return Get color of the ball itself
     */
    public Color getInnerColor() {
        return inner;
    }

    /**
     * @return Get position of ball
     */
    public Point2D getPosition() {
        return getCenter();
    }

    public Shape getBallFace() {
        return ballFace;
    }

    /**
     * @param p Location where the ball needs to move to
     */
    public void moveTo(Point p) {
        getCenter().setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((getCenter().getX() - (w / 2)), (getCenter().getY() - (h / 2)), w, h);
        ballFace = tmp;
    }

    /**
     * @param width Width of ball movement
     * @param height Height of ball movement
     */
    private void setPoints(double width, double height) {
        up.setLocation(getCenter().getX(), getCenter().getY() - (height / 2));
        down.setLocation(getCenter().getX(), getCenter().getY() + (height / 2));

        left.setLocation(getCenter().getX() - (width / 2), getCenter().getY());
        right.setLocation(getCenter().getX() + (width / 2), getCenter().getY());
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }


    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }
}
