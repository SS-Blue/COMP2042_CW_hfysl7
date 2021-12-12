package test.Components;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This class is to show the characteristics of cement bricks
 * @author Sue Sim
 */
public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;


    /**
     * @param point Point of brick
     * @param size Size of brick
     */
    public CementBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, CEMENT_STRENGTH);
        crack = new Crack(CementBrick.this, DEF_CRACK_DEPTH, DEF_STEPS);
        brickFace = super.brickFace;
    }

    /**
     * @param pos Position of brick
     * @param size Size of brick
     * @return Appearance of brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    /**
     * @param point Position when there is impact
     * @param dir Direction of the part that got hit
     * @return True if brick is broken, else false
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if (super.isBroken())
            return false;
        super.impact();
        if (!super.isBroken()) {
            crack.makeCrack(point, dir);
            updateBrick();
            return false;
        }
        return true;
    }


    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Update brick condition
     */
    private void updateBrick() {
        if (!super.isBroken()) {
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace, false);
            brickFace = gp;
        }
    }

    /**
     * Reset crack in brick
     */
    public void repair() {
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
