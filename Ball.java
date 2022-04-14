import java.lang.ProcessBuilder.Redirect.Type;

import biuoop.DrawSurface;

public class Ball implements Sprite {
    Line trajectory;
    Point m_center;
    int m_radius;
    java.awt.Color m_color;
    Velocity m_velocity;
    GameEnvironment G_env;

    // constructors
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment GE) {
        this.m_center = center;
        this.m_radius = r;
        this.m_color = color;
        this.G_env = GE;
    }

    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment GE) {
        this.m_center = new Point(x, y);
        this.m_radius = r;
        this.m_color = color;
        this.G_env = GE;
    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }

    private void changeTrajectory(Line line) {
        this.trajectory = line;

    }

    // getters and setters for the center/size of the ball
    public int getX() {
        return (int) this.m_center.getX();
    }

    public void setX(int new_val) {
        this.m_center.m_x = new_val;
    }

    public void setY(int new_val) {
        this.m_center.m_y = new_val;
    }

    public int getY() {
        return (int) this.m_center.getY();
    }

    public int getSize() {
        return this.m_radius;
    }

    public void setSize(int new_val) {
        this.m_radius = new_val;
    }

    // returns the color of the ball
    public java.awt.Color getColor() {
        return this.m_color;
    }

    // setters and getter of the ball's velocity
    public void setVelocity(Velocity v) {
        this.m_velocity = v;

    }

    public void setVelocity(double dx, double dy) {
        this.m_velocity = new Velocity(dx, dy);
    }

    public Velocity getVelocity() {
        return this.m_velocity;
    }

    // moves the ball. the ball's "step" is according to it's velocity
    public void moveOneStep(Point Edge) {
        // this.m_center = this.getVelocity().applyToPoint(this.m_center);
        Point tmp1 = this.m_center;
        /*
         * if (this.getVelocity().m_dy > 0) {
         * while (tmp1.getY() < Edge.getY() && (tmp1.getX() > 50 && tmp1.getX() < 750))
         * {
         * tmp1 = this.getVelocity().applyToPoint(tmp1);
         * }
         * }
         * if (this.getVelocity().m_dy < 0) {
         * while (tmp1.getY() > 50 && (tmp1.getX() > 50 && tmp1.getX() < 750)) {
         * tmp1 = this.getVelocity().applyToPoint(tmp1);
         * }
         */
        while (tmp1.getY() > 50 && tmp1.getY() < Edge.getY() && tmp1.getX() > 50 && tmp1.getY() < Edge.getX()) {
            tmp1 = this.getVelocity().applyToPoint(tmp1);
        }
        changeTrajectory(new Line(m_center, tmp1));
        CollisionInfo inf = G_env.getClosestCollision(trajectory);
        if (inf == null) {
            this.m_center = this.getVelocity().applyToPoint(this.m_center);
        } else {
            Point col = new Point(inf.collisionPoint().getX(), inf.collisionPoint().getY());
            Point vel_Dif = new Point(this.getX() + this.getVelocity().m_dx,
                    this.getY() + this.getVelocity().m_dy);
            /*
             * Rectangle col_rec = inf.collisionObject().getCollisionRectangle();
             * if (vel_Dif.getX() >= col_rec.m_UpLeft.getX()
             * && vel_Dif.getX() <= col_rec.m_UpLeft.getX() + col_rec.getWidth()
             * && vel_Dif.getY() >= col_rec.m_UpLeft.getY()
             * && vel_Dif.getY() <= col_rec.m_UpLeft.getY() + col_rec.getHeight()
             * && !(vel_Dif.getY() > col.getY() + col_rec.getHeight()
             * || vel_Dif.getY() < col.getY() - col_rec.getHeight())) {
             * this.m_center = col;
             * this.m_velocity = inf.collisionObject().hit(col, this.getVelocity());
             * }
             */
            if (vel_Dif.distance(col) > this.m_center.distance(col)) {
                this.m_center = col;
                System.out.println("Gonna hit " + inf.collisionObject());
                this.m_velocity = inf.collisionObject().hit(col, this.getVelocity());

            }
            this.m_center = this.getVelocity().applyToPoint(this.m_center);
        }
    }

    public static void drawAnimation(Point start, double dx, double dy, Ball ball, Point Edge) {
        Ball cur_ball = ball;

        // turns the ball's direction if it's going out of bounds
        /*
         * if (cur_ball.m_center.getX() + cur_ball.m_radius + dx >= Edge.getX()
         * | cur_ball.m_center.getX() - cur_ball.m_radius + dx <= 50) {
         * dx *= -1;
         * cur_ball.setVelocity(dx, dy);
         * }
         * // turns the ball's direction if it's going out of bounds
         * if (cur_ball.m_center.getY() + cur_ball.m_radius + dy >= Edge.getY()
         * | cur_ball.m_center.getY() - cur_ball.m_radius + dy <= 50) {
         * dy *= -1;
         * cur_ball.setVelocity(dx, dy);
         * }
         */
        cur_ball.moveOneStep(Edge);
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.m_color);
        surface.fillCircle((this.getX()), this.getY(), this.m_radius);

    }

    public void timePassed() {
        SimpleTest.drawAnimation(this.m_center, this.m_velocity.m_dx, this.m_velocity.m_dy, this, new Point(750, 550));

    }

}