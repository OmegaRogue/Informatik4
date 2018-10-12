package omegaRogue;

import java.util.ArrayList;

public class BaumElement 
{
	private String inhalt;
	public ArrayList <BaumElement> nachfolger;
	private int pos;

	
	public BaumElement(int pos, String wert)
	{
		this.pos = pos;
		inhalt = wert;

		this.nachfolger = new ArrayList<BaumElement>();
	}
	
	public void addNachfolger(String wert)
	{
		BaumElement neuerNachfolger = new BaumElement(pos+1,wert);
		nachfolger.add(neuerNachfolger);
	}
	public String toString() {
		String string = inhalt;
		if(this.pos == 0) {
			return string;
		}
		string = "└" + string;
		for (int i = 2; i <= pos; i++) {
			string = " " + string;
		}
		
		return string;
	}
	public void rekursiveAusgabe() {
		System.out.println(this);
		for (BaumElement be : nachfolger) {
			be.rekursiveAusgabe();
		}
	}
	public BaumElement getThis () {
		return this;
	}
	public int getPos() {
		return pos;
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
