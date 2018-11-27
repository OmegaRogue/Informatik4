package omegaRogue;

import javafx.geometry.Point2D;

public class Transform {
    public Point2D position;
    public Point2D velocity;
    public Point2D size;
    public Transform(Point2D pos, Point2D vel, Point2D size) {
        this.position = pos;
        this.velocity = vel;
        this.size = size;
    }
    public Transform(Point2D pos, Point2D vel) {
        this.position = pos;
        this.velocity = vel;
        this.size = new Point2D(1,1);
    }
}
