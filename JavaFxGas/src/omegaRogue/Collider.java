package omegaRogue;


import javafx.concurrent.Task;

public class Collider {
    public final double minX;

    public final double minY;

    public final double maxX;

    public final double maxY;

    public final boolean isTrigger;
    public javafx.scene.shape.Shape collisionShape;
    public Collider (boolean isTrigger, double x1, double y1, double x2, double y2) {
        this.minX = Math.min(x1, x2);
        this.minY = Math.min(y1, y2);
        this.maxX = Math.max(x1, x2);
        this.maxY = Math.max(y1, y2);
        this.isTrigger = isTrigger;
    }

    public boolean intersects(double x1, double y1, double x2, double y2)
    {
        return this.minX < x2 && this.maxX > x1 && this.minY < y2 && this.maxY > y1;
    }
    public BoundingBox offset(double x, double y)
    {
        return new BoundingBox(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
    }
}
