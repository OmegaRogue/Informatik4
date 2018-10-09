package omegaRogue;

import java.util.ArrayList;

public class BaumElement 
{
	public String inhalt;
	public ArrayList <BaumElement> nachfolger;
	
	public BaumElement(String wert)
	{
		inhalt = wert;
	}
	
	public void addNachfolger(String wert)
	{
		BaumElement neuerNachfolger = new BaumElement(wert);
		nachfolger.add(neuerNachfolger);
	}
	public String toString() {
		return inhalt;
	}
	
}
