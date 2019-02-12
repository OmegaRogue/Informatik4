import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

@SuppressWarnings("Duplicates")
public class SortOf extends Application {

	private final int[] inputInts = {8,7,6,5,4,3,2,1};
	private  ArrayList<Integer> from = new ArrayList();
	private ArrayList<Integer> to = new ArrayList();
	private final Stick stickOne = new Stick(200, 100);
	private final Stick stickTwo = new Stick(400, 100);
	private final Stick stickThree = new Stick(600, 100);
	private final Stick stickDone = new Stick(0, 0);
	private final Stick[] sticks = {stickOne, stickTwo, stickThree};


//	public Deque<Integer> intsOne = new ArrayDeque(inputInts.length);
//	public Deque<Integer> intsTwo = new ArrayDeque();
//	public Deque<Integer> intsThree = new ArrayDeque();
	public int[] intsOne = inputInts;
	public int[] intsTwo = new int[inputInts.length];
	public int[] intsThree = new int[inputInts.length];
	private Stick selectedOne;
	private Stick selectedTwo;
	private int time = 0;
	private final Text timer = new Text(20, 20, String.valueOf(time));

	private int iter = 0;



	boolean paused;

	Scanner keyIn;

	Stage staged;



	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {


		keyIn = new Scanner(System.in);
		primaryStage.setTitle("Türme von Hanoi");
		Scene scene = new Scene(new Group(), 800, 100);

		intsToPlates(stickOne, inputInts);
		intsToPlates(stickDone, inputInts);


		EventHandler<MouseEvent> selectStick = e -> {
			if (e.getSource().getClass() == Stick.class) {
				Stick stick = (Stick) e.getSource();
				selectick(stick);

			}


		};

		EventHandler<KeyEvent> key = event -> {
			if (event.getCode() == KeyCode.ENTER)
			{

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
		scene.setOnKeyPressed(key);


		startup(primaryStage, scene);


	}

	private void selectick(Stick stick) {
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
		staged = stage;
		scene.setFill(Color.GHOSTWHITE);
		stage.setScene(scene);
		stage.setTitle("Türme von Hanoi");

		stage.getIcons().add(new Image(getClass().getResourceAsStream("HanoiIcon.png")));
		stage.show();



		SimulationTimer simulationLoop = new SimulationTimer(this,1000);
		move(sticks[0].plates.size(),0,1,2);
		simulationLoop.start();
	}

	public void stuff() {
		if(iter<from.size()){
			sticks[from.get(iter)].moveFirst(sticks[to.get(iter)]);
			iter++;

		}
		if (stickThree.plates != stickDone.plates) {
			time += 1;
			timer.setText(String.valueOf(time));

		}








	}

	private void intsToPlates(Stick stick, int[] ints) {
		for (int anInt : ints) {
			new Plate(anInt, stick);

		}
	}

	void movePlateFromTo(int from, int to) {
		sticks[from].moveFirst(sticks[to]);
	}
	void startWithArray(int[] ints) {

	}

	void move(int i, int a, int b, int c) {

			if(i>0) {

				move(i-1,a,c,b);
				from.add(a);
				to.add(c);
				move(i-1,b,a,c);


			}



	}

	int[] arrayWithInts(int n) {
		int[] ints = new int[n];
		for (int i = n-1; i == 0; i--) {
			ints[i] = i+1;
		}
		return ints;
	}
}
