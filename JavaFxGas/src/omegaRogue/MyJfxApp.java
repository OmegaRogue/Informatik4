package omegaRogue;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.lang.reflect.*;

/**
 * Übungsapplikation zur objektorientierten Programmierung mit JavaFX.
 * 
 * @version 2
 * @author OmegaRogue
 * 
 * Das kennst du schon:
 * - Variablen und elementare Datentypen
 * - Gültigkeitsbereiche von Variablen
 * - Arrays
 * - Listen
 * - Bäume/Graphen
 * - die OOP-Grundbegriffe Klasse, Objekt, Attribut, Methode, Vererbung und ihre 
 *   Realisierung in Java
 *
 */
public class MyJfxApp extends Application
{
	Group root = new Group();
	Scene scene = new Scene(root, 600, 400);
	Random rand = new Random();
	Ball[] Baelle = new Ball[500];
	SimulationTimer simulationLoop;
	Iterator objItr;
	Slider tempControl;
	Block[] Bloecke = new Block[1];
   // Line[] Bounds = new Line[4];


	public void start(Stage stage)
	{
		for (int i = 0; i < Baelle.length; i++) {
			Baelle[i] = new Ball(stage,
					ThreadLocalRandom.current().nextDouble(
							15,
							scene.getWidth()),
					ThreadLocalRandom.current().nextDouble(
							15,
							scene.getHeight()),
					5,
					Color.GREEN);

			Baelle[i].velocity = new Point2D(
					ThreadLocalRandom.current().nextDouble(-10,10),
					ThreadLocalRandom.current().nextDouble(-10,10)
			);
		}
		for(int i = 0; i <Bloecke.length; i++) {
		    Bloecke[i] = new Block(30,30,20,10,Color.RED);
        }
		tempControl = new Slider(1,100,1);
		tempControl.setBlockIncrement(1);
        tempControl.setMajorTickUnit(1);
        root.getChildren().add(tempControl);
		root.getChildren().addAll(Baelle);
		root.getChildren().addAll(Bloecke);
     //   root.getChildren().addAll(Bounds);
		stage.setTitle("My JavaFX Application");
		stage.setScene(scene);

		stage.show();
        objItr = root.getChildren().listIterator();

            

		startSimulation(scene);

	}

	private void startSimulation(Scene scene)
	{
		simulationLoop = new SimulationTimer(this,5);
		simulationLoop.start();
		while(objItr.hasNext()) {
			Object element = objItr.next();
			if (element instanceof Behaviour) {
				((Behaviour) element).Start();
			}
		}

	}

	/**
	 * Diese Methode wird vom Simulationstimer immer wieder aufgerufen. 
	 */
	public void updateSimulation()
	{
//		objItr = root.getChildren().listIterator();
//		while(objItr.hasNext()) {
//			Object element = objItr.next();
//			if (element instanceof Behaviour) {
//				((Behaviour) element).Update();
//				if(element.getClass() == Ball.class) {
//                    checkBounds((Ball) element);
//                    ((Ball)element).setVelMod((long)tempControl.getValue());
//                }
//
//			}
//		}
		for (int i = 0; i < Baelle.length; i++) {
			checkBounds(Baelle[i]);
			Baelle[i].Update();
            Baelle[i].setVelMod((long)tempControl.getValue());

		}
	}

	/*
	 * @param args the command line arguments
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}

    private void checkBounds(Ball ball) {
		if (ball.position.getX()<0 || ball.position.getX()+ball.getRadius()>ball.stage.getWidth())
		{
			ball.reflectX();
		}


		if (ball.position.getY()<0 || ball.position.getY()+ball.getRadius()>ball.stage.getHeight())
		{
			ball.reflectY();
		}
        for (Ball static_ball : Baelle)
            if (static_ball != ball) {
                if (ball.collideWith(static_ball)) {
					Point2D temp = ball.velocity;
                    ball.velocity = static_ball.velocity;
                    static_ball.velocity = temp;
                }
            }


    }
}
