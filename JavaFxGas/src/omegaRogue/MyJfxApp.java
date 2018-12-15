package omegaRogue;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Übungsapplikation zur objektorientierten Programmierung mit JavaFX.
 *
 * @author OmegaRogue
 * <p>
 * Das kennst du schon:
 * - Variablen und elementare Datentypen
 * - Gültigkeitsbereiche von Variablen
 * - Arrays
 * - Listen
 * - Bäume/Graphen
 * - die OOP-Grundbegriffe Klasse, Objekt, Attribut, Methode, Vererbung und ihre
 * Realisierung in Java
 * @version 2
 */
public class MyJfxApp extends Application {
	public HBox toolBar;
	public VBox navBar;
	public GridPane viewWindow;
	Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	DecimalFormat df;
	Random rand = new Random();

	Ball[] Baelle = new Ball[500];

	SimulationTimer simulationLoop;

	Iterator objItr;

	Slider tempControl;

	Block[] Bloecke = new Block[1];


	Text ballCount;
	Text tempDisplay;

	Text ballCountDisplay;


	ColorPicker colorPicker;
	ColorPicker velDisplay;

	Selector selector = Selector.getInstance();

	Group root = new Group();

	Scene scene = new Scene(createBallGasPane(), primaryScreenBounds.getWidth() - 100, primaryScreenBounds.getHeight() - 100);
	Scene topScene;
	Scene leftScene;
	Scene rightScene;


	Stage top;
	Stage left;
	Stage right;

	/*
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {

		left = new Stage();
		top = new Stage();
		right = new Stage();
		df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);

		EventHandler<MouseEvent> select = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getSource().getClass() == Ball.class) {
					Ball balll = (Ball) e.getSource();
					selector.attach(balll);
				}


			}
		};


		for (int i = 0; i < Baelle.length; i++) {
			Baelle[i] = new Ball(scene,
					ThreadLocalRandom.current().nextDouble(
							15,
							scene.getWidth()),
					ThreadLocalRandom.current().nextDouble(
							15,
							scene.getHeight()),
					5,
					Color.GREEN);

			Baelle[i].velocity = new Point2D(
					ThreadLocalRandom.current().nextDouble(-10, 10),
					ThreadLocalRandom.current().nextDouble(-10, 10)
			);
			Baelle[i].setOnMouseClicked(select);
			//root.getChildren().add(Baelle[i].nr);

		}

		//Bloecke[1] = new Block(borderp.getCenter().getLayoutX(),borderp.getCenter().getLayoutY(),scene.getWidth(),scene.getHeight(),Color.GREEN);

		for (int i = 0; i < Bloecke.length; i++) {
			if (Bloecke[i] == null) {
				Bloecke[i] = new Block(300, 200, 200, 100, Color.RED);
			}
			root.getChildren().addAll(Bloecke[i].sides);
		}


		ballCount = new Text("Anzahl Baelle:\n" + Ball.ballCount);


		tempControl = new Slider(0.1, 10, 1);
		tempControl.setBlockIncrement(0.1);
		tempControl.setMajorTickUnit(5);

		tempDisplay = new Text();

		changeTempDisplay();


		root.getChildren().addAll(Baelle);
		root.getChildren().addAll(Bloecke);
		root.getChildren().add(selector);


		toolBar = (HBox) createToolbarPane();

		navBar = (VBox) createNavigationPane();
		viewWindow = (GridPane) createDisplayPane();

		topScene = new Scene(toolBar, primaryScreenBounds.getWidth(), (primaryScreenBounds.getHeight() / 21.6));
		topScene.getStylesheets().add(String.valueOf(MyJfxApp.class.getResource("Styles.css")));

		leftScene = new Scene(navBar, (primaryScreenBounds.getWidth() / 25.6), primaryScreenBounds.getHeight() - topScene.getHeight());
		leftScene.getStylesheets().add(String.valueOf(MyJfxApp.class.getResource("Styles.css")));

		rightScene = new Scene(viewWindow, 200, primaryScreenBounds.getHeight() - topScene.getHeight());
		rightScene.getStylesheets().add(String.valueOf(MyJfxApp.class.getResource("Styles.css")));

		scene.getStylesheets().add(String.valueOf(MyJfxApp.class.getResource("Styles.css")));

		stage.setTitle("My JavaFX Application");
		stage.setScene(scene);
		stage.setX(primaryScreenBounds.getMinX() + leftScene.getWidth());
		stage.setY(primaryScreenBounds.getMinY() + topScene.getHeight());
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setHeight(primaryScreenBounds.getHeight() - topScene.getHeight());
		stage.setWidth(primaryScreenBounds.getWidth() - leftScene.getWidth() - rightScene.getWidth());


		//left.setTitle("Nav");
		left.setScene(leftScene);
		left.setX(primaryScreenBounds.getMinX());
		left.setY(primaryScreenBounds.getMinY() + topScene.getHeight());
		left.initStyle(StageStyle.UNDECORATED);


		top.setTitle("Display");
		top.setScene(topScene);
		top.setX(primaryScreenBounds.getMinX());
		top.setY(primaryScreenBounds.getMinY());
		top.initStyle(StageStyle.UNDECORATED);

		right.setScene(rightScene);
		right.setX(primaryScreenBounds.getMinX() + leftScene.getWidth() + stage.getWidth());
		right.setY(primaryScreenBounds.getMinY() + topScene.getHeight());
		right.initStyle(StageStyle.UNDECORATED);


		top.show();
		left.show();
		right.show();

		stage.show();
		objItr = root.getChildren().listIterator();


		startSimulation(scene);


	}

	private void changeTempDisplay() {
		tempDisplay.setText("Temperatur:" + df.format(tempControl.getValue()) + "K");
	}

	private void startSimulation(Scene scene) {
		simulationLoop = new SimulationTimer(this, 5);
		simulationLoop.start();
		while (objItr.hasNext()) {
			Object element = objItr.next();
			if (element instanceof Behaviour) {
				((Behaviour) element).Start();
			}

		}
		selector.attach(Baelle[1]);


	}

	/**
	 * Diese Methode wird vom Simulationstimer immer wieder aufgerufen.
	 */
	public void updateSimulation() {
		objItr = root.getChildren().listIterator();
		while (objItr.hasNext()) {
			Object element = objItr.next();
			if (element instanceof Behaviour) {
				((Behaviour) element).Update();
				if (element.getClass() == Ball.class) {
					checkBounds((Ball) element);
					((Ball) element).setVelMod((long) tempControl.getValue());
				}

			}
		}

		changeTempDisplay();
		selector.Update();
		Ball selected = (Ball) selector.getAttached();
		selected.setColor(colorPicker.getValue());
		ballCountDisplay.setText("Selected Ball Nr. " + selected.nr.getText());


	}

