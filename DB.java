package projekt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DB {
	private Connection conn; 
	public boolean connect() { 
	       if(conn==null) {
	    	   try {
	                Class.forName("org.sqlite.JDBC");
	                conn = DriverManager.getConnection("jdbc:sqlite:C:myDB.db");
	            } catch (SQLException | ClassNotFoundException e) {
	                e.printStackTrace();
	                return  false;
	            }
	       }
	      return true;
	}
	public void disconnect() { 
		if (conn != null) {
		       try {     
		    	   conn.close();
		    	   conn=null;
		       } 
	               catch (SQLException ex) { System.out.println(ex.getMessage()); }
		}
	}
	public boolean createTable()
	{
	     if (conn==null)
	           return false;
	    String sql = "CREATE TABLE IF NOT EXISTS knihy (" + "id integer PRIMARY KEY," + "nazev varchar(255) NOT NULL,"+ "autor varchar(255) NOT NULL, " + "rok_vydani int NOT NULL, " + "dostupnost BOOLEAN," + "typ varchar(255),"+"specifikum varchar(255)"+")";
	    try{
	            Statement stmt = conn.createStatement(); 
	            stmt.execute(sql);
	            return true;
	    } 
	    catch (SQLException e) {
	    System.out.println(e.getMessage());
	    }
	    return false;
	}
	public void insertRecord(String nazev, String autor, int rok_vydani,boolean dostupnost, String typ,String specifikum) {
        String sql = "INSERT INTO knihy(nazev,autor,rok_vydani,dostupnost,typ,specifikum) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, nazev);
            pstmt.setString(2, autor);
            pstmt.setInt(3, rok_vydani);
            pstmt.setBoolean(4, dostupnost);
            pstmt.setString(5, typ);
            pstmt.setString(6,specifikum);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	public HashMap<String, Kniha> getKniha(){
		 HashMap<String, Kniha> hashMap = new HashMap<>();
	     String sql = "SELECT * FROM knihy;";
	     try {
	           Statement pstmt  = conn.createStatement();
	           ResultSet rs  = pstmt.executeQuery(sql);
	          while (rs.next()) {
	        	  rs.getInt("id");
	        	  String nazev=rs.getString("nazev");
	        	  String autor=rs.getString("autor");
	        	  int rok_vydani=rs.getInt("rok_vydani");
	        	  boolean dostupnost=rs.getBoolean("dostupnost");
	        	  if(rs.getString("typ").equals("roman")) {
	  				Romany roman=new Romany(nazev,autor,rok_vydani,dostupnost,rs.getString("specifikum"));
	  				hashMap.put(nazev,roman);
	  			}
	  			else {
	  				Ucebnica ucebnice=new Ucebnica(nazev,autor,rok_vydani,dostupnost,Integer.valueOf(rs.getString("specifikum")));
	  				hashMap.put(nazev,ucebnice);
	  			}
	          }
	     } 
	     catch (SQLException e) {
	           System.out.println(e.getMessage());
	     }
	     return hashMap;
	}



}
