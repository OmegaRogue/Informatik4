
/**
 * 
 * @author OmegaRogue
 * Eigene Listenimplementation
 * 
 */
public class Liste {
	private ListenElement startElement;
	private int size;
	/**
	 * Dies ist der  Konstruktor der Liste.
	 * @param startWert Der Inhalt des ersten Elements der Liste
	 */
	public  Liste(String startWert) {
		startElement = new ListenElement(startWert);
		size = 1;
	}
	/**
	 *  Fügt ein neues Element am Ende der Liste hinzu
	 * @param neuerWert Der Inhalt des neuen Elements
	 */
	public void add(String neuerWert) {
		
		ListenElement neuesElement = new ListenElement(neuerWert);
		startElement.setNachfolger(neuesElement);
		size++;
	}
	/**
	 * Gibt den Inhalt aller Elemente aus
	 */
	public void printAll() {
		for(int i = 0; i < size;i++) {
			System.out.println(getForIndex(i));
		}
	}
	/**
	 * Gibt den Inhalt eines Elements aus
	 * @param index Die Position des Elements
	 */
	public void printIndex(int index) {
		System.out.println(this.getForIndex(index));
	}
	/**
	 * Gibt die Länge der Liste zurück
	 * @return Die Länge der Liste
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Gibt das Element der Liste an einer Position zurück
	 * @param index Die Position des Elements
	 * @return Das Element
	 */
	public ListenElement getForIndex(int index) {
		ListenElement get = startElement;
		for (int i = 0; i < index; i++) {
			get = get.getNachfolger();
		}
		return get;
	}
}
