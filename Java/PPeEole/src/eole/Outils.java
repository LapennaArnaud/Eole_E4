package eole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Outils {
	public static String cheminBdd = "jdbc:ucanaccess://C:/Users/arnaud/eclipse_projets/Eole_E4/BddAccess/BddEole.accdb";
	
	public int tempsReel(int h, int m, int s){
		return s + m*60 + h*3600;
	}
	
	public boolean estEntier(String s){
		String chiffre = "0123456789";
		boolean b = true;
		for(int i = 0 ; i < s.length() ; i++){
			if(chiffre.contains(s.substring(i, i+1))){
				b = false;
			}
		}
		return b;
	}
	
	public String afficherTemps(int temps){ // ne marche pas 
		int h = temps / 3600;
		temps -= h;
		int m = temps / 60;
		temps -= m;
		return h + ":" + m + ":" + temps;
	}
	
	public int tempsCompensé(int temps, int rating, int distance){
		int handicap = (int)(5143 / (Math.sqrt(rating) + 3.5 ) * distance);
		return (temps + handicap);
	}
	
	public void chargeListeRegAcceuil(){
		Accueil.model.clear();
		Connection conn;
    	String requete = "Select NomRegate From Regate";
		try {
			conn = DriverManager.getConnection(cheminBdd);
	    	Statement s = conn.createStatement();
	    	ResultSet rs = s.executeQuery(requete);
	    	while (rs.next()) {
	    		Accueil.model.addElement(rs.getString(1));
	    	}
	    	conn.close();
	    	s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}