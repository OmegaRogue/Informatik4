
public class ListenElement {
	private String inhalt;
	private int index;
	private ListenElement nachfolger;
	private ListenElement vorg�nger;
	private boolean start;
	public ListenElement(boolean first,String wert) {
		inhalt = wert;
		start = first;
		index = 0;
	}
	public void setNachfolger(ListenElement neuerNachfolger) {
		if(nachfolger == null) {
			nachfolger = neuerNachfolger;
			nachfolger.setVorg�nger(this);
			nachfolger.setIndex(getVorg�nger().index++);
		}
		else {
			getNachfolger().setNachfolger(neuerNachfolger);
		}
		
			
		
		
	}
	public ListenElement getNachfolger() {
		return nachfolger;
	}
	public void setVorg�nger(ListenElement alterVorg�nger) {
		vorg�nger = alterVorg�nger;
	}
	public ListenElement getVorg�nger() {
		return vorg�nger;
	}
	public void setIndex(int i) {
		this.index = i;
	}
	public int getIndex() {
		return this.index;
	}
	public String toString() {
		//System.out.println(nachfolger);
		if(start) {
			return "["+0+"]"+": " + inhalt;
		}
		return "["+index+"]"+": " + inhalt;
		
	}
}
