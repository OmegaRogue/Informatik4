package omegaRogue;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.stage.WindowEvent;
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

	public static double velMod;

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
	public static ObservableList<Point2D> BallVel = FXCollections.observableList(new ArrayList());
	double v;
	double V;
	private Text ballCount;
	private Text tempDisplay;
	private Text ballCountDisplay;
	double p;

	private LineChart<Number, Number> velChart = createVelChart();
	private ColorPicker colorPicker;
	private Selector selector = Selector.getInstance();
	private Group root = new Group();
	double T = baseTemp;
	private Scene topScene;
	private Scene leftScene;
	private Scene rightScene;
	private Stage top;
	private Stage left;
	private Stage right;
	int N = Ball.ballCount;
	private Ball ball;
	public static final double atomicMassUnit = 1.66 * Math.pow(10, -27);
	public static final double u = atomicMassUnit;
	public static final double baseTemp = 298.15;
	double m = Ball.baseMass;
	private Slider velControl;
	private Text velDisplay = new Text();
	private Block[] Bloecke = new Block[0];
	private Text pressure;
	private Scene scene = new Scene(createBallGasPane(), primaryScreenBounds.getWidth() - 200, primaryScreenBounds.getHeight() - 200);
	private Stage center;
	private ObservableList<Ball> Baelle = FXCollections.observableList(new ArrayList());


	/*
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public static double calcTemp(double m, double v) {
		return m * v * v / (3 * boltzmann);
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
					ThreadLocalRandom.current().nextDouble(-50, 50),
					ThreadLocalRandom.current().nextDouble(-50, 50)
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


		ballCount = new Text("Anzahl Baelle: " + N);


		velControl = new Slider(0, 10, 1);
		velControl.setBlockIncrement(0.0001);
		velControl.setMajorTickUnit(5);

		tempDisplay = new Text();


		p = getp();
		pressure = new Text("Pressure: " + df.format(getp()) + "Pa");

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


		//top.show();
		left.show();
		right.show();

		stage.show();
		center = stage;
		objItr = root.getChildren().listIterator();

		EventHandler<WindowEvent> closeHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				top.close();
				left.close();
				right.close();
				center.close();
			}
		};


		Baelle.addListener(new ListChangeListener<Ball>() {
			@Override
			public void onChanged(Change<? extends Ball> c) {
				N = Baelle.size();
				ballCount.setText("Anzahl Baelle: " + N);
			}
		});
		velControl.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				velMod = newValue.doubleValue();
				changeTempDisplay();
				Ball.velMod = velMod;
			}
		});


		top.setOnCloseRequest(closeHandler);
		left.setOnCloseRequest(closeHandler);
		right.setOnCloseRequest(closeHandler);
		center.setOnCloseRequest(closeHandler);

		startSimulation();


	}

	private void changePressureDisplay() {
		p = getp();
		pressure.setText("Pressure: " + getp() + "Pa");
	}

	private void changeTempDisplay() {
		tempDisplay.setText("Temperatur:" + df.format(T) + "K");

	}

	private void startSimulation() {

		N = Baelle.size();
		ballCount.setText("Anzahl Baelle: " + N);
		SimulationTimer simulationLoop = new SimulationTimer(this, 5);
		simulationLoop.start();
		Ball.baseMass = 16 * u;
		while (objItr.hasNext()) {
			Object element = objItr.next();
			if (element instanceof Behaviour) {
				((Behaviour) element).Start();
			}

		}
		selector.attach(Baelle.get(1));

		changeTempDisplay();
		changePressureDisplay();
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
					BallVel.add(((Ball) element).velocity);
				}

			}
		}
		changePressureDisplay();
		changeTempDisplay();
		selector.Update();
		Ball selected = (Ball) selector.getAttached();
		selected.setColor(colorPicker.getValue());
		ballCountDisplay.setText("Selected Ball Nr. " + selected.nr.getText());
		updateVelChart(selected.normVelocity);
		p = getp();

		V = getV();
		v = average(BallVel);
		T = calcTemp(Ball.baseMass, v);




	}

	private void checkBounds(Ball ball) {
		this.ball = ball;

		if (ball.position.getX() + ball.velocity.getX() < Ball.scene.getX() || ball.position.getX() + ball.getRadius() + ball.velocity.getX() > Ball.scene.getWidth()) {
			ball.reflect(EnumDirection.LEFT);
		}


		if (ball.position.getY() + ball.velocity.getY() < Ball.scene.getY() || ball.position.getY() + ball.velocity.getY() + ball.getRadius() > Ball.scene.getHeight()) {
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

	private Pane createToolbarPane() {
		final HBox hbox = new HBox(5);

		hbox.getStyleClass().add("leftBox");
		hbox.getChildren().addAll(ballCount, new VBox(new Text("Velocity"), velControl));

		return hbox;
	}

	private Pane createBallGasPane() {
		final Pane pane = new Pane();
		pane.getChildren().add(root);
		pane.getStyleClass().add("centerBox");


		return pane;
	}

	private Pane createNavigationPane() {
		final VBox vbox = new VBox(5);
		Button addBalls = new Button("Add 10 Balls");
		Button remBalls = new Button("Remove 10 Balls");
		vbox.getStyleClass().add("topBox");
		vbox.getChildren().addAll(tempDisplay, pressure, ballCount, new Text("Velocity"), velControl, addBalls, remBalls);
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
					newBaelle.get(i).baseVelocity = new Point2D(
							ThreadLocalRandom.current().nextDouble(-50, 50),
							ThreadLocalRandom.current().nextDouble(-50, 50)
					);
				}
				root.getChildren().addAll(newBaelle);
				Baelle.addAll(newBaelle);
			}

		};
		EventHandler<ActionEvent> remTen = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {


				for (int i = 0; i < 10; i++) {
					root.getChildren().remove(Baelle.get((Baelle.size() - 1) - i));
					Baelle.remove((Baelle.size() - 1) - i);
				}
			}
		};
		addBalls.setOnAction(addTen);
		remBalls.setOnAction(remTen);



		return vbox;
	}

	private void updateVelChart(Point2D velocity) {

		XYChart.Series series = new XYChart.Series();
		series.getData().add(new XYChart.Data(0, 0));
		series.getData().add(new XYChart.Data(velocity.getX(), velocity.getY()));
		velChart.getData().clear();
		velChart.getData().add(series);

	}

	private LineChart<Number, Number> createVelChart() {
		final NumberAxis xAxis = new NumberAxis("X", -1, 1, 1);
		final NumberAxis yAxis = new NumberAxis("Y", -1, 1, 1);
		XYChart.Data zero = new XYChart.Data(0, 0);
		XYChart.Data velocity = new XYChart.Data(1, 1);
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		XYChart.Series series = new XYChart.Series();
		series.getData().add(zero);
		series.getData().add(velocity);
		lineChart.getData().add(series);
		lineChart.autosize();
		lineChart.setAnimated(false);
		return lineChart;

	}

	public double getV() {
		return (Ball.scene.getWidth() * Ball.scene.getHeight()) / 1000;
	}

	public double getp() {
		return (1.0 / 3.0) * (N / V) * m * (v * v);
	}

	public double average(List<Point2D> points) {
		double a = 0;
		for (Point2D point :
				points) {
			a += point.magnitude();

		}
		return a / points.size();
	}

}
