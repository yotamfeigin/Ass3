public class Edge {
    double x_max;
    double x_min;
    double y_max;
    double y_min;

    // Used to access each line's min and max values
    public Edge(Line line) {
        x_max = Math.max(line.m_start.getX(), line.m_end.getX());
        x_min = Math.min(line.m_start.getX(), line.m_end.getX());
        y_max = Math.max(line.m_start.getY(), line.m_end.getY());
        y_min = Math.min(line.m_start.getY(), line.m_end.getY());
    }

}
