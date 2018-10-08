package omegaRogue;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;
public class Baum2 {
	public Baum2Element root;
	public ArrayList<Baum2Element> astBlatt;
	public CharImg output;
//	public Dictionary<Integer, String> charMap = {;
	public void Baum2(Baum2Element stamm) {
		this.root = stamm;
		astBlatt = new ArrayList<Baum2Element>();
		//
	}
	public String toString() {
		
		String ausgabe = "|\n";
		return ausgabe;
	}
}
