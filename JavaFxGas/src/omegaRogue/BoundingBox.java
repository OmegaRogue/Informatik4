package omegaRogue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class BoundingBox implements Behaviour {

	public final double minX;

	public final double minY;

	public final double maxX;

	public final double maxY;
	public final Rectangle rect;

	public BoundingBox(double x1, double y1, double x2, double y2) {
		this.minX = Math.min(x1, x2);
		this.minY = Math.min(y1, y2);
		this.maxX = Math.max(x1, x2);
		this.maxY = Math.max(y1, y2);
		rect = new Rectangle(minX, minY, maxX - minX, maxY - minY);
		rect.setFill(Color.CYAN);
		rect.setStrokeWidth(2);
	}

	public BoundingBox(Rectangle rect) {
		this.rect = rect;
		this.minX = rect.getX();
		this.minY = rect.getY();
		this.maxX = rect.getX() + rect.getWidth();
		this.maxY = rect.getY() + rect.getHeight();
		rect.setFill(Color.CYAN);
		rect.setStrokeWidth(2);
	}

	public boolean intersects(double x1, double y1, double x2, double y2) {

		return this.rect.getBoundsInParent().intersects(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2) - Math.min(x1, x2), Math.max(y1, y2) - Math.min(y1, y2));
	}

	public boolean intersects(BoundingBox box) {
		return this.rect.getBoundsInParent().intersects(box.rect.getBoundsInParent());
	}


	public BoundingBox offset(double x, double y) {
		return new BoundingBox(this.minX + x, this.minY + y, this.maxX + x, this.maxY + y);
	}

	public void Start() {
	}

	public void Update() {
	}
}
