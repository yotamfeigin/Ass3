import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

public class SpriteCollection {
    List<Sprite> sprites = new ArrayList<Sprite>();

    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    public void cleanSprite() {
        sprites.clear();
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);

        }
    }
}