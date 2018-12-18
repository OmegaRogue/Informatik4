package omegaRogue;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import static omegaRogue.MyJfxApp.*;


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

	public double T = baseTemp;
	public double m = 27 * u;
	public double v = 1;
	public Point2D baseVelocity;
	EventHandler<MouseEvent> select = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			if (e.getSource().getClass() == Ball.class) {
				Ball balll = (Ball) e.getSource();
				Selector.getInstance().attach(balll);
			}


		}
	};

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
		baseVelocity = velocity.normalize();
		velocity = baseVelocity.multiply(v);


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
		baseVelocity = velocity.normalize();
		velocity = baseVelocity.multiply(v);

	}

	public double getTemperature() {
		return T;
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

	public void setTemperature(double T) {
		this.T = T;
	}

	/**
	 * Aktualisiert die Ballposition entsprechend der Geschwindigkeitsattribute.
	 */
	public void updatePosition() {
		position = position.add(velocity);
		relocate(position.getX(), position.getY());
		//nr.relocate(position.getX(), position.getY());


	}

	public void Start() {
		setOnMouseClicked(select);

	}

	public void Update() {
		v = calcVel(m, T);
		velocity = baseVelocity.multiply(v);
		updatePosition();
	}

	public void reflectX() {
		baseVelocity = new Point2D(-baseVelocity.getX(), baseVelocity.getY());
	}

	public void reflect(EnumDirection direction) {
		if (direction == EnumDirection.LEFT || direction == EnumDirection.RIGHT)
			reflectX();
		if (direction == EnumDirection.UP || direction == EnumDirection.DOWN)
			reflectY();
	}

	public void reflectY() {
		baseVelocity = new Point2D(baseVelocity.getX(), -baseVelocity.getY());
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

	public void reflect(Ball b) {
		Point2D temp = baseVelocity;
		baseVelocity = b.baseVelocity;
		b.baseVelocity = temp;
	}

	public double calcVel(double m, double T) {
		return Math.sqrt(3 * boltzmann * T / m);
	}

	public double calcTemp(double m, double v) {
		return m * v * v / (3 * boltzmann);
	}
}

