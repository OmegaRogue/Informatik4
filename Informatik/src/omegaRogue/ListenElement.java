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
	private ListenElement vorgänger;
	/**
	 * Konstruktor für Listen Elemente
	 * @param wert Der Inhalt des Elements
	 */
	public ListenElement(String wert) {
		inhalt = wert;
		index = 0;
	}
	/**
	 * Fügt einen neues Element am Ende der Liste hinzu
	 * @param neuerNachfolger Das neue Element
	 */
	public void setNachfolger(ListenElement neuerNachfolger) {
		if(nachfolger == null) {
			nachfolger = neuerNachfolger;
			nachfolger.setVorgänger(this);
			nachfolger.setIndex(nachfolger.getVorgänger().getIndex()+1);
		}
		else {
			getNachfolger().setNachfolger(neuerNachfolger);
		}
		
			
		
		
	}
	/**
	 * Gibt den Nachfolger dieses Elements zurück
	 * @return Der Nachfolger
	 */
	public ListenElement getNachfolger() {
		return nachfolger;
	}
	/**
	 * Setzt den Pointer zum Element vor diesem Element in der Liste
	 * @param alterVorgänger Das vorherige Element
	 */
	public void setVorgänger(ListenElement alterVorgänger) {
		vorgänger = alterVorgänger;
	}
	/**
	 * Gibt den Vorgänger dieses Elements zurück
	 * @return Der Vorgänger
	 */
	public ListenElement getVorgänger() {
		return vorgänger;
	}
	/**
	 * Setzt den Index dieses Elements
	 * @param i Der Index
	 */
	public void setIndex(int i) {
		this.index = i;
	}
	/**
	 * Gibt den Index dieses Elements zurück
	 * @return Der Index
	 */
	public int getIndex() {
		return this.index;
	}
	/**
	 * Gibt Inhalt und Index dieses Elements als String zurück
	 */
	public String toString() {
		return "["+this.getIndex()+"]"+": " + inhalt;
		
	}
}
