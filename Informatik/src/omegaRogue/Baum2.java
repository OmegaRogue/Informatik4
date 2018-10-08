package omegaRogue;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;
public class Baum2 {
	public Baum2Element root;
	public ArrayList<Baum2Element> astBlatt;
	public CharImg output = new CharImg();
//	public Dictionary<Integer, String> charMap = {;
	public Baum2(String stammWert) {
		root = new Baum2Element(stammWert);
		astBlatt = new ArrayList<Baum2Element>();
		//
	}
	public String toString() {
		String ausgabe = "";
		ausgabe += root.inhalt;
		ausgabe += CharImg.generate("0301000602");
		return ausgabe;
	}
}