	private void checkBounds(Ball ball) {

		if (ball.position.getX() < Ball.scene.getX() || ball.position.getX() + ball.getRadius() > Ball.scene.getWidth()) {
			ball.reflect(EnumDirection.LEFT);
		}


		if (ball.position.getY() < Ball.scene.getY() || ball.position.getY() + ball.getRadius() > Ball.scene.getHeight()) {
			ball.reflect(EnumDirection.UP);
		}
		for (Block block : Bloecke) {
			ball.reflect(block.collideAt(ball));
		}
		for (Ball static_ball : Baelle)
			if (static_ball != ball) {
				if (ball.collideWith(static_ball)) {
					ball.reflect(static_ball);
				}
			}


	}


	private Pane createToolbarPane() {
		final HBox hbox = new HBox(5);

		hbox.getStyleClass().add("leftBox");
		hbox.getChildren().addAll(ballCount, new VBox(tempDisplay, tempControl));

		return hbox;
	}

	private Pane createDisplayPane() {
		final GridPane gridPane = new GridPane();
		colorPicker = new ColorPicker(Color.GREEN);
		velDisplay = new ColorPicker(Color.RED);
		ballCountDisplay = new Text("");
		Selector.getInstance().colorPicker = colorPicker;
		gridPane.add(ballCountDisplay, 0, 0);
		gridPane.add(colorPicker, 0, 1);
		gridPane.add(velDisplay, 0, 2);
		gridPane.getStyleClass().add("rightBox");


		return gridPane;
	}

	private Pane createNavigationPane() {
		final VBox vbox = new VBox(5);

		vbox.getStyleClass().add("topBox");
		vbox.getChildren().addAll(new Text("LEFT"), new Button("Vbox1"), new Button("Vbox2"));

		return vbox;
	}

	private Pane createBallGasPane() {
		final Pane pane = new Pane();
		pane.getChildren().add(root);
		pane.getStyleClass().add("centerBox");


		return pane;
	}

}
