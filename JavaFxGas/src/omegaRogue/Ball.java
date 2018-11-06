package omegaRogue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @version 1
 * @author OmegaRogue
 */
public class Ball extends Circle
{
	double radius = 4;
	double x = 0;
	double y = 0;
	double vx = 0.4;
	double vy = 0.2;
	Color c= Color.BLUE;


	/**
	 * Konstruktor der Ballklasse. 
	 * @param bx x-Koordinate des Startpunktes.
	 * @param by y-Koordinate des Startpunktes.
	 * @param radius Radius des Balls.
	 * @param col Farbe des Balls.
	 */
	public Ball(double bx, double by, double radius, Color col)
	{
		super(bx,by,radius);
		x=bx;
		y=by;
		c = col;
		setFill(col);
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
		x=x+vx;
		y=y+vy;
		relocate(x, y);
	}

}