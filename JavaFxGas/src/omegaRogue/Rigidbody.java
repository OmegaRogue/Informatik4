package omegaRogue;

import javafx.geometry.Point2D;

public interface Rigidbody {
	boolean collideWith(Rigidbody r);

	EnumDirection collideAt(Rigidbody r);

	Point2D getPosition();

	Point2D getSize();


	String toString();
}
