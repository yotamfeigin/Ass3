import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    Point m_UpLeft;
    double m_width;
    double m_height;
    Line[] Lines;

    public Line[] Get_Lines() {

        /*
         * Line[] lines = new Line[4];
         * lines[0] = new Line(m_x, m_y, m_x + this.m_width, m_y);
         * lines[1] = new Line(m_x, m_y + m_height, m_x + this.m_width, m_y +
         * this.m_height);
         * lines[2] = new Line(m_x + this.m_width, m_y, m_x + this.m_width, m_y +
         * this.m_height);
         * lines[3] = new Line(m_x, m_y, m_x, m_y + this.m_height);
         */
        return this.Lines;
    }

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.m_UpLeft = upperLeft;
        this.m_width = width;
        this.m_height = height;
        Lines = new Line[] {
                new Line((int) m_UpLeft.getX(), (int) m_UpLeft.getY(), (int) (m_UpLeft.getX() + m_width),
                        (int) m_UpLeft.getY()),
                new Line((int) m_UpLeft.getX(), (int) (m_UpLeft.getY() - m_height),
                        (int) (m_UpLeft.getX() + m_width),
                        (int) (m_UpLeft.getY() - m_height)),
                new Line((int) m_UpLeft.getX(), (int) m_UpLeft.getY(), (int) m_UpLeft.getX(),
                        (int) (m_UpLeft.getY() - m_height)),
                new Line((int) (m_UpLeft.getX() + m_width), (int) m_UpLeft.getY(), (int) (m_UpLeft.getX() + m_width),
                        (int) (m_UpLeft.getY() - m_height)) };
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {

        Line[] lines = this.Get_Lines();
        List<Point> intersections = new ArrayList<Point>();

        for (Line border : lines) {
            int i = 0;
            if (border.isIntersecting(line)) {
                if (intersections.isEmpty()) {
                    intersections.add(border.intersectionWith(line));
                }

                for (Point inter_p : intersections) {
                    if (inter_p.getX() == border.intersectionWith(line).getX()) {
                        i++;
                        break;
                    }
                }
                if (i == 0) {
                    intersections.add(border.intersectionWith(line));
                }
            }
        }
        if (intersections.size() > 0) {
            return intersections;
        } else {
            return null;
        }
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.m_width;
    }

    public double getHeight() {
        return this.m_height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.m_UpLeft;
    }

}
