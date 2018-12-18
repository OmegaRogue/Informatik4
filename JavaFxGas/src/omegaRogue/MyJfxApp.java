package omegaRogue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.ClassLoaderUtil;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	public URL mainWindow = ClassLoaderUtil.getResource("GUI/MainWindow.fxml", this.getClass());
	public URL style = ClassLoaderUtil.getResource("Stylesheets/Styles.css", this.getClass());
	Random rand = new Random();
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	private DecimalFormat df;
	public static final double boltzmann = 1.381 * Math.pow(10, -23);
	private Iterator objItr;
	private Slider tempControl;
	private Block[] Bloecke = new Block[1];
	private Text ballCount;
	private Text tempDisplay;
	private Text ballCountDisplay;
	private LineChart<Number, Number> velChart = createVelChart();
	private ColorPicker colorPicker;
	private Selector selector = Selector.getInstance();
	private Group root = new Group();
	private Scene scene = new Scene(createBallGasPane(), primaryScreenBounds.getWidth() - 100, primaryScreenBounds.getHeight() - 100);
	private Scene topScene;
	private Scene leftScene;
	private Scene rightScene;
	private Stage top;
	private Stage left;
	private Stage right;
	private Ball ball;
	public static final double atomicMassUnit = 1.66 * Math.pow(10, -27);
	public static final double u = atomicMassUnit;
	public static final double baseTemp = 298.15;
	private List<Ball> Baelle = new ArrayList();


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


		for (int i = 0; i < 500; i++) {
			Baelle.add(
					new Ball(scene,
					ThreadLocalRandom.current().nextDouble(
							scene.getX(),
							scene.getWidth()),
					ThreadLocalRandom.current().nextDouble(
							scene.getY(),
							scene.getHeight()),
					5,
							Color.GREEN));

			Baelle.get(i).baseVelocity = new Point2D(
					ThreadLocalRandom.current().nextDouble(-10, 10),
					ThreadLocalRandom.current().nextDouble(-10, 10)
			);
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


		tempControl = new Slider(0, 1000, baseTemp);
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


		topScene = new Scene(toolBar);
		topScene.getStylesheets().add(String.valueOf(style));


		leftScene = new Scene(navBar);
		leftScene.getStylesheets().add(String.valueOf(style));

		rightScene = new Scene(viewWindow);
		rightScene.getStylesheets().add(String.valueOf(style));

		scene.getStylesheets().add(String.valueOf(style));

		stage.setTitle("My JavaFX Application");
		stage.setScene(scene);

		stage.setHeight(primaryScreenBounds.getHeight() - topScene.getHeight());
		stage.setWidth(primaryScreenBounds.getWidth() - leftScene.getWidth() - rightScene.getWidth());


		left.setTitle("Nav");
		left.setScene(leftScene);



		top.setTitle("Display");
		top.setScene(topScene);


		right.setScene(rightScene);



		top.show();
		left.show();
		right.show();

		stage.show();
		objItr = root.getChildren().listIterator();


		startSimulation();


	}

	private void changeTempDisplay() {
		tempDisplay.setText("Temperatur:" + df.format(tempControl.getValue()) + "K");
	}

	private void startSimulation() {
		SimulationTimer simulationLoop = new SimulationTimer(this, 5);
		simulationLoop.start();
		while (objItr.hasNext()) {
			Object element = objItr.next();
			if (element instanceof Behaviour) {
				((Behaviour) element).Start();
			}

		}
		selector.attach(Baelle.get(1));


	}

	/**
	 * Diese Methode wird vom Simulationstimer immer wieder aufgerufen.
	 */
	void updateSimulation() {
		objItr = root.getChildren().listIterator();
		while (objItr.hasNext()) {
			Object element = objItr.next();
			if (element instanceof Behaviour) {
				((Behaviour) element).Update();
				if (element.getClass() == Ball.class) {
					checkBounds((Ball) element);
					((Ball) element).setTemperature(tempControl.getValue());
				}

			}
		}

		changeTempDisplay();
		selector.Update();
		Ball selected = (Ball) selector.getAttached();
		selected.setColor(colorPicker.getValue());
		ballCountDisplay.setText("Selected Ball Nr. " + selected.nr.getText());
		updateVelChart(selected.baseVelocity);
		ballCount.setText("Anzahl Baelle:\n" + Ball.ballCount);


	}

	private void checkBounds(Ball ball) {
		this.ball = ball;

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
		ballCountDisplay = new Text("");
		Selector.getInstance().colorPicker = colorPicker;
		gridPane.add(ballCountDisplay, 0, 0);
		gridPane.add(colorPicker, 0, 1);
		gridPane.add(velChart, 0, 2);
		gridPane.getStyleClass().add("rightBox");


		return gridPane;
	}

	private Pane createNavigationPane() {
		final VBox vbox = new VBox(5);
		Button addBalls = new Button("Add 10 Balls");
		vbox.getStyleClass().add("topBox");
		vbox.getChildren().addAll(new Text("LEFT"), addBalls, new Button("Vbox2"));
		EventHandler<ActionEvent> addTen = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {


				List<Ball> newBaelle = new ArrayList();
				for (int i = 0; i < 10; i++) {
					newBaelle.add(new Ball(scene,
							ThreadLocalRandom.current().nextDouble(
									scene.getX(),
									scene.getWidth()),
							ThreadLocalRandom.current().nextDouble(
									scene.getY(),
									scene.getHeight()),
							5,
							Color.GREEN));
				}
				root.getChildren().addAll(newBaelle);
				Baelle.addAll(newBaelle);
			}

		};
		addBalls.setOnAction(addTen);



		return vbox;
	}

	private Pane createBallGasPane() {
		final Pane pane = new Pane();
		pane.getChildren().add(root);
		pane.getStyleClass().add("centerBox");


		return pane;
	}


	private LineChart<Number, Number> createVelChart() {
		final NumberAxis xAxis = new NumberAxis("X", -10, 10, 1);
		final NumberAxis yAxis = new NumberAxis("Y", -10, 10, 1);
		XYChart.Data zero = new XYChart.Data(0, 0);
		XYChart.Data velocity = new XYChart.Data(1, 1);
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		XYChart.Series series = new XYChart.Series();
		series.getData().add(zero);
		series.getData().add(velocity);
		lineChart.getData().add(series);
		lineChart.autosize();
		lineChart.setAnimated(true);
		return lineChart;

	}

	private void updateVelChart(Point2D velocity) {

		XYChart.Series series = new XYChart.Series();
		series.getData().add(new XYChart.Data(0, 0));
		series.getData().add(new XYChart.Data(velocity.getX(), velocity.getY()));
		velChart.getData().clear();
		velChart.getData().add(series);

	}


}
