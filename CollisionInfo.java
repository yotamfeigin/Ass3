public class CollisionInfo {
    Point c_point;
    Collidable m_cold;

    public CollisionInfo(Point a, Collidable b) {
        this.c_point = a;
        this.m_cold = b;
    }

    public void setPoint(Point p) {
        this.c_point = p;
    }

    public void setCol(Collidable c) {
        this.m_cold = c;
    }

    // the point at which the collision occurs.
    public Point collisionPoint() {
        return c_point;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return m_cold;

    }
}