package omegaRogue;

public class Person {
String name;
String vorname;
String beruf;
int alter;
String straﬂe;
int plz;
String ort;
public Person(String name, String vorname, String beruf, int alter, String straﬂe,int plz, String ort) {
	this.name = name;
	this.vorname = vorname;
	this.beruf = beruf;
	this.alter = alter;
	this.straﬂe = straﬂe;
	this.plz = plz;
	this.ort = ort;
}
public String toString() {
	String strRueckgabe = "[";
	strRueckgabe = strRueckgabe + this.name + "; ";
	strRueckgabe = strRueckgabe + this.vorname + "; ";
	strRueckgabe = strRueckgabe + this.beruf + "; ";
	strRueckgabe = strRueckgabe + this.alter + "; ";
	strRueckgabe = strRueckgabe + this.straﬂe + "; ";
	strRueckgabe = strRueckgabe + this.plz + "; ";
	strRueckgabe = strRueckgabe + this.ort + "; ";
	return strRueckgabe;
}

}
