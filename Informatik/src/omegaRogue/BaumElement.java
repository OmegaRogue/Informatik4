package omegaRogue;

import java.util.ArrayList;

public class BaumElement 
{
	private String inhalt;
	public ArrayList <BaumElement> nachfolger;
	private int pos;
	private Baum tree;
	private BaumElement top;
	
	public BaumElement(Baum tree, BaumElement top, int pos, String wert)
	{
		this.tree = tree;
		this.pos = pos;
		inhalt = wert;

		this.nachfolger = new ArrayList<BaumElement>();
	}
	
	public void addNachfolger(String wert)
	{
		BaumElement neuerNachfolger = new BaumElement(tree, this,pos+1,wert);
		nachfolger.add(neuerNachfolger);
	}
	public String toString() {
		String string = inhalt;
		if(this.pos == 0) {
			return string;
		}
		if(this.pos == 1) {
			if((tree.root.nachfolger.size() - tree.root.nachfolger.indexOf(this) -1) == 0) {
				string = "└" + string;
			}
			else {
				string = "├" + string;
			}
			return string;
		} else {
			if((top.nachfolger.size() - top.nachfolger.indexOf(this) -1) == 0) {
				string = "└" + string;
			}
			else {
				string = "├" + string;
			}	
			for (int i = 2; i <= pos; i++) {
				string = " " + string;
			}
			
			return string;
		}
		
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
	public Baum getTree() {
		return tree;
	}
	
	
}
