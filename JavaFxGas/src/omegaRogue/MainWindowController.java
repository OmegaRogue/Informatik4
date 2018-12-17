package omegaRogue;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainWindowController {
	@FXML
	public Font x1;
	@FXML
	public Color x2;
	@FXML
	public Slider tempControl;
	@FXML
	public BallPane Balle;
	@FXML
	public Font x3;
	@FXML
	public Color x4;

	@FXML
	public void updateTemp(Event e) {
		MyJfxApp.changeTempDisplay();
	}
}
