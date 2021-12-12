/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test.Components;

import java.awt.*;


public class Player {


    public static final Color BORDER_COLOR = Color.LIGHT_GRAY.darker().darker();
    public static final Color INNER_COLOR = Color.GRAY;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    public Player(Point ballPoint, int width, int height, Rectangle container) {
        this.setBallPoint(ballPoint);
        setMoveAmount(0);
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    private Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (getBallPoint().getX() - (width / 2)), (int) getBallPoint().getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    public boolean impact(Ball b) {
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down);
    }

    public void move() {
        double x = getBallPoint().getX() + getMoveAmount();
        if (x < min || x > max)
            return;
        getBallPoint().setLocation(x, getBallPoint().getY());
        playerFace.setLocation(getBallPoint().x - (int) playerFace.getWidth() / 2, getBallPoint().y);
    }

    public void moveLeft() {
        setMoveAmount(-DEF_MOVE_AMOUNT);
    }

    public void moveRight() {
        setMoveAmount(DEF_MOVE_AMOUNT);
    }

    public void stop() {
        setMoveAmount(0);
    }

    public Shape getPlayerFace() {
        return playerFace;
    }

    public void moveTo(Point p) {
        getBallPoint().setLocation(p);
        playerFace.setLocation(getBallPoint().x - (int) playerFace.getWidth() / 2, getBallPoint().y);
    }

    public int getMoveAmount() {
        return moveAmount;
    }

    public void setMoveAmount(int moveAmount) {
        this.moveAmount = moveAmount;
    }

    public Point getBallPoint() {
        return ballPoint;
    }

    public void setBallPoint(Point ballPoint) {
        this.ballPoint = ballPoint;
    }
}
