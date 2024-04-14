package knihovna;
import java.util.HashMap;
import java.util.Scanner;
public class kniha {
String  nazev;
String autor;
int rok_vydani;
boolean kDispozici;
public kniha (String nazev, String autor, int rok_vydani, boolean kDispozici) {
	this.nazev = nazev;
	this.autor = autor;
	this.kDispozici = kDispozici;
	this.rok_vydani = rok_vydani;
}

public String getNazev() {
    return nazev;
}

public void setNazev(String nazev) {
    this.nazev = nazev;
}

public String getAutor() {
    return autor;
}

public void setAutor(String autor) {
    this.autor = autor;
}

public int getRokVydani() {
    return rok_vydani;
}

public void setRokVydani(int rokVydani) {
    this.rok_vydani = rokVydani;
}

public boolean jekDispozici() {
    return kDispozici;
}

public void setKDispozici(boolean kDispozici) {
    this.kDispozici = kDispozici;
}


}

