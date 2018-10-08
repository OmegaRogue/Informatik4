package omegaRogue;
import java.util.ArrayList;
public class Baum2Element {
	public ArrayList<Baum2Element> astblatt;
	public String inhalt;
	public Baum2Element(String wert) {
		inhalt = wert;
	}
	public String toString() {
		return inhalt;
	}
}
