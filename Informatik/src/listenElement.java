
public class listenElement {
	private String inhalt;
	private listenElement nachfolger;
	public listenElement(String wert) {
		inhalt = wert;
	}
	public void setNachfolger(listenElement neuerNachfolger) {
		nachfolger = neuerNachfolger;
	}
}
