package omegaRogue;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	DoubleStream randX;
	DoubleStream randY;

	public void start(Stage stage)
	{
		for (int i = 0; i < Baelle.length; i++) {
			Baelle[i] = new Ball(
					ThreadLocalRandom.current().nextDouble(
							15, scene.getWidth()),
					ThreadLocalRandom.current().nextDouble(15, scene.getHeight()),
					15,
					Color.RED);
			Baelle[i].vx = rand.nextDouble()-rand.nextDouble();
			Baelle[i].vy = rand.nextDouble()-rand.nextDouble();
		}
		root.getChildren().addAll(Baelle);

		stage.setTitle("My JavaFX Application");
		stage.setScene(scene);
		stage.show();

		startSimulation();
	}

	private void startSimulation() 
	{
		simulationLoop = new SimulationTimer(this,5); 
		simulationLoop.start();
	}

	/**
	 * Diese Methode wird vom Simulationstimer immer wieder aufgerufen. 
	 */
	public void updateSimulation()
	{
		for (int i = 0; i < Baelle.length; i++) {
			Baelle[i].updatePosition();
		}
	}

	/*
	 * @param args the command line arguments
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}

}
