package omegaRogue;

import javafx.geometry.Point2D;

public class transform {
    public Point2D position;
    public Point2D velocity;
    public transform() {
        position = Point2D.ZERO;
        velocity = Point2D.ZERO;
    }
    public transform(Point2D position) {
        this.position = position;
        this.velocity = Point2D.ZERO;

    }
    public transform(Point2D position,Point2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }
}
