import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    List<Collidable> collidables = new ArrayList<Collidable>();

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo nearest = new CollisionInfo(null, null);
        Boolean collide = false;
        for (Collidable collidable : collidables) {
            Line[] borders = collidable.getCollisionRectangle().Get_Lines();
            for (Line border : borders) {
                Point a = trajectory.intersectionWith(border);
                if (a != null) {
                    if (nearest.c_point == null || (trajectory.m_start.distance(a) < trajectory.m_start
                            .distance(nearest.collisionPoint()))) {
                        collide = true;
                        nearest.setPoint(a);
                        nearest.setCol(collidable);
                    }
                }
            }
        }
        if (collide == true) {
            return nearest;
        } else {
            return null;
        }
    }
}
