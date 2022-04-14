
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    // dx and dy are the changes in the Y-axis and X-axis dou to the velocity
    double m_dx;
    double m_dy;

    public Velocity(double dx, double dy) {
        this.m_dx = dx;
        this.m_dy = dy;
    }

    public double getLen() {
        return Math.sqrt(Math.pow(this.m_dx, 2) + Math.pow(this.m_dy, 2));
    }

    // calculates dx and dy by a given size of the vector and it's angle
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double in_radians = Math.toRadians(angle);
        double dy = speed * Math.cos(in_radians);
        double dx = speed * Math.sin(in_radians);
        return new Velocity(dx, dy);
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.m_dx;
        double y = p.getY() + this.m_dy;
        Point p2 = new Point(x, y);
        return p2;
    }
}