import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    Point m_UpLeft;
    double m_width;
    double m_height;
    java.awt.Color color;

    public Paddle(Point Edge, double Wid, double High, biuoop.KeyboardSensor KB, java.awt.Color color) {
        this.m_UpLeft = Edge;
        this.m_width = Wid;
        this.m_height = High;
        this.keyboard = KB;
        this.color = color;
    }

    public Point getEdge() {
        return this.m_UpLeft;
    }

    private void setX(double val) {
        double m_y = this.getEdge().getY();
        this.m_UpLeft = new Point(val, m_y);
    }

    private void horizontal_Move(double len) {
        double prev = this.m_UpLeft.getX();
        setX(prev + len);
    }

    public double getWidth() {
        return this.m_width;
    }

    public double getHeight() {
        return this.m_height;
    }

    public void moveLeft() {
        if (this.m_UpLeft.getX() > 50) {
            if (this.m_UpLeft.getX() < 60) {
                setX(0);
            } else {
                horizontal_Move(-10);
            }
        }
    }

    public void moveRight() {
        if (this.m_UpLeft.getX() + this.getWidth() < 750) {
            if (this.m_UpLeft.getX() + this.getWidth() + 10 > 750) {
                setX(800 - this.getWidth());
            } else {
                horizontal_Move(10);
            }
        }
    }

    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.getEdge().getX(), (int) this.getEdge().getY(), (int) this.getWidth(),
                (int) this.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        Rectangle rec = new Rectangle(m_UpLeft, m_width, m_height);
        return rec;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() > m_UpLeft.getY()) {
            return currentVelocity;
        }

        double hit_X = collisionPoint.getX();
        double hit_Y = collisionPoint.getY();
        double edge_X = this.getEdge().getX();
        double edge_Y = this.getEdge().getY();
        double segment_len = this.getWidth() / 5;
        double vel_size = currentVelocity.getLen();
        if (hit_X >= edge_X && hit_X <= edge_X + this.getWidth()) {
            if (hit_X >= edge_X && hit_X <= edge_X + segment_len) {
                currentVelocity = Velocity.fromAngleAndSpeed(300, vel_size);
            } else if (hit_X >= edge_X + segment_len && hit_X <= edge_X + 2 * segment_len) {
                currentVelocity = Velocity.fromAngleAndSpeed(330, vel_size);
            } else if (hit_X >= edge_X + 2 * segment_len && hit_X <= edge_X + 3 * segment_len) {
                double new_dy = -currentVelocity.m_dy;
                currentVelocity = new Velocity(currentVelocity.m_dx, new_dy);

            } else if (hit_X >= edge_X + 3 * segment_len && hit_X <= edge_X + 4 * segment_len) {
                currentVelocity = Velocity.fromAngleAndSpeed(30, vel_size);

            } else if (hit_X >= edge_X + 4 * segment_len && hit_X <= edge_X + this.getWidth()) {
                currentVelocity = Velocity.fromAngleAndSpeed(60, vel_size);
            }

        } else if (hit_Y >= edge_Y) {
            if (hit_X + currentVelocity.m_dx >= edge_X
                    || hit_X + currentVelocity.m_dx <= edge_X + this.getWidth()) {
                currentVelocity.m_dx *= -1;
            }
        }
        return currentVelocity;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}