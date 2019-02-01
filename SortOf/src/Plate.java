import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plate extends Rectangle {

	int i = 0;
	int n = 0;

	double x = 0;

	public double baseX = 0;
	public double baseY = 0;

	static final double height = 10;

	static final double yStep = 15;
	static final double xStep = 30;

	Stick thisStick;

	public Plate(int i, Stick stick) {
		super(stick.baseX-(0.5*(i*20.0)),(stick.baseY-20.0)-(stick.plates.size()*10.0), i*20,10);

		this.i = i;
		this.n = stick.plates.size();

		this.baseX = stick.baseX;
		this.baseY = stick.baseY;

		stick.plates.add(this);
		thisStick = stick;

		this.setFill(Color.FIREBRICK);

		this.setArcHeight(10);
		this.setArcWidth(10);

		this.setStroke(Color.DIMGREY);
		this.setStrokeWidth(2);

	}

	public void move(Stick stick) {
		thisStick.plates.remove(this);
		this.n = stick.plates.size();
		this.baseX = stick.baseX;
		this.baseY = stick.baseY;
		thisStick = stick;
		stick.plates.add(this);
		Update();
	}

	public void Update() {
		relocate(baseX-(0.5*(i*20.0)),(baseY-10.0)-(thisStick.plates.size()*10.0));
	}

}