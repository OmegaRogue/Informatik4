import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.misc.Version;

@SuppressWarnings("Duplicates")
public class SortOf extends Application {

	public int[] inputInts = {8, 7, 6, 5, 4, 3, 2, 1};

	public int[] intsOne = inputInts;
	public int[] intsTwo = new int[inputInts.length];
	public int[] intsThree = new int[inputInts.length];

	public int[] from = {
			0, 0, 2, 0, 1, 1, 0, 0, 2, 2, 1, 2,
			0, 0, 2, 0, 1, 1, 0, /**/0, 2, 2, 1, 2
	};
	public int[] to =   {
			2, 1, 1, 2, 0, 2, 2, 1, 1, 0, 0, 1,
			2, 1, 1, 2, 0, 2, 2, /**/1, 1, 0, 0, 1
	};


	public Stick selectedOne;
	public Stick selectedTwo;


//	public Deque<Integer> intsOne = new ArrayDeque(inputInts.length);
//	public Deque<Integer> intsTwo = new ArrayDeque();
//	public Deque<Integer> intsThree = new ArrayDeque();

	public Stick stickOne = new Stick(200, 100);
	public Stick stickTwo = new Stick(400, 100);
	public Stick stickThree = new Stick(600, 100);
	public Stick stickDone = new Stick(0, 0);

	public Stick[] sticks = {stickOne, stickTwo, stickThree};


	public int time = 0;
	public Text timer = new Text(20, 20, String.valueOf(time));

	int iter = 0;


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {


		primaryStage.setTitle("TitledPane");
		Scene scene = new Scene(new Group(), 800, 100);

		intsToPlates(stickOne, inputInts);
		intsToPlates(stickDone, inputInts);


		EventHandler<MouseEvent> selectStick = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getSource().getClass() == Stick.class) {
					Stick stick = (Stick) e.getSource();
					selectick(stick);

				}


			}
		};

		EventHandler<KeyEvent> key = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.DIGIT1)
					selectick(stickOne);
				if(event.getCode()== KeyCode.DIGIT1)
					selectick(stickTwo);
				if(event.getCode()== KeyCode.DIGIT1)
					selectick(stickThree);
			}
		};



		Group root = (Group) scene.getRoot();

		root.getChildren().add(timer);
		root.getChildren().addAll(stickOne, stickTwo, stickThree);
		root.getChildren().addAll(stickOne.plates);
//		root.getChildren().addAll(stickTwo.plates);
//		root.getChildren().addAll(stickThree.plates);


		selectedOne = null;
		selectedTwo = null;

		stickOne.setOnMouseClicked(selectStick);
		stickTwo.setOnMouseClicked(selectStick);
		stickThree.setOnMouseClicked(selectStick);

		//root.setOnKeyPressed(key);
		//scene.setOnKeyPressed(key);

		startup(primaryStage, scene);


	}

	void selectick(Stick stick) {
		if (selectedOne == null) {
			selectedOne = stick;
			selectedOne.setFill(Color.BLUE);
		} else if (selectedTwo == null) {
			selectedTwo = stick;
			selectedTwo.setFill(Color.RED);
		}
		if (selectedOne != null && selectedTwo != null) {
			selectedOne.moveFirst(selectedTwo);
			selectedOne.setFill(Color.GREEN);
			selectedTwo.setFill(Color.GREEN);
			selectedOne = null;
			selectedTwo = null;
		}
	}

	private void startup(Stage stage, Scene scene) {
		scene.setFill(Color.GHOSTWHITE);
		stage.setScene(scene);
		stage.show();

		SimulationTimer simulationLoop = new SimulationTimer(this, 1000);
		simulationLoop.start();

	}

	public void stuff() {


			if (iter < from.length) {
				sticks[from[iter]].moveFirst(sticks[to[iter]]);
				iter++;
			} else {

			}
		if (stickThree.plates != stickDone.plates) {
			time += 1;
			timer.setText(String.valueOf(time));

		} else if (stickThree.plates == stickDone.plates) {

		}

	}

	public void intsToPlates(Stick stick, int[] ints) {
		for (int i = 0; i < ints.length; i++) {
			new Plate(ints[i], stick);

		}
	}
}
