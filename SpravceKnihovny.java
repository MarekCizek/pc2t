package knihovna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SpravceKnihovny {
	private HashMap<String, kniha> knihovnaMapa;
	SpravceKnihovny(){
		knihovnaMapa = new HashMap<String, kniha> ();
		
	}
	public void pridatKnihu() {
		  Scanner sc = new Scanner(System.in);
		  System.out.println("vyberte typ knihy (roman/ucebnice");
		  String typKnihy = sc.nextLine();
		  System.out.println("Zadejte název knihy:");
	    String nazev = sc.nextLine();

	    System.out.println("Zadejte jméno autora:");
	    String autor = sc.nextLine();

	    System.out.println("Zadejte rok vydání:");
	    int rok_vydani = sc.nextInt();

	    System.out.println("Je kniha k dispozici? (true/false)");
	    boolean kDispozici = sc.nextBoolean();
	    sc.nextLine(); // Zde přečteme zbytek řádku (nový řádek), aby se další vstup načetl správně
	    if (typKnihy.equals("roman")) {
	  	  System.out.println("zadejte žánr románu");
	  	  String zanr = sc.nextLine();
	  	  Romany roman1 = new Romany(nazev,autor,rok_vydani,kDispozici,zanr);
	  	  knihovnaMapa.put(nazev, roman1);
	  	
	    }
	    else if (typKnihy.equals("ucebnice")) {
	     System.out.println("zadejte pro jaký ročník je kniha vhodná");
	     int vhodnost = sc.nextInt();
	     sc.nextLine(); // Zde přečteme zbytek řádku (nový řádek), aby se další vstup načetl správně
	     ucebnice ucebnice1 = new ucebnice (nazev, autor, rok_vydani, kDispozici, vhodnost);
	     knihovnaMapa.put(nazev, ucebnice1);
	    }
	    else {
	    	System.out.println("nebyl zadan platný typ");
	    	return;
	    }
	    System.out.println("kniha byla přidána");
	}
	public void zmenitUdajeOKnize() {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("Zadejte název knihy, jejíž údaje chcete změnit:");
	    String nazevKnihy = sc.nextLine();
	    
	    if (knihovnaMapa.containsKey(nazevKnihy)) {
	        kniha nalezenaKniha = knihovnaMapa.get(nazevKnihy);

	        System.out.println("Zadejte nový název knihy:");
	        String novyNazev = sc.nextLine();
	        nalezenaKniha.setNazev(novyNazev);

	        System.out.println("Zadejte nového autora knihy:");
	        String novyAutor = sc.nextLine();
	        nalezenaKniha.setAutor(novyAutor);

	        System.out.println("Zadejte nový rok vydání knihy:");
	        int novyRokVydani = sc.nextInt();
	        nalezenaKniha.setRokVydani(novyRokVydani);

	        System.out.println("Je kniha k dispozici? (true/false)");
	        boolean novaDostupnost = sc.nextBoolean();
	        nalezenaKniha.setKDispozici(novaDostupnost);

	        knihovnaMapa.put(novyNazev, nalezenaKniha);
	        knihovnaMapa.remove(nazevKnihy);

	        System.out.println("Údaje o knize " + nazevKnihy + " byly úspěšně změněny.");
	    } else {
	        System.out.println("Kniha s názvem " + nazevKnihy + " nebyla nalezena.");
	    }
	}
	public boolean smazatKnihu (String nazev) {
		if (knihovnaMapa.remove(nazev)!=null)
			return true;
		return false;
		
	}
	public void vypisKnihovny () {
		Set<String> seznamKnih = new TreeSet<>(knihovnaMapa.keySet());
        for (String jmeno : seznamKnih) {
            kniha Kniha = knihovnaMapa.get(jmeno);
            System.out.println("Název: " + Kniha.getNazev());
            System.out.println("Autor: " + Kniha.getAutor());
            if (Kniha instanceof Romany) {
                Romany roman = (Romany) Kniha;
                System.out.println("Žánr: " + roman.getZanr());
            } else if (Kniha instanceof ucebnice) {
                ucebnice ucebnice = (ucebnice) Kniha;
                System.out.println("Ročník: " + ucebnice.getVhodnost());
            }
            System.out.println("Rok vydání: " + Kniha.getRokVydani());
            System.out.println("Je dostupná: " + (Kniha.jekDispozici()));
            System.out.println();
		}

		
	} 
	public void vyhledatKnihu(String nazev) {
	    if (knihovnaMapa.containsKey(nazev)) {
	        kniha nalezenaKniha = knihovnaMapa.get(nazev);
	        System.out.println("Název knihy: " + nalezenaKniha.getNazev());
	        System.out.println("Autor: " + nalezenaKniha.getAutor());
	        System.out.println("Rok vydání: " + nalezenaKniha.getRokVydani());
	        System.out.println("Je k dispozici: " + (nalezenaKniha.jekDispozici() ? "Ano" : "Ne"));
	    } else {
	        System.out.println("Kniha s názvem '" + nazev + "' nebyla nalezena.");
	    }
	}

}
