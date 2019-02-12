import javafx.animation.AnimationTimer;

class SimulationTimer extends AnimationTimer {
	 final int msec;
	private SortOf sortOf = null;
	 long lastUpdate = 0;
	 long now;

	public SimulationTimer(SortOf app, int millisekunden) {
		msec = millisekunden;
		sortOf = app;
	}

	@Override
	public void handle(long now) {
		this.now = now;
		if ((now - lastUpdate) > (msec * 1000000)) {
			sortOf.stuff();
			lastUpdate = now;

		}

	}
}
