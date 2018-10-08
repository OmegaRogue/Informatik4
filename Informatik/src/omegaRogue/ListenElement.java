package omegaRogue;
/**
 * Elemente der eigenen Listenimplementation
 * @author OmegaRogue
 *
 */
public class ListenElement {
	private String inhalt;
	private int index;
	private ListenElement nachfolger;
	private ListenElement vorg�nger;
	/**
	 * Konstruktor f�r Listen Elemente
	 * @param wert Der Inhalt des Elements
	 */
	public ListenElement(String wert) {
		inhalt = wert;
		index = 0;
	}
	/**
	 * F�gt einen neues Element am Ende der Liste hinzu
	 * @param neuerNachfolger Das neue Element
	 */
	public void setNachfolger(ListenElement neuerNachfolger) {
		if(nachfolger == null) {
			nachfolger = neuerNachfolger;
			nachfolger.setVorg�nger(this);
			nachfolger.setIndex(nachfolger.getVorg�nger().getIndex()+1);
		}
		else {
			getNachfolger().setNachfolger(neuerNachfolger);
		}
		
			
		
		
	}
	/**
	 * Gibt den Nachfolger dieses Elements zur�ck
	 * @return Der Nachfolger
	 */
	public ListenElement getNachfolger() {
		return nachfolger;
	}
	/**
	 * Setzt den Pointer zum Element vor diesem Element in der Liste
	 * @param alterVorg�nger Das vorherige Element
	 */
	public void setVorg�nger(ListenElement alterVorg�nger) {
		vorg�nger = alterVorg�nger;
	}
	/**
	 * Gibt den Vorg�nger dieses Elements zur�ck
	 * @return Der Vorg�nger
	 */
	public ListenElement getVorg�nger() {
		return vorg�nger;
	}
	/**
	 * Setzt den Index dieses Elements
	 * @param i Der Index
	 */
	public void setIndex(int i) {
		this.index = i;
	}
	/**
	 * Gibt den Index dieses Elements zur�ck
	 * @return Der Index
	 */
	public int getIndex() {
		return this.index;
	}
	/**
	 * Gibt Inhalt und Index dieses Elements als String zur�ck
	 */
	public String toString() {
		return "["+this.getIndex()+"]"+": " + inhalt;
		
	}
}
