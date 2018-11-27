package omegaRogue;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle implements Rigidbody, Behaviour {

    public Point2D position;
    public Point2D dimensions;

    public Block(long x, long y, long w, long h, Color color) {
        super(x,y,w,h);
        setFill(color);
        position = new Point2D(x,y);
        dimensions = new Point2D(w,h);
    }
    @Override
    public void Start() {

    }

    @Override
    public void Update() {

    }

    @Override
    public boolean collideWith(Rigidbody r) {
        if(r.getClass() == Ball.class) {
            Ball b = (Ball) r;

            return this.getBoundsInParent().intersects(b.getBoundsInParent());


        }
        return false;
    }

    @Override
    public EnumDirection collideAt(Rigidbody r) {
        return EnumDirection.NONE;
    }
}
