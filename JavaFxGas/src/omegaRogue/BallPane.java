package omegaRogue;

import javafx.scene.layout.Pane;

public class BallPane extends Pane {
	public BallPane() {
		Ball.parent = this;
		getChildren().add(MyJfxApp.root);
		getStyleClass().add("centerBox");

	}
}
