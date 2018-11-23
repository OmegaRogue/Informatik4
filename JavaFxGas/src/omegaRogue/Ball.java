package omegaRogue;

import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.*;

/**
 * @version 1
 * @author OmegaRogue
 */
public class Ball extends Circle implements Behaviour
{
	double radius = 1;


	//Vector3 position;
	//Vector3 velocity;
	Point2D position;
	Point2D velocity;
	Color c= Color.BLUE;
	Stage stage;



	/**
	 * Konstruktor der Ballklasse. 
	 * @param bx x-Koordinate des Startpunktes.
	 * @param by y-Koordinate des Startpunktes.
	 * @param radius Radius des Balls.
	 * @param col Farbe des Balls.
	 */
	public Ball(Stage stage, double bx, double by, double vx, double vy, double radius, Color col)
	{
		super(bx,by,radius);
		//position = new Vector3(bx,by);
		//velocity = new Vector3(vx,vy);
		position = new Point2D(bx,by);
		velocity = new Point2D(vx,vy);
		this.radius = radius;
		c = col;
		setFill(col);
		this.stage = stage;
	}
	public Ball(Stage stage, double bx, double by, double radius, Color col)
	{
		super(bx,by,radius);
		//position = new Vector3(bx,by);
		//velocity = Vector3.Zero();
		position = new Point2D(bx,by);
		velocity = Point2D.ZERO;
		this.radius = radius;
		c = col;
		setFill(col);
		this.stage = stage;
	}
	/**
	 * Setzt die Farbe des Balls.
	 * @param farbe
	 */
	public void setColor(Color farbe)
	{
		c = farbe;
		setFill(farbe);
	}

	/**
	 * Aktualisiert die Ballposition entsprechend der Geschwindigkeitsattribute.
	 */
	public void updatePosition()
	{
		position.add(velocity);
		relocate(position.getX(),position.getY());



	}
	public void Start() {

	}
	public void Update() {
		updatePosition();
	}
	public void reflectX(){
		velocity.add(-(2*velocity.getX()),0);
	}
	public void reflectY() {
		velocity.add(0,-(2*velocity.getY()));
	}
	public void reflect(){
		reflectX();
		reflectY();
	}



}
