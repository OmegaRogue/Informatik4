package omegaRogue;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle implements Rigidbody, Behaviour {

	public Point2D position;
	public Point2D dimensions;

	public Rectangle left;
	public Rectangle right;
	public Rectangle top;
	public Rectangle bottom;
	public Rectangle[] sides = new Rectangle[4];
	private double cS = 0.1;

	public Block(double x, double y, double w, double h, Color color) {
		super(x, y, w, h);
		setFill(color);
		position = new Point2D(x, y);
		dimensions = new Point2D(w, h);
		sides[0] = left = new Rectangle(x - cS, y - cS, cS, h + 2 * cS);
		sides[1] = right = new Rectangle(x + w, y - cS, cS, h + 2 * cS);
		sides[3] = bottom = new Rectangle(x - cS, y + h, w + 2 * cS, cS);
		sides[2] = top = new Rectangle(x - cS, y - cS, w + 2 * cS, cS);
		for (Rectangle rect : sides
		) {
			rect.setFill(Color.INDIGO);
		}

	}

	@Override
	public void Start() {

	}

	@Override
	public void Update() {

	}

	@Override
	public boolean collideWith(Rigidbody r) {
		if (r.getClass() == Ball.class) {
			Ball b = (Ball) r;

			return this.getBoundsInParent().intersects(b.getBoundsInParent())
					|| left.getBoundsInParent().intersects(b.getBoundsInParent())
					|| right.getBoundsInParent().intersects(b.getBoundsInParent())
					|| top.getBoundsInParent().intersects(b.getBoundsInParent())
					|| bottom.getBoundsInParent().intersects(b.getBoundsInParent())
					;


		}
		return false;
	}

	@Override
	public EnumDirection collideAt(Rigidbody r) {
		if (r.getClass() == Ball.class) {
			Ball b = (Ball) r;
			if (!collideWith(b))
				return EnumDirection.NONE;
			int i = sideCheck(b);

			switch (i) {
				case 0:
					return EnumDirection.LEFT;
				case 1:
					return EnumDirection.RIGHT;
				case 2:
					return EnumDirection.UP;
				case 3:
					return EnumDirection.DOWN;
				case 4:
					return EnumDirection.NONE;
			}


		}
		return EnumDirection.NONE;

	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public Point2D getSize() {
		return dimensions;
	}

	private int sideCheck(Ball b) {


		for (int i = 0; i < sides.length; i++) {
			if (sides[i].getBoundsInParent().intersects(b.getBoundsInParent())) {
				return i;
			}
		}
		return 4;
	}


}
