
import javafx.animation.AnimationTimer;

public class SimulationTimer extends AnimationTimer {
	SortOf sortOf = null;
	int msec;
	long lastUpdate = 0;

	public SimulationTimer(SortOf app, int millisekunden) {
		msec = millisekunden;
		sortOf = app;
	}

	@Override
	public void handle(long now) {
		if ((now - lastUpdate) > (msec * 1000000)) {
			sortOf.stuff();
			lastUpdate = now;
		}
	}
}
