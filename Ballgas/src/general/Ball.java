package general;
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends BeweglElement
{
	int radius = 4;
	Color c= Color.blue;

	/**
	 * Konstruktor der Ballklasse.
	 * @param bx x-Koordinate des Startpunktes.
	 * @param by y-Koordinate des Startpunktes.
	 */
	public Ball(int bx, int by)
	{
		System.out.println("Hallo, ich bin ein neuer Ball!");
		x = bx;
		y = by;
	}
	
	/**
	 * Setzt die Farbe des Balls.
	 * @param farbe
	 */
	public void setColor(Color farbe)
	{
		c = farbe;
	}
	
	/**
	 * Zeichnet den Ball.
	 * @param g
	 */
	public void zeichnen(Graphics g)
	{
		g.setColor(c);
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
	}
}
