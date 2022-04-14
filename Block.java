import java.awt.Color;

import javax.swing.text.AttributeSet.ColorAttribute;

import biuoop.DrawSurface;

public class Block implements Collidable, Sprite {
    Point m_UpLeft;
    double m_width;
    double m_height;
    java.awt.Color m_color;
    double epsilon = 0.1;

    public Block(Point Up_Left, Double Wid, double High, java.awt.Color Color) {
        this.m_UpLeft = Up_Left;
        this.m_width = Wid;
        this.m_height = High;
        this.m_color = Color;
    }

    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    public void setColor(java.awt.Color color) {
        this.m_color = color;
    }

    public Point GetEdge() {
        return this.m_UpLeft;
    }

    public double getWidth() {
        return this.m_width;
    }

    public double getHeight() {
        return this.m_height;
    }

    public Rectangle getCollisionRectangle() {
        Rectangle rec = new Rectangle(m_UpLeft, m_width, m_height);
        return rec;

    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        /*
         * if ((collisionPoint.getX() + currentVelocity.m_dx >= m_UpLeft.getX()
         * | collisionPoint.getX() + currentVelocity.m_dx <= m_UpLeft.getX() + m_width)
         * && collisionPoint.getY() + currentVelocity.m_dy >= this.m_UpLeft.getY()
         * && collisionPoint.getY() + currentVelocity.m_dy <= this.m_UpLeft.getY() +
         * this.m_height) {
         * System.out.println("side");
         * currentVelocity.m_dx *= -1;
         * }
         * if ((collisionPoint.getY() + currentVelocity.m_dy >= m_UpLeft.getY()
         * | collisionPoint.getY() + currentVelocity.m_dy <= m_UpLeft.getY() + m_height)
         * && collisionPoint.getX() + currentVelocity.m_dx >= this.m_UpLeft.getX() &&
         * collisionPoint.getX() + currentVelocity.m_dx <= this.m_UpLeft.getX() +
         * this.m_width) {
         * System.out.println("top or bottom");
         * currentVelocity.m_dy *= -1;
         * }
         */
        if ((collisionPoint.getX() <= m_UpLeft.getX() + epsilon && collisionPoint.getX() >= m_UpLeft.getX() - epsilon)
                ||
                (collisionPoint.getX() <= m_UpLeft.getX() + m_width + epsilon
                        && collisionPoint.getX() >= m_UpLeft.getX() + m_width - epsilon)
                        && (collisionPoint.getY() >= this.m_UpLeft.getY()
                                && collisionPoint.getY() <= this.m_UpLeft.getY() + this.m_height)) {
            System.out.println("Changed dx");
            currentVelocity.m_dx *= -1;
        }
        if ((collisionPoint.getY() <= m_UpLeft.getY() + epsilon && collisionPoint.getY() >= m_UpLeft.getX() - epsilon)
                || (collisionPoint.getY() <= m_UpLeft.getY() + m_height + epsilon
                        && collisionPoint.getY() >= m_UpLeft.getY() + m_height - epsilon)
                        && (collisionPoint.getX() >= this.m_UpLeft.getX()
                                && collisionPoint.getX() <= this.m_UpLeft.getX() + this.m_width)) {
            System.out.println("Changed dy");

            currentVelocity.m_dy *= -1;
        }

        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.m_color);
        surface.fillRectangle(((int) this.GetEdge().getX()), (int) this.GetEdge().getY(), (int) this.getWidth(),
                (int) this.getHeight());
        surface.setColor(Color.WHITE);
        surface.drawRectangle(((int) this.GetEdge().getX()), (int) this.GetEdge().getY(), (int) this.getWidth(),
                (int) this.getHeight());
    }

    public void timePassed() {

    }

}
