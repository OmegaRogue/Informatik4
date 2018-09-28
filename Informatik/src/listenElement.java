
public class ListenElement {
	private String inhalt;
	private int index;
	private ListenElement nachfolger;
	private ListenElement vorgänger;
	private boolean start;
	public ListenElement(boolean first,String wert) {
		inhalt = wert;
		start = first;
		index = 0;
	}
	public void setNachfolger(ListenElement neuerNachfolger) {
		if(nachfolger == null) {
			nachfolger = neuerNachfolger;
			nachfolger.setVorgänger(this);
			nachfolger.setIndex(getVorgänger().index++);
		}
		else {
			getNachfolger().setNachfolger(neuerNachfolger);
		}
		
			
		
		
	}
	public ListenElement getNachfolger() {
		return nachfolger;
	}
	public void setVorgänger(ListenElement alterVorgänger) {
		vorgänger = alterVorgänger;
	}
	public ListenElement getVorgänger() {
		return vorgänger;
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
