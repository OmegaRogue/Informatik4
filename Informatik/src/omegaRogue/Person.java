package omegaRogue;

public class Person {
String name;
String vorname;
String beruf;
int alter;
String stra�e;
int plz;
String ort;
public Person(String name, String vorname, String beruf, int alter, String stra�e,int plz, String ort) {
	this.name = name;
	this.vorname = vorname;
	this.beruf = beruf;
	this.alter = alter;
	this.stra�e = stra�e;
	this.plz = plz;
	this.ort = ort;
}
public String toString() {
	String strRueckgabe = "[";
	strRueckgabe = strRueckgabe + this.name + "; ";
	strRueckgabe = strRueckgabe + this.vorname + "; ";
	strRueckgabe = strRueckgabe + this.beruf + "; ";
	strRueckgabe = strRueckgabe + this.alter + "; ";
	strRueckgabe = strRueckgabe + this.stra�e + "; ";
	strRueckgabe = strRueckgabe + this.plz + "; ";
	strRueckgabe = strRueckgabe + this.ort + "; ";
	return strRueckgabe;
}

}
