package omegaRogue;

import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

import static omegaRogue.MyJfxApp.ballCountDisplay;
import static omegaRogue.MyJfxApp.colorPicker;
import static omegaRogue.VelChart.velChart;

public class DisplayPane extends GridPane {
	public DisplayPane() {

		super();
		add(ballCountDisplay, 0, 0);
		add(colorPicker, 0, 1);
		add(velChart, 0, 2);
		getStyleClass().add("rightBox");
		ballCountDisplay.setTextAlignment(TextAlignment.CENTER);
	}
}
