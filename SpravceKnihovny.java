package kokotina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SpravceKnihovny {
	DB databaze =new DB();
	Vector<Kniha> books = new Vector<>();
	private HashMap<String, Kniha> knihovnaMapa;
	Scanner sc = new Scanner(System.in);
	SpravceKnihovny(){
		knihovnaMapa = new HashMap<String, Kniha> ();
		
	}
public void pridatKnihu() {
	  Scanner sc = new Scanner(System.in);
	  System.out.println("vyberte typ knihy (roman/ucebnice)");
	  
	  String typKnihy = sc.nextLine();
	  while (!typKnihy.equals("roman") && !typKnihy.equals("ucebnice")) {
			 System.out.println("zadejte pouze ve tvaru roman/ucebnice");
			 typKnihy = sc.nextLine();
			 
		  } 
			  
	System.out.println("Zadejte název knihy:");
  String nazev = sc.nextLine();

  System.out.println("Zadejte jméno autora:");
  String autor = sc.nextLine();

  System.out.println("Zadejte rok vydání:");
  int rok_vydani = Test.pouzeCelaCisla(sc);


  boolean kDispozici = true;
  sc.nextLine(); 
  if (typKnihy.equals("roman")) {
	  System.out.println("zadejte žánr románu");
	  String zanr = sc.nextLine();
	  Romany roman1 = new Romany(nazev,autor,rok_vydani,kDispozici,zanr);
	  knihovnaMapa.put(nazev, roman1);
	
  }
  else if (typKnihy.equals("ucebnice")) {
   System.out.println("zadejte pro jaký ročník je kniha vhodná");
   int vhodnost = sc.nextInt();
   sc.nextLine();
   Ucebnica ucebnice1 = new Ucebnica (nazev, autor, rok_vydani, kDispozici, vhodnost);
   knihovnaMapa.put(nazev, ucebnice1);
  }
  else {
  	System.out.println("nebyl zadan platný typ");
  	return;
  }
  Kniha kniha = new Kniha (nazev,autor,rok_vydani,kDispozici);
  books.add(kniha );
  System.out.println("kniha byla přidána");
}
public void zmenitUdajeOKnize() {
  Scanner sc = new Scanner(System.in);

  System.out.println("Zadejte název knihy, jejíž údaje chcete změnit:");
  String nazevKnihy = sc.nextLine();
  
  if (knihovnaMapa.containsKey(nazevKnihy)) {
      Kniha nalezenaKniha = knihovnaMapa.get(nazevKnihy);

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
      Kniha Kniha = knihovnaMapa.get(jmeno);
      System.out.println("Název: " + Kniha.getNazev());
      System.out.println("Autor: " + Kniha.getAutor());
      if (Kniha instanceof Romany) {
          Romany roman = (Romany) Kniha;
          System.out.println("Žánr: " + roman.getZanr());
      } else if (Kniha instanceof Ucebnica) {
          Ucebnica ucebnice = (Ucebnica) Kniha;
          System.out.println("Ročník: " + ucebnice.getVhodnost());
      }
      System.out.println("Rok vydání: " + Kniha.getRokVydani());
      System.out.println("Je dostupná: " + (Kniha.jekDispozici()));
      System.out.println();
	}

	
}
public void Pujcka () {
	 Scanner sc = new Scanner(System.in);
	
	    System.out.println("zadejte název vypůjčené knihy");
	    String nazevKnihy = sc.nextLine();
	    
	    if (knihovnaMapa.containsKey(nazevKnihy)) {
	        Kniha nalezenaKniha = knihovnaMapa.get(nazevKnihy);
	        System.out.print("je kniha dostupná?");
	        boolean dostupnost_knihy = sc.nextBoolean(); 
	        nalezenaKniha.setKDispozici(dostupnost_knihy); 
	    }
	
}  
public void vyhledatKnihu(String nazev) {
  if (knihovnaMapa.containsKey(nazev)) {
      Kniha nalezenaKniha = knihovnaMapa.get(nazev);
      System.out.println("Název knihy: " + nalezenaKniha.getNazev());
      System.out.println("Autor: " + nalezenaKniha.getAutor());
      System.out.println("Rok vydání: " + nalezenaKniha.getRokVydani());
      System.out.println("Je k dispozici: " + (nalezenaKniha.jekDispozici() ? "Ano" : "Ne"));
  } else {
      System.out.println("Kniha s názvem '" + nazev + "' nebyla nalezena.");
  }
}
	
	public void vypisAutora(String meno) {
		int pocet=0;
		books.sort(Comparator.comparingInt(Kniha::getRokVydani));
		//System.out.println(books.get(0).getAutor());
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getAutor().equals(meno)) {
				pocet++;
				System.out.println("Kniha: " + books.get(i).getNazev());
				System.out.println("Rok: " + books.get(i).getRokVydani());
				System.out.println("Dostupnost: " + books.get(i).jekDispozici()+"\n");
			}
			}
		if(pocet==0) {
			System.out.println("Autor nenalezen.\n");
		}
		}
	
	public void vyhladanieZanru(String zaner) {
		int pocet=0;
		for(Kniha k:knihovnaMapa.values()) {
			if(k instanceof Romany) {
				Romany roman= (Romany)k;
				if(roman.getZanr().equals(zaner)) {
					System.out.println("Kniha: " + roman.getNazev() + "\nAutor: " + roman.getAutor());
					pocet++;
				}
			}
			}
		if(pocet==0) {
			System.out.println("Zanr nenalezen.");
		}   
	    }
	public void vypisVypijcenych() {
		int pocet=0;
		for(Kniha k:knihovnaMapa.values()) {
			if(k.jekDispozici()==false) {
				pocet++;
				System.out.println("Nazov: " + k.getNazev() + "\nAutor: " + k.getAutor());
				if(k instanceof Romany) {
					System.out.println("Typ: roman");
				}
				else {
					System.out.println("Typ: ucebnica");
				}
			}
		}
		if(pocet==0) {
			System.out.print("Zadna kniha neni vypujcena");
		}
	}
	public boolean ulozKnihu(String nazov) {
		if (knihovnaMapa.containsKey(nazov)) {
	        Kniha nalezenaKniha = knihovnaMapa.get(nazov);
	        File directory = new File("Knihy");
	        File file = new File(directory, nazov + ".txt");
		    try{
				FileWriter fw=new FileWriter(file);
				BufferedWriter out = new BufferedWriter(fw);
				out.write(nalezenaKniha.getNazev());
				out.newLine();
				out.write(nalezenaKniha.getAutor());
				out.newLine();
				out.write(Integer.toString(nalezenaKniha.getRokVydani()));
				out.newLine();
				if(nalezenaKniha instanceof Romany) {
					Romany roman= (Romany)nalezenaKniha;
					out.write("roman");
					out.newLine();
					out.write(roman.getZanr());
				}
				else {
					Ucebnica ucebnica=(Ucebnica)nalezenaKniha;
					out.write("ucebnice");
					out.newLine();
					out.write(Integer.toString(ucebnica.getVhodnost()));
				}
				out.newLine();
				out.write(Boolean.toString(nalezenaKniha.jekDispozici()));
				out.close();
				fw.close();
			}
		    catch (IOException e) {
				System.out.println("Soubor nelze vytvorit");
				return false;
			}
			return true;
	}
		else {
			return false;
		}
}
	public boolean nactiKnihu(String nazov) {
		File directory = new File("Knihy");
		File file = new File(directory, nazov);
		FileReader fr=null;
		BufferedReader in=null;
		try {
			fr = new FileReader(file);
	        in = new BufferedReader(fr);
			String[] radek= new String[6];
			for(int i=0;i<6;i++) {
				radek[i] = in.readLine();
			}
			if(radek[3].equals("roman")) {
				Romany roman1 = new Romany(radek[0],radek[1],Integer.parseInt(radek[2]),Boolean.parseBoolean(radek[5]),radek[4]);
			  	knihovnaMapa.put(radek[0], roman1);
			  	books.add(roman1);
			}
			else {
				Ucebnica ucebnica1= new Ucebnica(radek[0],radek[1],Integer.parseInt(radek[2]),Boolean.parseBoolean(radek[5]),Integer.parseInt(radek[4]));
				knihovnaMapa.put(radek[0], ucebnica1);
				books.add(ucebnica1);
			}

	}
		catch (IOException e) {
			System.out.println("Soubor  nelze otevřít");
			return false;
		} 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Chyba integrity dat v souboru");
			return false;
		}
		finally
		{
			try
			{	if (in!=null)
				{
					in.close();
					fr.close();
				}
			}
			catch (IOException e) {
				System.out.println("Soubor  nelze zavrit");
				return false;
			} 
		}
		
		return true;
}
	public void databazicka() {
		databaze.connect();
		databaze.createTable();
		String typ,specifikum;
		for(int i=0;i<books.size();i++) {
			Kniha nalezenaKniha = books.get(i);
			if(nalezenaKniha instanceof Romany) {
				Romany roman= (Romany)nalezenaKniha;
				typ="roman";
				specifikum=roman.getZanr();
			}
			else {
				Ucebnica ucebnice=(Ucebnica)nalezenaKniha;
				typ="ucebnice";
				specifikum=String.valueOf(ucebnice.getVhodnost());
			}
			databaze.insertRecord(nalezenaKniha.getNazev(),nalezenaKniha.getAutor(),nalezenaKniha.getRokVydani(),nalezenaKniha.jekDispozici(),typ,specifikum);
		}
		databaze.disconnect();
	}
	public void nacitajDB() {
		databaze.connect();
		
		knihovnaMapa=databaze.getKniha();
		for(Kniha k:knihovnaMapa.values()) {
			books.add(k);
		}
		databaze.disconnect();
	}
}