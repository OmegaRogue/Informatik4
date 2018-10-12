package omegaRogue;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GrafikPanel extends JPanel
{
	
	Ball[] baelle = new Ball[10000];
	Random zufall = new Random();
	
	/**
	 * Der Konstruktor des Grafikpanels.
	 */
	public GrafikPanel()
	{
		for (int i=0; i<baelle.length; i++)
		{
			baelle[i] = new Ball(zufall.nextInt(400),zufall.nextInt(400));
			baelle[i].setV(zufall.nextInt(10)-5, zufall.nextInt(10)-5);
			//baelle[i].setColor(new Color(zufall.nextInt(255), zufall.nextInt(255), zufall.nextInt(255)) );
		}
	}
	
	// Diese Funktion wird immer aufgerufen, wenn das
	// Fenster (das Grafikpanel) neu gezeichnet wird.
	
	protected void paintComponent(Graphics g) 
	{
		int breite = getWidth();
		int hoehe = getHeight();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, breite, hoehe);
		
		for (int i=0;i<baelle.length; i++)
		{
			baelle[i].setBreiteHoehe(breite,hoehe);
			baelle[i].zeichnen(g);
			baelle[i].move();
		}
		
		g.setColor(Color.pink);
		g.fillRect(120, 240, 280, 140);
		g.setColor(Color.pink);
		g.fillRect(680,240,280,140);
		g.setColor(Color.pink);
		g.fillRect(120, 520, 280, 140);
		g.setColor(Color.pink);
		g.fillRect(680, 520, 280, 140);
	}
	
}
