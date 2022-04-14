
public class Point {
    public double m_x;
    public double m_y;

    // constructor
    public Point(double x, double y) {
        m_x = x;
        m_y = y;
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double x1 = this.m_x;
        double x2 = other.m_x;
        double y1 = this.m_y;
        double y2 = other.m_y;
        return (Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
    }

    public void Printer() {
        System.out.println("(" + this.m_x + "," + this.m_y + ")");
    }

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if (this.distance(other) == 0) {
            return true;
        } else {
            return false;
        }

    }

    // Return the x and y values of this point
    public double getX() {
        return this.m_x;
    }

    public double getY() {
        return this.m_y;
    }

}