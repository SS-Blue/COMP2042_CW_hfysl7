package test.Components;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is to show the characteristics of bricks in general
 * @author Sue Sim
 */
abstract public class Brick {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * @param name Name of brick
     * @param pos Position of brick
     * @param size Size of brick
     * @param border Border color of brick
     * @param inner Inner color of brick
     * @param strength Strength of brick
     */
    public Brick(String name, Point pos, Dimension size, Color border, Color inner, int strength) {
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos, size);
        this.border = border;
        this.inner = inner;
        this.setStrength(strength);
        this.fullStrength = this.getStrength();

    }

    /**
     * @param pos Position of brick
     * @param size Size of brick
     * @return Appearance of brick
     */
    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    /**
     * @param point Position where the impact came
     * @param dir Direction of the part that got hit
     * @return True if brick is broken, else false
     */
    public boolean setImpact(Point2D point, int dir) {
        if (broken)
            return false;
        impact();
        return broken;
    }

    public abstract Shape getBrick();


    /**
     * @return Get border color of brick
     */
    public Color getBorderColor() {
        return border;
    }

    /**
     * @return Get color of brick itself
     */
    public Color getInnerColor() {
        return inner;
    }


    /**
     * @param b The ball used to hit the brick
     * @return The impact done on the brick
     */
    public final int findImpact(Ball b) {
        if (broken)
            return 0;
        int out = 0;
        if (brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if (brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if (brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if (brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    public final boolean isBroken() {
        return broken;
    }

    /**
     * Reset strength of ball to initial strength
     */
    public void repair() {
        broken = false;
        setStrength(fullStrength);
    }

    /**
     * This is when one damage is done on cement and steel bricks
     */
    public void impact() {
        setStrength(getStrength() - 1);
        broken = (getStrength() == 0);
    }

    /**
     * @return Get random tries of hitting a specific brick
     */
    public static Random getRnd() {
        return rnd;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    protected Shape getBrickFace() {
        return brickFace;
    }
}





