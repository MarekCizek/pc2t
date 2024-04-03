package knihovna;

public class Romany extends kniha {
String zanr;
public Romany(String nazev, String autor, int rok_vydani, boolean kDispozici, String zanr) {
    super(nazev, autor, rok_vydani, kDispozici);
    this.zanr = zanr;
}

public String getZanr() {
    return zanr;
}

public void setZanr(String zanr) {
    this.zanr = zanr;
}
}

