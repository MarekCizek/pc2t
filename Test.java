package projekt;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

	public static void main(String[] args) 
	{
		File cesta = new File("Knihy");
		if (!cesta.exists()) {
            cesta.mkdir();
		}
		int operace;
		DB databaze =new DB();
		SpravceKnihovny spravceKnihovny = new SpravceKnihovny();
		do {
		System.out.println("jakou operaci chcete provést");
		System.out.println("1: přidání knihy");
		System.out.println("2: úprava knihy");
		System.out.println("3: smazání knihy");
		System.out.println("4: označení knihy jako vypůjčené");
		System.out.println("5: Výpis knih");
		System.out.println("6: vyhledání knihy");
		System.out.println("7: výpis všech knih od určitého autora");
		System.out.println("8: výpis všech knih konkrétního žánru");
		System.out.println("9: výpis všech vypůjčených knih");
		System.out.println("10: uložení informace o knize do souboru");
		System.out.println("11: načtení informací ze souboru");
		System.out.println("99: načtení informací z databaze");
		
		
		Scanner sc = new Scanner(System.in);
		operace=pouzeCelaCisla(sc);
		switch (operace){
			case 1:
				spravceKnihovny.pridatKnihu();
				break;
			case 2:
				spravceKnihovny.zmenitUdajeOKnize();
				break;
			case 3:
				System.out.println("zadej název knihy");
				String nazev = sc.nextLine();
				spravceKnihovny.smazatKnihu(nazev);
				break;
			case 5:
				spravceKnihovny.vypisKnihovny();
				break;
			case 6:
				sc.nextLine();
				System.out.println("Zadejte název knihy:");
			    String hledanyNazev = sc.nextLine();
			    spravceKnihovny.vyhledatKnihu(hledanyNazev);
			    break;
			case 7:
				sc.nextLine();
				System.out.print("Zadajte meno autora:");
				String hladaneMeno=sc.nextLine();
				spravceKnihovny.vypisAutora(hladaneMeno);
				break;
			case 8:
				sc.nextLine();
				System.out.print("Zadajte zaner romanu: ");
				String zaner=sc.nextLine();
				spravceKnihovny.vyhladanieZanru(zaner);
				break;
			case 9:
				spravceKnihovny.vypisVypijcenych();
				break;
			case 10:
				System.out.println("Zadejte jmeno knihy: ");
				if (spravceKnihovny.ulozKnihu(sc.next()))
					System.out.println("Kniha ulozena");
				else
					System.out.println("Knihu nebylo mozno ulozit");
				break;
			case 11:
				sc.nextLine();
				System.out.println("Zadejte jmeno souboru");
				if (spravceKnihovny.nactiKnihu(sc.nextLine()))
					System.out.println("Kniha nactena");
				else
					System.out.println("Knihu nebylo mozno nacist");
				break;
			case 0:
				File fileToDelete = new File("myDB.db");
				fileToDelete.delete();
				spravceKnihovny.databazicka();
				System.out.println ("konec programu");
				break;
			case 99:
				spravceKnihovny.nacitajDB();
				break;
			default:
				System.out.println("neplatná volba");
		
		}
		}while (operace !=0);
	}

}
