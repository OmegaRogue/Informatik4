package omegaRogue;


/**
 * Eigene Baumimplementation zu Übungszwecken im Informatikuntericht 4std G11.
 * 
 * 
 * @since 05.10.2018
 *
 */
public class Baum 
{
	public CharImg output = new CharImg();
	BaumElement root;
	
	/**
	 * Die ist der Konstruktor der Liste.
	 * @param startWert Dies ist das Startelement der Liste. 
	 */
	public Baum(String startWert)
	{
		root = new BaumElement(startWert);
	}
	public String toString() {
		String ausgabe = "";
		ausgabe += root;
		ausgabe += CharImg.generate("000301000602");
		return ausgabe;
	}
}	






	/*
	/**
	 * Diese Funktion fügt ein neues Baumelement hinzu. 
	 * @param neuerWert Der Inhalt für das hinzuzufügende Baumelement.
	 *
	 *
	public void add(String neuerWert)
	{
		// Zeilenkommentar
		BaumElement neuesElement = new ListenElement(neuerWert);
		ListenElement e = startElement;
		while (e.nachfolger!=null)
		{
			e=e.nachfolger;
		}
		e.setNachfolger(neuesElement);
	}
	
	/**
	 * Diese Funktion gibt den Inhalt der Liste auf der Konsole aus.
	 *
	public void listeAusgeben()
	{
	/*
	 *  Blockkommentar
	 * 
	    ListenElement e = startElement;
		while (e.nachfolger!=null)
		{
			System.out.println(e.inhalt);
			e=e.nachfolger;
		}
		System.out.println(e.inhalt); 
	}
	*/
	
