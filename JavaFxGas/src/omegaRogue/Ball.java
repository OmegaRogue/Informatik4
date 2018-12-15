package omegaRogue;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * @author OmegaRogue
 * @version 1
 */
public class Ball extends Circle implements Behaviour, Rigidbody {
	static int ballCount = 0;
	static Scene scene;
	//Vector3 position;
	//Vector3 velocity;
	public Point2D position;
	public Point2D velocity;
	public Text nr;
	int ballNr = 0;
	double radius = 1;
	Color c = Color.BLUE;
	private double velMod;

	/**
	 * Konstruktor der Ballklasse.
	 *
	 * @param bx     x-Koordinate des Startpunktes.
	 * @param by     y-Koordinate des Startpunktes.
	 * @param radius Radius des Balls.
	 * @param col    Farbe des Balls.
	 */
	public Ball(Scene scene, double bx, double by, double vx, double vy, double radius, Color col) {
		super(bx, by, radius);
		//position = new Vector3(bx,by);
		//velocity = new Vector3(vx,vy);
		position = new Point2D(bx, by);
		velocity = new Point2D(vx, vy);
		this.radius = radius;
		c = col;
		setFill(col);
		Ball.scene = scene;
		ballNr = ballCount;
		ballCount++;
		nr = new Text("" + ballNr);

	}

	public Ball(Scene scene, double bx, double by, double radius, Color col) {
		super(bx, by, radius);
		//position = new Vector3(bx,by);
		//velocity = Vector3.Zero();
		position = new Point2D(bx, by);
		velocity = Point2D.ZERO;
		this.radius = radius;
		c = col;
		setFill(col);
		Ball.scene = scene;
		ballNr = ballCount;
		ballCount++;
		nr = new Text(Integer.toString(ballNr));

	}

	public double getVelMod() {
		return velMod;
	}

	public void setVelMod(double velMod) {
		this.velMod = velMod;
	}

	/**
	 * Setzt die Farbe des Balls.
	 *
	 * @param farbe
	 */
	public void setColor(Color farbe) {
		c = farbe;
		setFill(farbe);
	}

	/**
	 * Aktualisiert die Ballposition entsprechend der Geschwindigkeitsattribute.
	 */
	public void updatePosition() {
		position = position.add(velocity.multiply(velMod));
		relocate(position.getX(), position.getY());
		nr.relocate(position.getX(), position.getY());


	}

	public void Start() {
		velMod = 3;
	}

	public void Update() {
		updatePosition();
	}

	public void reflectX() {
		velocity = new Point2D(-velocity.getX(), velocity.getY());
	}

	public void reflectY() {
		velocity = new Point2D(velocity.getX(), -velocity.getY());
	}

	public void reflect(EnumDirection direction) {
		if (direction == EnumDirection.LEFT || direction == EnumDirection.RIGHT)
			reflectX();
		if (direction == EnumDirection.UP || direction == EnumDirection.DOWN)
			reflectY();
	}

	public void reflect(Ball b) {
		Point2D temp = velocity;
		velocity = b.velocity;
		b.velocity = temp;
	}

	public boolean collideWith(Rigidbody r) {
		if (r.getClass() == Ball.class) {
			Ball b = (Ball) r;

			return this.getBoundsInParent().intersects(b.getBoundsInParent());


		}
		if (r.getClass() == Block.class) {
		}

		return false;
	}

	@Override
	public EnumDirection collideAt(Rigidbody r) {
		return EnumDirection.NONE;
	}

	public Point2D getPosition() {
		return position;
	}

	@Override
	public Point2D getSize() {
		return new Point2D(radius, radius);
	}

}
