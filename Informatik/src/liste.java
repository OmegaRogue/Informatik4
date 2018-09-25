
public class liste {
	listenElement startElement;
	public  liste(String startWert) {
		startElement = new listenElement(startWert);
	}
	public void add(String neuerWert) {
		
		listenElement neuesElement = new listenElement(neuerWert);
		startElement.setNachfolger(neuesElement);
	}
}
