package test.Components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final Point BallPoint = new Point(300, 400);
    private final Rectangle Container = new Rectangle(0, 0, 600, 450);

    private Player PTest;

    @BeforeEach
    void setUp() {
        PTest = new Player(BallPoint, 150, 10, Container);
    }

    @Test
    void testImpact() {

        final Ball b = new RubberBall(BallPoint);

        final boolean result = PTest.impact(b);

        assertTrue(result);
    }


    @Test
    void testMoveToTheLeft() {

        PTest.moveLeft();

        assertEquals(-5, PTest.getMoveAmount());
    }

    @Test
    void testMoveToTheRight() {

        PTest.moveRight();

        assertEquals(5, PTest.getMoveAmount());
    }

    @Test
    void testStop() {
        PTest.moveLeft();
        PTest.stop();

        assertEquals(0, PTest.getMoveAmount());
    }

    @Test
    void testMoveTo() {

        final Point p = new Point(10, 10);

        PTest.moveTo(p);

        assertEquals(new Point(10, 10), PTest.getBallPoint());

    }
}