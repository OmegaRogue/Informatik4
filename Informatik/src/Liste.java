
public class Liste {
	private ListenElement startElement;
	private int size;
	
	public  Liste(String startWert) {
		startElement = new ListenElement(true,startWert);
		size = 1;
	}
	public void add(String neuerWert) {
		
		ListenElement neuesElement = new ListenElement(false,neuerWert);
		startElement.setNachfolger(neuesElement);
		size++;
	}
	public void printAll() {
		ListenElement print = startElement;
		for(int i = 0; i < size--;i++) {
			//System.out.println(print);
			System.out.println(print);
			print = print.getNachfolger();
		}
	}
	public void printIndex(int index) {
		System.out.println(this.getForIndex(index));
	}
	public int getSize() {
		return size;
	}
	public ListenElement getForIndex(int index) {
		ListenElement get = startElement;
		for (int i = 0; i < index; i++) {
			get = get.getNachfolger();
		}
		return get;
	}
}
