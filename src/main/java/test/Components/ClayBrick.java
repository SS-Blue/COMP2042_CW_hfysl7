package test.Components;

import test.Components.Brick;

import java.awt.*;
import java.awt.Point;

/**
 * This class is to show the characteristics of clay bricks
 * @author Sue Sim
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * @param point Point of brick
     * @param size Size of brick
     */
    public ClayBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
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

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
