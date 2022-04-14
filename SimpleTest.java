import biuoop.*;

import java.awt.Color;
import java.util.Random;

public class SimpleTest {

    public static void drawAnimation(Point start, double dx, double dy, Ball ball, Point Edge) {
        Ball cur_ball = ball;

        // turns the ball's direction if it's going out of bounds
        if (cur_ball.m_center.getX() + cur_ball.m_radius + dx >= Edge.getX()
                || cur_ball.m_center.getX() - cur_ball.m_radius + dx <= 50) {
            dx *= -1;
            cur_ball.setVelocity(dx, dy);
        }
        // turns the ball's direction if it's going out of bounds
        if (cur_ball.m_center.getY() + cur_ball.m_radius + dy >= Edge.getY()
                || cur_ball.m_center.getY() - cur_ball.m_radius + dy <= 50) {
            dy *= -1;
            cur_ball.setVelocity(dx, dy);
        }
        cur_ball.moveOneStep(Edge);
        // cur_ball.drawOn(f);
    }

    public static void main(String[] args) {
        /*
         * Sleeper sleeper = new Sleeper();
         * GameEnvironment G = new GameEnvironment();
         * GUI gui = new GUI("title", 400, 400);
         * Point surface_min = new Point(0, 0);
         * Point surface_max = new Point(400, 400);
         * Point a = new Point(20, 200);
         * Point b = new Point(100, 200);
         * Point c = new Point(180, 200);
         * Block cold1 = new Block(a, (double) 60, 20);
         * Block cold2 = new Block(b, (double) 60, 20);
         * Block cold3 = new Block(c, (double) 60, 20);
         * G.addCollidable(cold1);
         * G.addCollidable(cold2);
         * G.addCollidable(cold3);
         * 
         * Ball test_ball = new Ball(200, 300, 3, Color.black, G);
         * 
         * Velocity v = new Velocity(20, 20);
         * test_ball.setVelocity(v);
         * 
         * while (true) {
         * DrawSurface d = gui.getDrawSurface();
         * cold1.drawOn(d);
         * cold2.drawOn(d);
         * cold3.drawOn(d);
         * drawAnimation(test_ball.m_center, test_ball.getVelocity().m_dx,
         * test_ball.getVelocity().m_dy, test_ball, d,
         * surface_max);
         * gui.show(d);
         * sleeper.sleepFor(50);
         */
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
