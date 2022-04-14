import java.awt.Color;

import biuoop.*;

public class Game {

    GUI gui = new GUI("Game", 800, 600);
    biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
    Sleeper sleeper = new Sleeper();
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private java.awt.Color Colors[] = { Color.BLUE, Color.DARK_GRAY, Color.YELLOW, Color.GREEN, Color.pink,
            Color.MAGENTA };

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    /*
     * public void initialize() {
     * Sleeper sleeper = new Sleeper();
     * Ball ball = new Ball(new Point(400, 400), 8, Color.GRAY, environment);
     * ball.setVelocity(15, 20);
     * for (int i = 0; i < 6; i++) {
     * for (int j = 0; j < i; j++) {
     * Block block = new Block(new Point(j * 100, i * 40), (double) 100, 40);
     * this.addCollidable(block);
     * this.addSprite(block);
     * }
     * }
     * while (true) {
     * DrawSurface d = gui.getDrawSurface();
     * SimpleTest.drawAnimation(ball.m_center, ball.getVelocity().m_dx,
     * ball.getVelocity().m_dy, ball, d,
     * new Point(800, 600));
     * sprites.drawAllOn(d);
     * ball.drawOn(d);
     * gui.show(d);
     * sleeper.sleepFor(50);
     * 
     * }
     * 
     * }
     */

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        Ball ball1 = new Ball(new Point(50, 50), 2, Color.green, environment);
        Ball ball2 = new Ball(new Point(200, 300), 5, Color.ORANGE, environment);
        Paddle paddle = new Paddle(new Point(400, 520), 80, 30, keyboard, Color.white);
        ball1.setVelocity(-9, 12);
        ball2.setVelocity(15, -3);
        ball1.addToGame(this);
        paddle.addToGame(this);

        // ball2.addToGame(this);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                Block block = new Block(new Point(750 - (j + 1) * 40, 50 + i * 10), (double) 40, 10, Colors[i]);
                environment.addCollidable(block);
                block.addToGame(this);
            }
        }
        Block[] edges;
        edges = new Block[] {
                new Block(new Point(0, 0), (double) 50, 600, Color.DARK_GRAY),
                new Block(new Point(750, 0), (double) 50, 600, Color.DARK_GRAY),
                new Block(new Point(0, 0), (double) 800, 50, Color.DARK_GRAY),
                new Block(new Point(0, 550), (double) 800, 50, Color.DARK_GRAY)
        };
        for (Block block : edges) {
            block.addToGame(this);

        }
    }

    // Run the game -- start the animation loop.
    public void run() {
        // ...
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.blue);
            d.fillRectangle(50, 50, 700, 500);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
