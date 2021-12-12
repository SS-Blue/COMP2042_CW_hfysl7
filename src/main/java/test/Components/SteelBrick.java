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

import test.Components.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is to show the characteristics of steel bricks
 * @author Sue Sim
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * @param point Point of brick
     * @param size Size of brick
     */
    public SteelBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, STEEL_STRENGTH);
        rnd = new Random();
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

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * @param point Position when there is impact
     * @param dir Direction of the part that got hit
     * @return True if brick is broken, else false
     */
    public boolean setImpact(Point2D point, int dir) {
        if (super.isBroken())
            return false;
        impact();
        return super.isBroken();
    }

    /**
     * Strength of steel brick are random
     */
    public void impact() {
        if (rnd.nextDouble() < STEEL_PROBABILITY) {
            super.impact();
        }
    }

}
