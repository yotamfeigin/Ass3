
public class Line {
    public Point m_start;
    public Point m_end;
    public double m_slope;
    public double length;

    public Line(Point start, Point end) {
        /*
         * if (start.getX() >= end.getX()) {
         * m_start = start;
         * m_end = end;
         * } else {
         * m_start = end;
         * m_end = start;
         * }
         */
        this.m_start = start;
        this.m_end = end;
        if (start.getX() == end.getX()) {
            this.m_slope = 0;
        } else {
            this.m_slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
        }
        this.length = this.m_start.distance(this.m_end);
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.m_start = new Point(x1, y1);
        this.m_end = new Point(x2, y2);
        if (m_start.getX() == m_end.getX()) {
            this.m_slope = 0;
        } else {
            this.m_slope = (m_start.getY() - m_start.getY()) / (m_start.getX() - m_end.getX());
        }
        this.length = this.m_start.distance(this.m_end);
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this) == null) {
            return null;
        } else {
            if (this.m_start.distance(rect.intersectionPoints(this).get(0)) < this.m_start
                    .distance(rect.intersectionPoints(this).get(1))) {
                return rect.intersectionPoints(this).get(0);
            } else {
                return rect.intersectionPoints(this).get(1);
            }
        }
    }

    // Return the length of the line
    public double length() {
        return this.m_start.distance(this.m_end);
    }

    // Returns the middle point of the line
    public Point middle() {
        double mid_x = (this.m_start.getX() - this.m_end.getX()) / 2;
        double mid_y = (this.m_start.getY() - this.m_end.getY()) / 2;
        Point mid = new Point(mid_x, mid_y);
        return mid;

    }

    // Returns the start point of the line
    public Point start() {
        return this.m_start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.m_end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if ((this.m_slope == other.m_slope) &&
                (this.m_start.distance(other.m_start) > this.length
                        || this.m_start.distance(other.m_end) > this.length)) {
            return false;
        }

        if (this.m_start.equals(other.m_start) || this.m_start.equals(other.m_end)) {
            return true;

        }

        if (this.m_end.equals(other.m_start) || this.m_end.equals(other.m_end)) {
            return true;
        }
        Edge edge1 = new Edge(this);
        Edge edge2 = new Edge(other);
        if ((edge1.x_min >= edge2.x_max) || (edge1.x_max <= edge2.x_min)
                || (edge1.y_min >= edge2.y_max) || (edge1.y_max <= edge2.y_min)) {
            return false;
        }
        return true;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other) == false) {
            return null;
        }
        Func func1 = new Func(this);
        Func func2 = new Func(other);
        // check if the lines are equal on a certain area
        if (func1.func_slope == func2.func_slope) {
            if (this.m_start.getX() <= other.m_start.getX() && this.m_start.getX() >= other.m_end.getX()) {
                System.out.println("B");
                return this.m_start;
            } else if (other.m_start.getX() <= this.m_start.getX() && other.m_start.getX() >= this.m_end.getX()) {
                System.out.println("B");

                return other.m_start;
            }
        }
        double inter_x;
        double inter_y;
        // checks if the lines are equal on their start\end
        if (this.m_start.getX() == this.m_end.getX()) {
            inter_x = this.m_start.getX();
            inter_y = other.m_slope * inter_x + func2.func_b;

        } else if (other.m_start.getX() == other.m_end.getX()) {
            inter_x = other.m_start.getX();
            inter_y = this.m_slope * inter_x + func1.func_b;
        } else {
            inter_x = (func2.func_b - func1.func_b) / (func1.func_slope - func2.func_slope);
            inter_y = inter_x * m_slope + func1.func_b;
        }
        // double inter_y = inter_x * m_slope + func1.func_b;
        Point inter_point = new Point(inter_x, inter_y);
        // checks if the lines are really intersecting by checking if their length is
        // enough to reach the suspected intersection point
        if (this.length < this.m_start.distance(inter_point) || this.length < this.m_end.distance(inter_point)
                || other.length < other.m_start.distance(inter_point)
                || other.length < other.m_end.distance(inter_point)) {
            return null;
        }

        return inter_point;
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.m_start.equals(other.m_start) && (this.m_end.equals(other.m_end))) {
            return true;
        } else {
            return false;
        }
    }

}
