package general;
import java.awt.Container;
import javax.swing.JFrame;

public class GrafikProgramm
{
	/**
	 * TODO Change to Java8
	 * @todo Change to Java8
	 * @body Update BallGas to Java8
	 */
	JFrame grafiktestFenster = new JFrame();
	GrafikPanel grafikpanel = new GrafikPanel();
	
	public static void main(String[] args) 
	{
		GrafikProgramm meinProgrammObjekt = new GrafikProgramm();
		meinProgrammObjekt.initialisierung();
		meinProgrammObjekt.grafiktestFenster.setVisible(true);
	}

	private void initialisierung() 
	{
		grafiktestFenster.setTitle("Java Übungen");
		grafiktestFenster.setSize(1024,768);
		grafiktestFenster.setLocation(100,100);
		grafiktestFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentpane = grafiktestFenster.getContentPane();
		contentpane.add(grafikpanel);
		
		Timer timer = new Timer(grafikpanel,20);
		timer.start();
	}
	
}
