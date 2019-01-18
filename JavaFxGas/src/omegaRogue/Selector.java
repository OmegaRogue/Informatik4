package omegaRogue;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Selector extends Circle {
	private static Selector ourInstance = new Selector();

	public ColorPicker colorPicker;
	private Rigidbody attached;

	private Selector() {
		super(0, 0, 15);
		setFill(Color.INDIGO.deriveColor(0, 1, 1, 0.75));
	}

	public static Selector getInstance() {
		return ourInstance;
	}

	public Rigidbody getAttached() {
		return attached;
	}

	public void move(double x, double y) {
		relocate(x, y);
	}

	public void Update() {
		if (attached != null) {
			move(attached.getPosition().getX() - (getRadius() - attached.getSize().getX()),
					attached.getPosition().getY() - (getRadius() - attached.getSize().getX()));
		}
	}

	public void attach(Rigidbody object) {
		attached = object;
		colorPicker.setValue(((Ball) attached).c);
	}
}
