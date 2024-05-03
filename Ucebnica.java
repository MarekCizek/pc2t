package kokotina;

public class Ucebnica extends Kniha {
int vhodnost;
public Ucebnica(String nazev, String autor, int rokVydani, boolean kDispozici, int vhodnost) {
    super(nazev, autor, rokVydani, kDispozici);
    this.vhodnost = vhodnost;
}

public int getVhodnost() {
    return vhodnost;
}

public void setRocniOd(int rocniOd) {
    this.vhodnost = vhodnost;
}
}
