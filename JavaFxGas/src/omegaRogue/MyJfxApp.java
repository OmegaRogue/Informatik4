package omegaRogue;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.ClassLoaderUtil;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
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
	static Text ballCount;
	public URL style = ClassLoaderUtil.getResource("Stylesheets/Styles.css", this.getClass());
	Random rand = new Random();
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	static Text tempDisplay;
	private Ball[] Baelle = new Ball[500];
	private Iterator objItr;
	static Text ballCountDisplay = new Text("");
	static ColorPicker colorPicker;
	static Group root = new Group();
	static Ball selected;
	private static DecimalFormat df;
	private Selector selector = Selector.getInstance();
	public URL mainWindow = ClassLoaderUtil.getResource("omegaRogue/MainWindow.fxml", this.getClass());
	//private static Slider tempControl;
	private Block[] Bloecke = new Block[1];
	private Parent mainWin;
	private Scene topScene;
	private Scene leftScene;
	private Scene rightScene;
	private Stage top;
	private Stage left;
	private Stage right;
	private Ball ball;
	private Scene mainScene;


	/*
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public static void changeTempDisplay(double temp) {
		tempDisplay.setText("Temperatur:" + df.format(temp) + "K");
		Ball.setVelMod(temp);
	}

	public void start(Stage stage) {

		MainWindowController controller = new MainWindowController();

		FXMLLoader loader = new FXMLLoader();
		loader.setController(controller);
		colorPicker = new ColorPicker(Color.GREEN);
		try {
			mainWin = FXMLLoader.load(ClassLoaderUtil.getResource("omegaRogue/MainWindow.fxml", this.getClass()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainScene = new Scene(mainWin, primaryScreenBounds.getWidth() - 100, primaryScreenBounds.getHeight() - 100);
		left = new Stage();
		top = new Stage();
		right = new Stage();
		df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);

		EventHandler<MouseEvent> select = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getSource().getClass() == Ball.class) {
					Ball ball = (Ball) e.getSource();
					selector.attach(ball);
				}


			}
		};

		for (int i = 0; i < Baelle.length; i++) {
//			Baelle[i] = new Ball(
//					ThreadLocalRandom.current().nextDouble(
//							Ball.parent.getLayoutX(),
//							Ball.parent.getLayoutX() + Ball.parent.getWidth()+1),
//					ThreadLocalRandom.current().nextDouble(
//							Ball.parent.getLayoutY(),
//							Ball.parent.getLayoutY() + Ball.parent.getHeight()+1),
//					5,
//					Color.GREEN);
			Baelle[i] = new Ball(0, 0, 5, Color.GREEN);

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


//		tempControl = new Slider(0.1, 10, 1);
//		tempControl.setBlockIncrement(0.1);
//		tempControl.setMajorTickUnit(5);

		tempDisplay = new Text();
		changeTempDisplay();



		root.getChildren().addAll(Baelle);
		root.getChildren().addAll(Bloecke);
		root.getChildren().add(selector);


		toolBar = (HBox) createToolbarPane();

		navBar = (VBox) createNavigationPane();
		viewWindow = new DisplayPane();


		topScene = new Scene(toolBar);
		topScene.getStylesheets().add(String.valueOf(style));


		leftScene = new Scene(navBar);
		leftScene.getStylesheets().add(String.valueOf(style));

		rightScene = new Scene(viewWindow);
		rightScene.getStylesheets().add(String.valueOf(style));


		stage.setTitle("My JavaFX Application");
		stage.setScene(mainScene);

//		stage.setHeight(primaryScreenBounds.getHeight() - topScene.getHeight());
//		stage.setWidth(primaryScreenBounds.getWidth() - leftScene.getWidth() - rightScene.getWidth());


		left.setTitle("Nav");
		left.setScene(leftScene);



		top.setTitle("Display");
		top.setScene(topScene);


		right.setScene(rightScene);


		//top.show();
		//left.show();
		right.show();

		stage.show();
		objItr = root.getChildren().listIterator();


		startSimulation();


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



	}

	/**
	 * Diese Methode wird vom Simulationstimer immer wieder aufgerufen.
	 */
	void updateSimulation() {
		if (selected == null) {
			selector.attach(Baelle[0]);
		}


		objItr = root.getChildren().listIterator();
		while (objItr.hasNext()) {
			Object element = objItr.next();
			if (element instanceof Behaviour) {
				((Behaviour) element).Update();
				if (element.getClass() == Ball.class) {
					checkBounds((Ball) element);

				}

			}
		}

		changeTempDisplay();
		selector.Update();
		selected = (Ball) selector.getAttached();
		selected.setColor(colorPicker.getValue());
		ballCountDisplay.setText("Selected Ball Nr. " + selected.nr.getText());
		ballCountDisplay.setTextAlignment(TextAlignment.CENTER);
		VelChart.Update(selected.velocity);


	}

	private void checkBounds(Ball ball) {
		this.ball = ball;

		if (ball.position.getX() < Ball.parent.getLayoutX() || ball.position.getX() + ball.getRadius() > Ball.parent.getWidth()) {
			ball.reflect(EnumDirection.LEFT);
		}


		if (ball.position.getY() < Ball.parent.getLayoutY() || ball.position.getY() + ball.getRadius() > Ball.parent.getHeight()) {
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
		//hbox.getChildren().addAll(ballCount, new VBox(tempDisplay, tempControl));

		return hbox;
	}



	private Pane createNavigationPane() {
		final VBox vbox = new VBox(5);

		vbox.getStyleClass().add("topBox");
		vbox.getChildren().addAll(new Text("LEFT"), new Button("Vbox1"), new Button("Vbox2"));

		return vbox;
	}





}
