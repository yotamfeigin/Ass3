public class Func {
    double func_y;
    double func_x;
    double func_slope;
    double func_b;

    // used to create an equation of the lines given so it would be easier to
    // calculate intersection points
    public Func(Line line) {
        func_y = line.m_start.getY();
        func_x = line.m_start.getX() / func_y;
        func_slope = line.m_slope;
        func_b = line.m_start.getY() - (line.m_slope * line.m_start.getX());
    }

    public void print_func() {
        System.out.println("Y= " + func_slope + func_x + "+" + func_b);

    }
}