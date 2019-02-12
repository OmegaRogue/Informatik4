import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Stick extends Rectangle implements Keller {

	public final double baseX;
	public final double baseY;
	public final List<Plate> plates = new ArrayList();


	public Stick(double x, double y) {
		super(x, y - 100, 10, 100);
		this.baseX = x + 0.5 * 10;
		this.baseY = y;
		this.setFill(Color.GREEN);
	}

	public Plate poll() {
		return plates.get(plates.size() - 1);
	}

	public Plate pull() {
		Plate plateBuffer = poll();
		plates.remove(plateBuffer);
		return plateBuffer;
	}

	public void moveFirst(Stick to) {
		pull().move(to);
	}

	@Override
	public boolean isEmpty() {
		return plates.isEmpty();
	}

	@Override
	public void push(int i) {
		new Plate(i, this);
	}


}
