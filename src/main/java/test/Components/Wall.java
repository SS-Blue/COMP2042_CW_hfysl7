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

import test.Components.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is to show the features displayed on the wall
 * @author Sue Sim
 */
public class Wall {

    private static final int LEVELS_COUNT = 5;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private Random rnd;
    private Rectangle area;

    public Brick[] bricks;
    public Ball ball;
    public Player player;

    private Brick[][] levels;
    private int level;

    private int individualScore;
    private int totalScore;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * @param drawArea Area of game
     * @param brickCount Number of bricks
     * @param lineCount Number of lines
     * @param brickDimensionRatio Ratio of the brick dimension
     * @param ballPos Position of ball
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {

        this.startPoint = new Point(ballPos);

        levels = Levels.makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX, speedY;
        do {
            speedX = rnd.nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -rnd.nextInt(3);
        } while (speedY == 0);

        ball.setSpeed(speedX, speedY);

        player = new Player((Point) ballPos.clone(), 150, 10, drawArea);

        area = drawArea;


    }

//    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, int type) {
//        /*
//          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
//          multiple of lineCount smaller then brickCount
//         */
//        brickCount -= brickCount % lineCount;
//
//        int brickOnLine = brickCount / lineCount;
//
//        double brickLen = drawArea.getWidth() / brickOnLine;
//        double brickHgt = brickLen / brickSizeRatio;
//
//        brickCount += lineCount / 2;
//
//        Brick[] tmp = new Brick[brickCount];
//
//        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
//        Point p = new Point();
//
//        int i;
//        for (i = 0; i < tmp.length; i++) {
//            int line = i / brickOnLine;
//            if (line == lineCount)
//                break;
//            double x = (i % brickOnLine) * brickLen;
//            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
//            double y = (line) * brickHgt;
//            p.setLocation(x, y);
//            tmp[i] = makeBrick(p, brickSize, type);
//        }
//
//        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
//            double x = (brickOnLine * brickLen) - (brickLen / 2);
//            p.setLocation(x, y);
//            tmp[i] = new ClayBrick(p, brickSize);
//        }
//        return tmp;
//
//    }
//
//    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, int typeA, int typeB) {
//        /*
//          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
//          multiple of lineCount smaller then brickCount
//         */
//        brickCount -= brickCount % lineCount;
//
//        int brickOnLine = brickCount / lineCount;
//
//        int centerLeft = brickOnLine / 2 - 1;
//        int centerRight = brickOnLine / 2 + 1;
//
//        double brickLen = drawArea.getWidth() / brickOnLine;
//        double brickHgt = brickLen / brickSizeRatio;
//
//        brickCount += lineCount / 2;
//
//        Brick[] tmp = new Brick[brickCount];
//
//        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
//        Point p = new Point();
//
//        int i;
//        for (i = 0; i < tmp.length; i++) {
//            int line = i / brickOnLine;
//            if (line == lineCount)
//                break;
//            int posX = i % brickOnLine;
//            double x = posX * brickLen;
//            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
//            double y = (line) * brickHgt;
//            p.setLocation(x, y);
//
//            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
//            tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize, typeB);
//        }
//
//        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
//            double x = (brickOnLine * brickLen) - (brickLen / 2);
//            p.setLocation(x, y);
//            tmp[i] = makeBrick(p, brickSize, typeA);
//        }
//        return tmp;
//    }

    /**
     * @param ballPos Create new ball to specific location
     */
    private void makeBall(Point2D ballPos) {
        ball = new RubberBall(ballPos);
    }

//    private Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
//        Brick[][] tmp = new Brick[LEVELS_COUNT][];
//        tmp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY);
//        tmp[1] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
//        tmp[2] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
//        tmp[3] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
//        return tmp;
//    }

    /**
     * Movement of player and ball
     */
    public void move() {
        player.move();
        ball.move();
    }

    /**
     * The impact done from ball to brick and player
     */
    public void findImpacts() {
        if (player.impact(ball)) {
            ball.reverseY();
        } else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
            individualScore += 10;
        } else if (impactBorder()) {
            ball.reverseX();
        } else if (ball.getPosition().getY() < area.getY()) {
            ball.reverseY();
        } else if (ball.getPosition().getY() > area.getY() + area.getHeight()) {
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * @return Movement of ball will reverse when there is impact
     */
    private boolean impactWall() {
        for (Brick b : bricks) {
            switch (b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up, Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right, Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left, Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * @return Ball will move if there's impact on the border of game
     */
    private boolean impactBorder() {
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * @return Get number of brick
     */
    public int getBrickCount() {
        return brickCount;
    }

    /**
     * @return Get number of ball
     */
    public int getBallCount() {
        return ballCount;
    }

    /**
     * @return Get individual score for every brick destroyed
     */
    public int getIndividualScore() {
        return individualScore;
    }

    /**
     * Reset score
     */
    public void resetScore() {
        individualScore = 0;
    }

    /**
     * @return Get total score from entire game
     */
    public int getTotalScore() {
        totalScore += individualScore;
        return totalScore;
    }

    public boolean isBallLost() {
        return ballLost;
    }

    /**
     * Reset the ball
     */
    public void ballReset() {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX, speedY;
        do {
            speedX = rnd.nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -rnd.nextInt(3);
        } while (speedY == 0);

        ball.setSpeed(speedX, speedY);
        ballLost = false;
    }

    /**
     * Reset the wall
     */
    public void wallReset() {
        for (Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * @return Ball becomes 0
     */
    public boolean ballEnd() {
        return ballCount == 0;
    }

    /**
     * @return Brick is 0
     */
    public boolean isDone() {
        return brickCount == 0;
    }

    /**
     * Move to next level
     */
    public void nextLevel() {
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel() {
        return level < levels.length;
    }

    /**
     * @param s Set ball speed
     */
    public void setBallXSpeed(int s) {
        ball.setXSpeed(s);
    }

    /**
     * @param s Set ball speed
     */
    public void setBallYSpeed(int s) {
        ball.setYSpeed(s);
    }

    /**
     * Reset number of ball
     */
    public void resetBallCount() {
        ballCount = 3;
    }

//    private Brick makeBrick(Point point, Dimension size, int type) {
//        Brick out;
//        switch (type) {
//            case CLAY:
//                out = new ClayBrick(point, size);
//                break;
//            case STEEL:
//                out = new SteelBrick(point, size);
//                break;
//            case CEMENT:
//                out = new CementBrick(point, size);
//                break;
//            default:
//                throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
//        }
//        return out;
//    }

}
