package omegaRogue;

import java.util.ArrayList;

public class BaumElement 
{
	public String inhalt;
	public ArrayList <BaumElement> nachfolger;
	
	public BaumElement(String wert)
	{
		inhalt = wert;
		nachfolger = new ArrayList<BaumElement>();
	}
	
	public void addNachfolger(String wert)
	{
		BaumElement neuerNachfolger = new BaumElement(wert);
		nachfolger.add(neuerNachfolger);
	}
	public String toString() {
		return inhalt;
	}
	public void rekursiveAusgabe() {
		System.out.println(inhalt);
		for (BaumElement be : nachfolger) {
			be.rekursiveAusgabe();
		}
	}
	public BaumElement getThis () {
		return this;
	}
	public BaumElement rekursiveSuche(String suchWert) {
		BaumElement get = null;
		if(this.inhalt.equals(suchWert)) {
			get = this;
//			System.out.println(inhalt);
			return get;
			
		}
		
		for (BaumElement be : nachfolger) {
				return be.rekursiveSuche(suchWert);
			}
		return get;
		
	}
	
	
}
