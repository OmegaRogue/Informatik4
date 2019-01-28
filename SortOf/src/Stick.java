import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Stick extends Rectangle {

	public double baseX;
	public double baseY;
	public List<Plate> plates = new ArrayList();


	public Stick(double x, double y) {
		super(x,y-100,10,100);
		this.baseX = x+0.5*10;
		this.baseY = y;
		this.setFill(Color.GREEN);
	}

	public Plate pollFirst() {
		return plates.get(plates.size()-1);
	}
	public Plate pullFirst() {
		Plate plateBuffer = pollFirst();
		plates.remove(plateBuffer);
		return plateBuffer;
	}

	public void moveFirst(Stick to) {
		pullFirst().move(to);
	}
}
